/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.util.LinkedList;

public class RRScheduler {

    private int quantum;

    RRScheduler(int quantum) {
        this.quantum = quantum;
        Main.readyProcessesList.defineQueueType(new LinkedList<>());
    }

    /* τοποθετεί μια διεργασία στην κατάλληλη θέση της λίστας των έτοιμων διεργασιών */
    public void addProcessToReadyList(Process process) {
        Main.readyProcessesList.addProcess(process);
    }

    /* εκτελεί την εναλλαγή διεργασίας στη CPU με βάση τη λίστα έτοιμων διεργασιών και το είδος του
    αλγορίθμου δρομολόγησης */
    public void RR() {

        Process runningProcess = Main.cpu.getRunningProcess();

        if (runningProcess != null) {

            if (runningProcess.getCurrentState() != ProcessState.TERMINATED) {
                Main.readyProcessesList.addProcess(runningProcess);
            }
            Main.cpu.removeProcessFromCpu();

        }
        
        Process nextProcess = Main.readyProcessesList.getAndRemoveProcess();
        Main.cpu.addProcess(nextProcess);
        Main.cpu.setTimeToNextContextSwitch(Main.clock.ShowTime() + Math.min(quantum, nextProcess.getRemainingTime()));

    }
}
