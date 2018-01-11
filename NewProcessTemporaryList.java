/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/* Αναπαριστά μια λίστα στην οποία τοποθετούνται νέες διεργασίες που μόλις έχουν δημιουργηθεί και βρίσκονται
στην κατάσταση new */
public class NewProcessTemporaryList {

    /* η λίστα που περιέχει τις νέες διεργασίες */
    private Queue<Process> processList;
    private BufferedReader input_times; //reads the times of prcesses nneded for completion from a file.
    private BufferedWriter setting_times; //in case we need to create random times.
    private Random rand = new Random();
    private String next;
    private int n;
    private int prev;

    private int arrivalTime, cpuBurstTime;

    /* constructor - αρχικοποίηση της λίστας και άλλων πεδίων */
    public NewProcessTemporaryList() throws IOException {

        this.processList = new LinkedList<>();

        setting_times = new BufferedWriter(new FileWriter("Process_times.txt")); //Process_times.txt είναι το αρχείο που αποθηκεύονται οι διεργασίες

        prev = 0; //Ο χρόνος άφοιξης της προηγούμενηυς διεργασίας. Τον χρόνο 0 δεν ήρθε καμία διεργασία.
        n = rand.nextInt(1000); //Δημιουργεί τυχαία από 0 εως 1000 διεργασίες.
        for (int i = 0; i < n; i++) {
            prev = (rand.nextInt(2000)) + prev;  //2000 είναι τα millisecond "διαφοράς άφοιξης" που θα έχει η μια διεργασία από μια άλλη
            setting_times.write(Integer.toString(prev));//Χρόνος άφοιξης.
            setting_times.newLine();

            setting_times.write(Integer.toString(rand.nextInt(60000))); //60000 μπαίνει για να διαρκεί μια διεργασία από 0 εως 1 λεπτά. (60000 millisecond == 1 λεπτό).
            setting_times.newLine();
        }
        setting_times.close();

        input_times = new BufferedReader(new FileReader("Process_times.txt"));

        for (int i = 0; i < n; i++) {
            next = input_times.readLine(); //Διαβάζει τον χρόνο άφηξης της επόμενης διεργασίας.
            if (next != null) {
                arrivalTime = Integer.parseInt(next);
            } else {
                System.out.println("There was an error reading file by name Process_times.txt");
                input_times.close();
            }

            next = input_times.readLine(); //Διαβάζει τον χρόνο διάρκειας της επόμενης διεργασίας.
            if (next != null) {
                cpuBurstTime = Integer.parseInt(next);
            } else {
                System.out.println("There was an error reading file 'Process_times.txt'");
                input_times.close();
            }

            addNewProcess(new Process((i / 2) + 1, arrivalTime, cpuBurstTime)); //Το i αυξάνεται κατά 2 σε κάθε επανάληψη του βρόγχου. 
        }
        input_times.close();
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
