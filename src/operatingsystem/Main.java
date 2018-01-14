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
        return (cpu.getRunningProcess() == null) && (readyProcessesList.isEmpty()) && (newProcessList.isEmpty());
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

       // processGen = new ProcessGenerator("filename", false); //Δεν χρειάζεται εάν υπάρχει ήδη αρχείο
        processParse = new ProcessGenerator("filename", true);
        cpu = new CPU();
        newProcessList = new NewProcessTemporaryList();
        stats = new Statistics("statistics.txt");
        clock = new Clock();
        readyProcessesList = new ReadyProcessesList();

        
         // ============== PREEMPTIVE SJF ==============//
        
        processParse.addProcessesToTemporayList();
        SJFScheduler sjfs = new SJFScheduler(false);
          
        while (!end()) {
            sjfs.SJF();
            cpu.execute();
        }
        
        stats.WriteStatistics2File();
        stats.printStatistics();
        
        
        // ============== NON-PREEMPTIVE SJF ==============//
        clock.reset();
        processParse.addProcessesToTemporayList();
        sjfs.setIsPreemptive(true);
        
        while (!end()) {

            sjfs.SJF();
            cpu.execute();
        }
        
        stats.WriteStatistics2File();
        stats.printStatistics();
        
        
        // ============== RR WITH QUANTUM = 50 ==============//
        
        clock.reset();
        processParse.addProcessesToTemporayList();
        RRScheduler rrs = new RRScheduler(50);
        
        while (!end()) {
            rrs.RR();
            cpu.execute();
        }
        
        stats.WriteStatistics2File();
        stats.printStatistics();
        
        // ============== RR WITH QUANTUM = 300 ==============//
        clock.reset();
        processParse.addProcessesToTemporayList();
        rrs.setQuantum(300);
        
        while (!end()) {
            rrs.RR();
            cpu.execute();
        }
       
        stats.WriteStatistics2File();
        stats.printStatistics();

    }
    

}
