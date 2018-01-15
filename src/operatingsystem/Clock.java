package operatingsystem;

import static operatingsystem.Main.newProcessList;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Κλάση που αναπαριστά το ρολόι του συστήματος */
public class Clock {

    /* αποθηκεύει τον τρέχων αριθμό χτύπων ρολογιού που έχουν παρέλθει */
    protected static int ticks;

    /* constructor της κλάσης */
    public Clock() {
        this.ticks = -1;
    }

    /* αύξηση των χτύπων του ρολογιού (+1) */
    public void Time_Run() {        
        
        Main.readyProcessesList.updateWaitingAndResponseTimes(); // Ενημέρωση χρόνων για τα στατιστικά
        ticks++;
        newProcessList.update(); // Ενημέρωση της ready list από την new list        

    }

    /* επιστροφή της ώρας βάσει της μεταβλητής ticks */
    public int ShowTime() {
        return ticks;
    }

    /* επαναφέρει το ρολόι στην αρχική του τιμή ώστε να τρέξει νέα προσομοίωση */
    public void reset() {
        this.ticks = -1;
    }
}
