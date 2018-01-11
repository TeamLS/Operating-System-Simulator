/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Main {

    public static Clock clock;
    public static CPU cpu;
    public static NewProcessTemporaryList newProcessList;
    public static ReadyProcessesList readyProcessesList;
    public static ProcessGenerator processGen;
    public static Statistics stats;

    public static boolean end() {

        return (cpu.getRunningProcess() == null) && (readyProcessesList.isEmpty());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        processGen = new ProcessGenerator("Inputfile.txt", false);
        cpu = new CPU();
        stats = new Statistics("statistics.txt");
        clock = new Clock();
        readyProcessesList = new ReadyProcessesList();

        try {
            newProcessList = new NewProcessTemporaryList();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SJFScheduler sjfs = new SJFScheduler(true);

        while (!end()) {
            sjfs.SJF();
            cpu.execute();
        }

    }

}
