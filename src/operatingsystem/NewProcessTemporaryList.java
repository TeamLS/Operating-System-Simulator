package operatingsystem;

import java.util.PriorityQueue;
import java.util.Queue;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Αναπαριστά μια λίστα στην οποία τοποθετούνται νέες διεργασίες που μόλις έχουν δημιουργηθεί και βρίσκονται
στην κατάσταση new */
public class NewProcessTemporaryList {

    /* η λίστα που περιέχει τις νέες διεργασίες */
    private final Queue<Process> processList;

    /* constructor - αρχικοποίηση της λίστας και άλλων πεδίων */
    public NewProcessTemporaryList() {
        // Δίνεται προτεραιότητα στις διεργασίες με το μικρότερο arrival time
        this.processList = new PriorityQueue<>(new ArrivalTimeComparator().reversed());
    }

    /* εισαγωγή μιας νέας διεργασίας στη λίστα */
    public void addNewProcess(Process process) {
        this.processList.add(process);
    }

    /* επιστροφή της πρώτης διεργασίας της λίστας */
    public Process getFirst() {
        if (!processList.isEmpty()) {
            return processList.poll();
        }
        return null;
    }
    
    /* επιστροφή της πρώτης διεργασίας της λίστας χωρις διαγραφή της διεργασίας από τη λίστα */
    public Process peekFirst() {
        if (!processList.isEmpty()) {
            return processList.peek();
        }
        return null;
    }

    /* εκτύπωση της λίστας με τις νέες διεργασίες στην οθόνη */
    public void printList() {
        for (Process proc : processList) {
            System.out.println(proc.toString());
        }
    }
    
    /* Έλεγχος για διεργασίες που έφτασε ο arraival time τους
    και πέρασμά τους στην ready list */
    public void update() {
        
        Process nextProcessForReadyList = peekFirst();
        if (nextProcessForReadyList == null){
            return;
        }
        
        /* όσο υπάρχουν διεργασίες που έχουν arrival time ίσο
        με τον τωρινό χρόνο, προσθήκη αυτών στην ready list */
        while (nextProcessForReadyList.getArrivalTime() == Main.clock.ShowTime()) {
            Main.readyProcessesList.addProcess(getFirst());            
            nextProcessForReadyList = peekFirst();
            if (nextProcessForReadyList == null) {
                return;
            }
        }
        
    }

    /* επιστρέφει true ή false αν η new list είναι άδεια ή όχι αντίστοιχα */
    public boolean isEmpty() {
        return this.processList.isEmpty();
    }
    
    /* επιστρέφει το μέγεθος της new list */
    public int getSize(){
        return processList.size();
    }

}
