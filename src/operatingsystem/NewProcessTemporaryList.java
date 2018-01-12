/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/* Αναπαριστά μια λίστα στην οποία τοποθετούνται νέες διεργασίες που μόλις έχουν δημιουργηθεί και βρίσκονται
στην κατάσταση new */
public class NewProcessTemporaryList {

    /* η λίστα που περιέχει τις νέες διεργασίες */
    private Queue<Process> processList;
    private Process nextProcessForReadyList;

    /* constructor - αρχικοποίηση της λίστας και άλλων πεδίων */
    public NewProcessTemporaryList() {

        this.processList = new PriorityQueue<>(new ArrivalTimeComparator());
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

    /* εκτύπωση της λίστας με τις νέες διεργασίες στην οθόνη */
    public void printList() {

        for (Process proc : processList) {
            System.out.println(proc.toString());
        }
    }

    void update() {
        if (nextProcessForReadyList == null) {
            nextProcessForReadyList = getFirst();
            if (nextProcessForReadyList == null){
                return;
            }
        }
        
        if (nextProcessForReadyList.getArrivalTime() == Main.clock.ShowTime()) {
            this.addNewProcess(nextProcessForReadyList);
            nextProcessForReadyList = getFirst();
        }
    }

    public boolean isEmpty() {
        return this.processList.isEmpty();
    }

}
