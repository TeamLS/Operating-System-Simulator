/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.io.IOException;

/**
 *
 * @author User
 */
public class Main {

    public static Clock clock;
    public static CPU cpu;
    public static NewProcessTemporaryList newProcessList;
    public static ReadyProcessesList readyProcessesList;
    public static ProcessGenerator processGen, processParse;
    public static Statistics stats;

    public static boolean end() {

        return (cpu.getRunningProcess() == null) && (readyProcessesList.isEmpty());
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here

        processGen = new ProcessGenerator("filename", false); //Δεν χρειάζεται εάν υπάρχει ήδη αρχείο
        processParse = new ProcessGenerator("filename", true);
        cpu = new CPU();
        newProcessList = new NewProcessTemporaryList();
        processParse.addProcessesToTemporayList();
        newProcessList.printList();
        stats = new Statistics("statistics.txt");
        clock = new Clock();
        readyProcessesList = new ReadyProcessesList();

        SJFScheduler sjfs = new SJFScheduler(true);
        //sjfs.addProcessToReadyList(new Process(1,2,3799));
        //sjfs.addProcessToReadyList(new Process(2,2880,3884));

        while (!end()) {
            
            sjfs.SJF();
            cpu.execute();
        }

        System.out.println(clock.ShowTime());
    }

}