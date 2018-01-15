package operatingsystem;

import java.util.Comparator;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Comparator που θέτει ως κριτήριο τον συνολικό χρόνο της διεργασίας */
public class RemainingTimeComparator implements Comparator {

    @Override
    public int compare(Object proc, Object proc2) {
        
        Process pr = (Process) proc;
        Process pr2 =  (Process) proc2;
        
        return  pr2.getRemainingTime()- pr.getRemainingTime() ;
      
    }
    
}
