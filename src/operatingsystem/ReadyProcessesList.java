/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/* Λίστα στην οποία τοποθετούνται οι έτοιμες (Ready) διεργασίες. Η υλοποίησή της εξαρτάται από τον εκάστοτε
αλγόριθμο που θα χρησιμοποιηθεί. Σκεφτείτε για παράδειγμα αν είναι κατάλληλη η χρήση κάποιας δομής κυκλικά
συνδεδεμένης λίστας, ουράς προτεραιότητας, ή κάποιας άλλης δομής δεδομένων */
public class ReadyProcessesList {

    /* η λίστα που περιέχει τις έτοιμες διεργασίες */
    private Queue<Process> processList;

    /* constructor της κλάσης */
    public ReadyProcessesList(Comparator comp) {

        this.processList = new PriorityQueue<>(comp);
    }

    /* προσθήκη μιας νέας έτοιμης διεργασίας στη λίστα*/
    public void addProcess(Process item) {
        item.setProcessState(ProcessState.READY);
        this.processList.add(item);
    }

    /* επιστροφή της διεργασίας της οποίας η σειρά είναι να εκτελεστεί στη CPU σύμφωνα με τον εκάστοτε
αλγόριθμο δρομολόγησης */
    public Process getProcessToRunInCPU() {
        return this.processList.peek();
    }   
    
    public void removeProcess() {
        this.processList.remove();
    }
        
    public Process getAndRemoveProcessToRunInCPU() {
        return this.processList.poll();
    }   

    /* εκτύπωση του περιεχομένου της λίστας στην οθόνη */
    public void printList() {

        for (Process proc : processList) {
            proc.toString();
        }
    }

}
