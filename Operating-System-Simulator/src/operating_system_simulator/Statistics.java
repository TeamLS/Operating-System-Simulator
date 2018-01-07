/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.io.File;

/* Αυτή η κλάση υπολογίζει ορισμένα στατιστικά στοιχεία βάσει των διεργασιών που εμφανίζονται στο σύστημα και
τα αποθηκεύει σε ένα αρχείο */
class Statistics {

    /* ο τρέχων μέσος χρόνος αναμονής των διεργασιών προς εκτέλεση */
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

    /* constructor της κλάσης */
    public Statistics(String filename) {
    }

    /* ελέγχει το μήκος της λίστας έτοιμων διεργασιών και ενημερώνει, αν είναι απαραίτητο, την τιμή της
μεταβλητής maximumLengthOfReadyProcessesList */
    public void UpdateMaximumListLength() {
    }

    /*υ πολογίζει τον μέσο χρόνο αναμονής*/
    public float CalculateAverageWaitingTime() {

        //calculate
        return this.averageWaitingTime;
    }

    /* προσθέτει μια νέα γραμμή με τα τρέχοντα στατιστικά στο αρχείο outputFile */
    public void WriteStatistics2File() {
    }
}
