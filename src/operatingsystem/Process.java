package operatingsystem;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Η συγκεκριμένη κλάση αναπαριστά μια διεργασία του συστήματος */
public class Process {

    /* το id της διεργασίας (PID) */
    private int pid;
    /* περιέχει την χρονική στιγμή άφιξης της διεργασίας στο σύστημα */
    private final int arrivalTime;
    /* περιέχει το συνολικό χρόνο απασχόλησης της CPU από τη διεργασία */
    private final int cpuTotalTime;
    /* περιέχει τον εναπομείναντα χρόνο απασχόλησης της CPU από τη διεργασία */
    private int cpuRemainingTime;
    /* περιέχει την τρέχουσα κατάσταση της διεργασίας
    0 – Created/New, 1 – Ready/Waiting, 2 – Running, 3 – Terminated */
    private int currentState;    
    /* περιέχει την χρονική διάρκεια αναμονής στην ready list (χρησιμεύει για τα στατιστικά)*/
    private int waitingTime;
    /* περιέχει την χρόνο απόκρισης της διεργασίας (χρησιμεύει για τα στατιστικά) */
    private int responseTime;
    /* true αν η διεργασία έχει μπει έστω και μία φορά στην CPU, αλλιώς false (χρησιμεύει για τα στατιστικά) */
    private boolean requestAccepted;

    /* constructor – αρχικοποίηση των πεδίων */
    public Process(int pid, int arrivalTime, int cpuBurstTime) {

        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.cpuTotalTime = cpuBurstTime;
        this.cpuRemainingTime = cpuBurstTime;
        this.currentState = ProcessState.NEW;
        this.waitingTime = 0;
        this.requestAccepted = false;

    }
    

    /* θέτει την κατάσταση της διεργασίας ίση με την παράμετρο newState (αλλαγή της κατάστασής της) */
    public void setProcessState(int newState) {
        this.currentState = newState;
    }

    /* αλλάζει τον εναπομείναντα χρόνο απασχόλησης της CPU από τη διεργασία */
    public void changeCpuRemainingTime(int newCpuRemainingTime) {
        this.cpuRemainingTime = newCpuRemainingTime;
    }

    /* μείωση του χρόνου που απομένει για τον τερματισμό της διεργασίας */
    public void decreaseCpuRemainingTime() {
        if (this.cpuRemainingTime > 0) {
            this.cpuRemainingTime--;
        }
    }

    /* επιστρέφει τον συνολικό χρόνο που χρειάζεται στην CPU η διεργασία */
    public int getTotalTime() {
        return this.cpuTotalTime;
    }

    /* επιστρέφει τον εναπομείναντα χρόνο που χρειάζεται στην CPU η διεργασία */
    public int getRemainingTime() {
        return this.cpuRemainingTime;
    }

    /* επιστρέφει το χρόνο άφιξης της διεργασίας */
    public int getArrivalTime() {
        return this.arrivalTime;
    }

    /* επιστρέφει τον αριθμό που αντιστοιχεί στην κατάσταση της διεργασίας */
    public int getCurrentState() {
        return this.currentState;
    }

    /* αυξάνει τον χρόνο αναμονής της διεργασίας στην ready list */
    public void increaseWaitingTime() {
        this.waitingTime++;
    }

    /* αυξάνει τον χρόνο ανταπόκρισης της διεργασίας */
    public void increaseResponseTime() {
        if (!requestAccepted) {
            this.responseTime++;

        }
    }

    /* επιστρέφει το PID της διεργασίας */
    public int getPID(){
        return this.pid;
    }
    
    /* επιστρέφει το χρόνο αναμονής της διεργασίας στην ready list */
    public int getWaitingTime() {
        return this.waitingTime;
    }

    /* επιστρέφει το χρόνο απόκρισης της διεργασίας στην ready list */
    public int getResponseTime() {
        return this.responseTime;
    }

    /* δήλωση ότι η διεργασία αυτή έχει μπει στην CPU (χρειάζεται στα στατιστικά) */
    public void requestAccepted() {
        this.requestAccepted = true;
    }

    @Override
    public String toString() {

        // StringBuilder info  = new StringBuilder();
        return "PID: " + this.pid + '\n'
                + "Current State: " + ProcessState.getStateName(this.currentState) + '\n'
                + "Arrival Time: " + this.arrivalTime + '\n'
                + "CPU Total Time: " + this.cpuTotalTime + '\n'
                + "CPU Remaining Time: " + this.cpuRemainingTime + '\n';

    }

}