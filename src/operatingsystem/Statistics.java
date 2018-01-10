/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.io.File;
import java.util.ArrayList;

/* Αυτή η κλάση υπολογίζει ορισμένα στατιστικά στοιχεία βάσει των διεργασιών που εμφανίζονται στο σύστημα και
τα αποθηκεύει σε ένα αρχείο */
public class Statistics {

    /* ο μέσος χρόνος αναμονής των διεργασιών προς εκτέλεση */
    private float averageWaitingTime;
    /* ο τρέχων συνολικός χρόνος αναμονής διεργασιών */
    private int totalWaitingTime;
    /* ο μέσος χρόνος απόκρισης */
    private int responseTime;
    /* το τρέχον μέγιστο πλήθος διεργασιών προς εκτέλεση */
    private int maximumLengthOfReadyProcessesList;
    /* ο τρέχων συνολικός αριθμός διεργασιών */
    public int totalNumberOfProcesses;
    /*αρχείο που αποθηκεύονται τα στατιστικά δεδομένα */
    private File outputFile;

    private ArrayList<Process> terminatedProcesses;

    /* constructor της κλάσης */
    public Statistics(String filename) {

        this.maximumLengthOfReadyProcessesList = 0;

    }

    /* ελέγχει το μήκος της λίστας έτοιμων διεργασιών και ενημερώνει, αν είναι απαραίτητο, την τιμή της
μεταβλητής maximumLengthOfReadyProcessesList */
    public void UpdateMaximumListLength() {

        maximumLengthOfReadyProcessesList = Math.max(Main.readyProcessesList.getSize(), maximumLengthOfReadyProcessesList);

    }

    public void processTerminated(Process process) {
        this.terminatedProcesses.add(process);
    }

    /*υπολογίζει τον μέσο χρόνο απόκρισης*/
    public float CalculateAverageWaitingTime() {

        averageWaitingTime = 0;

        for (Process proc : terminatedProcesses) {
            averageWaitingTime += proc.getWaitingTime();
        }
        
        averageWaitingTime /= terminatedProcesses.size();
        
        return this.averageWaitingTime;
    }

    /*υπολογίζει τον μέσο χρόνο αναμονής*/
    public float CalculateAverageResponseTime() {

        responseTime = 0;

        for (Process proc : terminatedProcesses) {
            responseTime += proc.getResponseTime();
        }
        
        responseTime /= terminatedProcesses.size();
        
        return this.responseTime;
    }

    /*υπολογίζει τον μέσο χρόνο αναμονής*/
    public float CalculateTotalWaitingTime() {

        totalWaitingTime = 0;

        for (Process proc : terminatedProcesses) {
            totalWaitingTime += proc.getWaitingTime();
        }
        
        return totalWaitingTime;
    }
    
    
    /* προσθέτει μια νέα γραμμή με τα τρέχοντα στατιστικά στο αρχείο outputFile */
    public void WriteStatistics2File() {
    }
}
