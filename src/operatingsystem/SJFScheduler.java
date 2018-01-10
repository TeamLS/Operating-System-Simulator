/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

public class SJFScheduler {

    private boolean isPreemptive;
    public ReadyProcessesList readyProcessesList;

    SJFScheduler(boolean isPreemptive) {
        this.isPreemptive = isPreemptive;
        readyProcessesList = new ReadyProcessesList(new TotalTimeComparator());
    }

    /* τοποθετεί μια διεργασία στην κατάλληλη θέση της λίστας των έτοιμων διεργασιών*/
    public void addProcessToReadyList(Process process) {
        this.readyProcessesList.addProcess(process);
    }

    /* εκτελεί την εναλλαγή διεργασίας στη CPU με βάση τη λίστα έτοιμων διεργασιών και το είδος του
    αλγορίθμου δρομολόγησης (preemptive / non-preemptive) */
    public void SJF() {

        if (isPreemptive) {

            Process runningProcess = Main.cpu.getRunningProcess();

            if (runningProcess != null) {

                Process processToRunInCPU = readyProcessesList.getProcessToRunInCPU();

                if (runningProcess.getRemainingTime() > processToRunInCPU.getRemainingTime()) {
                    // There must be a preemption here

                    readyProcessesList.removeProcess();

                    Main.cpu.removeProcessFromCpu();
                    if (runningProcess.getCurrentState() == ProcessState.READY) {
                        // process hasn't terminated, so we add it in Ready Processes List
                        this.readyProcessesList.addProcess(runningProcess);
                    }

                    Main.cpu.addProcess(processToRunInCPU);
                }

            } else {
                Main.cpu.addProcess(this.readyProcessesList.getAndRemoveProcessToRunInCPU());
            }

        } else {

            Process runningProcess = Main.cpu.getRunningProcess();

            if (runningProcess != null) {

                if (runningProcess.getCurrentState() == ProcessState.TERMINATED) {
                    // process has terminated
                    Main.cpu.removeProcessFromCpu();
                    Main.cpu.addProcess(this.readyProcessesList.getAndRemoveProcessToRunInCPU());
                }

            } else {
                Main.cpu.addProcess(this.readyProcessesList.getAndRemoveProcessToRunInCPU());
            }

        }

        Main.cpu.execute();

    }
}
