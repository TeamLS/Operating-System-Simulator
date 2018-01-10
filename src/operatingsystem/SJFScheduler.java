/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

public class SJFScheduler {

    private boolean isPreemptive;

    SJFScheduler(boolean isPreemptive) {
        this.isPreemptive = isPreemptive;
        Main.readyProcessesList = new ReadyProcessesList(new TotalTimeComparator());
    }

    /*
    /* τοποθετεί μια διεργασία στην κατάλληλη θέση της λίστας των έτοιμων διεργασιών
    public void addProcessToReadyList(Process process) {
        Main.readyProcessesList.addProcess(process);
    }
     */
 /* εκτελεί την εναλλαγή διεργασίας στη CPU με βάση τη λίστα έτοιμων διεργασιών και το είδος του
    αλγορίθμου δρομολόγησης (preemptive / non-preemptive) */
    public void SJF() {

        Process runningProcess = Main.cpu.getRunningProcess();

        if (isPreemptive) {

            if (runningProcess != null) {

                if (runningProcess.getCurrentState() == ProcessState.TERMINATED) {
                    // process has terminated
                    Main.cpu.removeProcessFromCpu();

                } else {
                    Main.readyProcessesList.addProcess(runningProcess);
                }

            }

            Process exe_proc = Main.readyProcessesList.getAndRemoveProcess();
            Main.cpu.addProcess(exe_proc);
            Main.cpu.setTimeToNextContextSwitch(Main.clock.ShowTime() + 1);

        } else {

            if (runningProcess != null) {

                if (runningProcess.getCurrentState() == ProcessState.TERMINATED) {
                    // process has terminated
                    Main.cpu.removeProcessFromCpu();
                    Main.cpu.addProcess(Main.readyProcessesList.getAndRemoveProcess());
                }

            } else {
                
                Process exe_proc = Main.readyProcessesList.getAndRemoveProcess();
                Main.cpu.addProcess(exe_proc);
                Main.cpu.setTimeToNextContextSwitch(Main.clock.ShowTime() + 1);
                
            }

        }

    }
}
