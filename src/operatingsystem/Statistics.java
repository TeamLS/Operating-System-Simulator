/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import static operatingsystem.Main.stats;

/* Αυτή η κλάση υπολογίζει ορισμένα στατιστικά στοιχεία βάσει των διεργασιών που εμφανίζονται στο σύστημα και
τα αποθηκεύει σε ένα αρχείο */
public class Statistics {

    /* ο μέσος χρόνος επιστροφής των διεργασιών */
    private float averageTurnaroundTime;
    /* ο μέσος χρόνος αναμονής των διεργασιών προς εκτέλεση */
    private float averageWaitingTime;
    /* ο τρέχων συνολικός χρόνος αναμονής διεργασιών */
    private float totalWaitingTime;
    /* ο μέσος χρόνος απόκρισης */
    private float responseTime;
    /* το τρέχον μέγιστο πλήθος διεργασιών προς εκτέλεση */
    private int maximumLengthOfReadyProcessesList;
    /* ο τρέχων συνολικός αριθμός διεργασιών */
    private int totalNumberOfProcesses;
    /*αρχείο που αποθηκεύονται τα στατιστικά δεδομένα */
    private String fileName;

    private List<Process> terminatedProcesses;

    /* constructor της κλάσης */
    public Statistics(String fileName) {

        this.fileName = fileName;
        this.maximumLengthOfReadyProcessesList = 0;
        this.terminatedProcesses = new ArrayList<>();

    }

    /* ελέγχει το μήκος της λίστας έτοιμων διεργασιών και ενημερώνει, αν είναι απαραίτητο, την τιμή της
μεταβλητής maximumLengthOfReadyProcessesList */
    public void UpdateMaximumListLength() {

        maximumLengthOfReadyProcessesList = Math.max(Main.readyProcessesList.getSize(), maximumLengthOfReadyProcessesList);

    }

    public int getMaximumLengthOfReadyProcessesList() {
        return maximumLengthOfReadyProcessesList;
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

    /*υπολογίζει τον μέσο χρόνο επιστροφής*/
    public float CalculateAverageTurnaroundTime() {

        averageTurnaroundTime = 0;

        for (Process proc : terminatedProcesses) {
            averageTurnaroundTime += proc.getWaitingTime() + proc.getTotalTime();
        }

        averageTurnaroundTime /= terminatedProcesses.size();

        return this.averageTurnaroundTime;
    }

    /*υπολογίζει τον συνολικό χρόνο αναμονής*/
    public float CalculateTotalWaitingTime() {

        totalWaitingTime = 0;

        for (Process proc : terminatedProcesses) {
            totalWaitingTime += proc.getWaitingTime();
        }

        return totalWaitingTime;
    }

    public int getTotalNumberOfProcesses() {
        return this.terminatedProcesses.size();
    }

    /* προσθέτει μια νέα γραμμή με τα τρέχοντα στατιστικά στο αρχείο outputFile */
    public void WriteStatistics2File() {

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            writer.write("Maximum length of ready processes list: " + stats.getMaximumLengthOfReadyProcessesList());
            writer.newLine();
            writer.write("Average response time: " + CalculateAverageResponseTime());
            writer.newLine();
            writer.write("Average turnaround time: " + stats.CalculateAverageTurnaroundTime());
            writer.newLine();
            writer.write("Average waiting time: " + stats.CalculateAverageWaitingTime());
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}
