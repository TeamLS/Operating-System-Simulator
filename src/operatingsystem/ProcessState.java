package operatingsystem;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* κλάση που κρατάει τους αντίστοιχους αριθμούς για κάθε κατάσταση διεργασίας */
public class ProcessState {
    public static int NEW = 0;
    public static int READY = 1;
    public static int RUNNING = 2;
    public static int TERMINATED = 3;
    
    /* επιστρέφει σε string την κατάσταση της διεργασίας */
    public static String getStateName(int state){
        switch (state){
            case 0:
                return "NEW";
            case 1:
                return "READY";
            case 2:
                return "RUNNING";
            case 3:
                return "TERMINATED";
            default:
                return "UNSUPPORTED STATE";
        }
    }
    
}