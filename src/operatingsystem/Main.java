/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        processGen = new ProcessGenerator("Inputfile.txt", false);
        cpu = new CPU();
        newProcessList = new NewProcessTemporaryList();
        stats = new Statistics("statistics.txt");
        clock = new Clock();
        

        SJFScheduler sjfs = new SJFScheduler(true);

        while (true) {

            sjfs.SJF();
            cpu.execute();

            clock.Time_Run();

        }

    }

}
