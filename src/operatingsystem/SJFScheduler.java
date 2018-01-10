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

    /* τοποθετεί μια διεργασία στην κατάλληλη θέση της λίστας των έτοιμων διεργασιών*/
    public void addProcessToReadyList(Process process) {
        Main.readyProcessesList.addProcess(process);
    }

    /* εκτελεί την εναλλαγή διεργασίας στη CPU με βάση τη λίστα έτοιμων διεργασιών και το είδος του
    αλγορίθμου δρομολόγησης (preemptive / non-preemptive) */
    public void SJF() {

        if (isPreemptive) {

            Process runningProcess = Main.cpu.getRunningProcess();

            if (runningProcess != null) {

                Process processToRunInCPU = Main.readyProcessesList.getProcessToRunInCPU();

                if (runningProcess.getRemainingTime() > processToRunInCPU.getRemainingTime()) {
                    // There must be a preemption here

                    Main.readyProcessesList.removeProcess();

                    Main.cpu.removeProcessFromCpu();
                    if (runningProcess.getCurrentState() == ProcessState.READY) {
                        // process hasn't terminated, so we add it in Ready Processes List
                        Main.readyProcessesList.addProcess(runningProcess);
                    }

                    Main.cpu.addProcess(processToRunInCPU);
                }

            } else {
                Main.cpu.addProcess(Main.readyProcessesList.getAndRemoveProcessToRunInCPU());
            }

        } else {

            Process runningProcess = Main.cpu.getRunningProcess();

            if (runningProcess != null) {

                if (runningProcess.getCurrentState() == ProcessState.TERMINATED) {
                    // process has terminated
                    Main.cpu.removeProcessFromCpu();
                    Main.cpu.addProcess(Main.readyProcessesList.getAndRemoveProcessToRunInCPU());
                }

            } else {
                Main.cpu.addProcess(Main.readyProcessesList.getAndRemoveProcessToRunInCPU());
            }

        }

        Main.cpu.execute();

    }
}
