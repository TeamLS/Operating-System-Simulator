package operatingsystem;

import java.util.LinkedList;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Η κλάση αυτή αναπαριστά τη λειτουργία του δρομολογητή με αλγόριθμο Round Robin,
μεταφέροντας διεργασίες από και προς τη CPU. Προσθέτει διεργασίες στη λίστα έτοιμων
διεργασιών σύμφωνα με το κβάντο που πέρνει ως παράμετρο στον constructor (quantum value) */
public class RRScheduler {

    /* το quantum που θα χρησιμοποιηθεί κατά την δρομολόγιση */
    private int quantum;

    RRScheduler(int quantum) {
        this.quantum = quantum;
        Main.readyProcessesList.defineQueueType(new LinkedList<>()); // Δομή απλής ουράς FIFO
    }

    /* τοποθετεί μια διεργασία στην κατάλληλη θέση της λίστας των έτοιμων διεργασιών */
    public void addProcessToReadyList(Process process) {
        Main.readyProcessesList.addProcess(process);
    }
    
    /* αλαγή του quantum */
    public void setQuantum(int quantum){
        this.quantum = quantum;
    }

    /* εκτελεί την εναλλαγή διεργασίας στη CPU με βάση τη
    λίστα έτοιμων διεργασιών και το είδος του αλγορίθμου δρομολόγησης */
    public void RR() {

        // Context Switching
        Process runningProcess = Main.cpu.getRunningProcess();

        Main.cpu.removeProcessFromCpu(); // απομάκρυνση της διεργασίας που εκτελείται τώρα στη CPU

        Process nextProcess = Main.readyProcessesList.getAndRemoveProcess();
        if (nextProcess != null) {
            // Προσθήκη της κατάλληλης διεργασίας από τη ready list στη CPU
            Main.cpu.addProcess(nextProcess);
            // Το επόμενο context-switch θα γίνει μετά από χρόνο ίσο με το κβάντο ή όταν τελειώσει η διεργασία αν θέλει χρόνο λιγότερο του κβάντου
            Main.cpu.setTimeToNextContextSwitch(Main.clock.ShowTime() + quantum - 1);
        }
        
        if (runningProcess != null && runningProcess.getCurrentState() != ProcessState.TERMINATED) {
            // Η διεργασία που εκτελούνταν στην CPU δεν τερμάτησε ακόμα οπότε ξαναμπαίνει στην ready list
            Main.readyProcessesList.addProcess(runningProcess);
        }

    }
}
