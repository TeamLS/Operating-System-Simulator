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

        //System.out.println("CPU RUNNING PROCESS NULL: "+(cpu.getRunningProcess() == null));
        //System.out.println("READY PROCESS LIST EMPTY: "+readyProcessesList.isEmpty());
        //System.out.println("NEW PROCESS LIST EMPTY: "+newProcessList.isEmpty());
        return (cpu.getRunningProcess() == null) && (readyProcessesList.isEmpty()) && (newProcessList.isEmpty());
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        processGen = new ProcessGenerator("filename", false); //Δεν χρειάζεται εάν υπάρχει ήδη αρχείο
        processParse = new ProcessGenerator("filename", true);
        cpu = new CPU();
        newProcessList = new NewProcessTemporaryList();
        processParse.addProcessesToTemporayList();
        //newProcessList.printList();
        stats = new Statistics("statistics.txt");
        clock = new Clock();
        readyProcessesList = new ReadyProcessesList();

        SJFScheduler sjfs = new SJFScheduler(true);
        //sjfs.addProcessToReadyList(new Process(1,9,20));
        //sjfs.addProcessToReadyList(new Process(2,10,30));

        
        while (!end()) {

            sjfs.SJF();
            cpu.execute();
        }
        
        System.out.println("Total time needed: " + (clock.ShowTime()-1));
        System.out.println("Maximum length of ready processes list: " + stats.getMaximumLengthOfReadyProcessesList());
        System.out.println("Average response time: " + stats.CalculateAverageResponseTime());
        System.out.println("Average turnaround time: " + stats.CalculateAverageTurnaroundTime());
        System.out.println("Average waiting time: " + stats.CalculateAverageWaitingTime());
        System.out.println("Total waiting time: " + stats.CalculateTotalWaitingTime());

    }

}
