package operatingsystem;

import java.util.Queue;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Λίστα στην οποία τοποθετούνται οι έτοιμες (Ready) διεργασίες. Η υλοποίησή της εξαρτάται από τον εκάστοτε
αλγόριθμο που θα χρησιμοποιηθεί. Σκεφτείτε για παράδειγμα αν είναι κατάλληλη η χρήση κάποιας δομής κυκλικά
συνδεδεμένης λίστας, ουράς προτεραιότητας, ή κάποιας άλλης δομής δεδομένων */
public class ReadyProcessesList {

    /* η λίστα που περιέχει τις έτοιμες διεργασίες */
    private Queue<Process> processList;

    /* constructor της κλάσης */
    public ReadyProcessesList() {}
    
    /*Ορίζει τον τύπο της ουράς ανάλογως τον δρομολογητή*/
    public void defineQueueType(Queue queue){
        this.processList = queue;
    }

    /* προσθήκη μιας νέας έτοιμης διεργασίας στη λίστα*/
    public void addProcess(Process item) {
        item.setProcessState(ProcessState.READY);
        this.processList.add(item);
       // System.out.println("Time " + Main.clock.ShowTime() + ": Process "+item.getPID()+" from new to ready");
        Main.stats.UpdateMaximumListLength();
    }

    /* επιστροφή της διεργασίας της οποίας η σειρά είναι να εκτελεστεί στη CPU σύμφωνα με τον εκάστοτε
αλγόριθμο δρομολόγησης */
    public Process getProcess() {
        
        return this.processList.peek();
    }   
    
    /*
      διαγραφή της διεργασίας με την υψηλότερη προτεραιότητα από την λίστα
    */
    public void removeProcess() {
        
        this.processList.remove();
    }
     
    /* επιστροφή της διεργασίας με την υψηλότερη προτεραιότητα  και διαγραφή της από τη λίστα */
    public Process getAndRemoveProcess() {
        Process proc = this.processList.poll();
        return proc;
    }   

    /* εκτύπωση του περιεχομένου της λίστας στην οθόνη */
    public void printList() {
        for (Process proc : processList) {
            proc.toString();
        }
    }
       
    /* επιστροφή του πλήθους των έτοιμων διεργασιών */
    public int getSize(){
        return processList.size();
    }
    
    /* Ανανέωση του χρόνου αναμονής  και του χρόνου απόκρισης  όλων των διεργασιών από την λίστα */
    public void updateWaitingAndResponseTimes(){
        for (Process proc : processList) {
            proc.increaseWaitingTime();
            proc.increaseResponseTime();
        }
    }
    
    /* true αν η λίστα είναι κενή διαφορετικά false*/
    public boolean isEmpty() {
        
        return processList.isEmpty();
    }
}