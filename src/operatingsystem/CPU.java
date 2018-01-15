package operatingsystem;

import static operatingsystem.Main.stats;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Η κλάση αυτή παριστάνει τη μονάδα επεξεργασίας CPU του συστήματος */
public class CPU {

    /* περιέχει τη διεργασία που χρησιμοποιεί τη CPU την τρέχουσα στιγμή */
    private Process runningProcess;
    /* περιέχει τη χρονική στιγμή της επόμενης διακοπής */
    private int timeToNextContextSwitch;
    /* περιέχει τη χρονική στιγμή έναρξης της τελευταίας διεργασίας */
    private int lastProcessStartTime;

    /* constructor – αρχικοποίηση των πεδίων*/
    public CPU() {

        this.runningProcess = null;
        this.timeToNextContextSwitch = 0;
        this.lastProcessStartTime = 0;

    }

    /* εισαγωγή της διεργασίας προς εκτέλεση στη CPU */
    public void addProcess(Process process) {
        if (process != null) {
            process.setProcessState(ProcessState.RUNNING);
            process.requestAccepted();
            this.runningProcess = process;
        }
    }

    /* εξαγωγή της τρέχουσας διεργασίας από τη CPU */
    public Process removeProcessFromCpu() {

        Process proc = this.runningProcess;
        this.runningProcess = null;

        return proc;
    }

    /* αλλάγη του χρόνου επόμενου context switch */
    public void setTimeToNextContextSwitch(int time) {
        this.timeToNextContextSwitch = time;
    }

    /* εκτέλεση της διεργασίας με αντίστοιχη μέιωση του χρόνου εκτέλεσής της */
    public void execute() {

        if (runningProcess == null) { // Δεν υπάρχει καμία διεργασία που τρέχει στην CPU
            Main.clock.Time_Run();
            return;
        }

        this.lastProcessStartTime = Main.clock.ShowTime();

        // Εκτέλεση της διεργασίας μέχρι το επόμενο context-switch
        while (Main.clock.ShowTime() <= this.timeToNextContextSwitch) {

            if (runningProcess.getRemainingTime() == 0) { // Η διεργασία έχει τερματίσει
                runningProcess.setProcessState(ProcessState.TERMINATED);
                stats.processTerminated(runningProcess);
                break;
            }

            // Εκτέλεση της διεργασίας
            Main.clock.Time_Run();
            runningProcess.decreaseCpuRemainingTime();

        }

    }

    /* επιστρέφει την διεργασία που τρέχει αυτή τη στιγμή
    ή null αν δεν τρέχει καμία διεργασία στην CPU */
    public Process getRunningProcess() {
        return this.runningProcess;
    }

}
