/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.util.LinkedList;
import java.util.Queue;

/* Αναπαριστά μια λίστα στην οποία τοποθετούνται νέες διεργασίες που μόλις έχουν δημιουργηθεί και βρίσκονται
στην κατάσταση new */
public class NewProcessTemporaryList {

    /* η λίστα που περιέχει τις νέες διεργασίες */
    private Queue<Process> processList;

    /* constructor - αρχικοποίηση της λίστας και άλλων πεδίων */
    public NewProcessTemporaryList() {
        
        this.processList = new LinkedList<>();
    }

    /* εισαγωγή μιας νέας διεργασίας στη λίστα */
    public void addNewProcess(Process process) {
        
        this.processList.add(process);
    }

    /* επιστροφή της πρώτης διεργασίας της λίστας */
    public Process getFirst() {
        
        return this.processList.poll();
    }

    /* εκτύπωση της λίστας με τις νέες διεργασίες στην οθόνη */
    public void printList() {
        
         for (Process proc : processList) {
                
                 proc.toString();
            }
    }
}
