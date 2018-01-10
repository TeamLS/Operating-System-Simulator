/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import static operatingsystem.Main.stats;

/* Η κλάση αυτή παριστάνει τη μονάδα επεξεργασίας CPU του συστήματος */
public class CPU {

    /* περιέχει τη διεργασία που χρησιμοποιεί τη CPU την τρέχουσα στιγμή */
    private Process runningProcess;
    /* περιέχει τη χρονική στιγμή της επόμενης διακοπής */
    private int timeToNextContextSwitch;
    /* περιέχει τη χρονική στιγμή έναρξης της τελευταίας διεργασίας */
    private int lastProcessStartTime;

    /* constructor – αρχικοποίηση των πεδίων*/
    public CPU() {

        this.runningProcess = null;
        this.timeToNextContextSwitch = 0;
        this.lastProcessStartTime = 0;

    }

    /* εισαγωγή της διεργασίας προς εκτέλεση στη CPU */
    public void addProcess(Process process) {

        process.setProcessState(ProcessState.RUNNING);
        this.runningProcess = process;
    }

    /* εξαγωγή της τρέχουσας διεργασίας από τη CPU */
    public Process removeProcessFromCpu() {

        Process proc = this.runningProcess;
        this.runningProcess = null;

        return proc;
    }

    /* εκτέλεση της διεργασίας με αντίστοιχη μέιωση του χρόνου εκτέλεσής της */
    public void execute() {

        runningProcess.decreaseCpuRemainingTime();
        Main.clock.Time_Run();

        if (runningProcess.getRemainingTime() == 0) {
            runningProcess.setProcessState(ProcessState.TERMINATED);
            stats.processTerminated(runningProcess);
        }

        // Update waitingTime and responseTime of all processes
        for (Process proc : Main.readyProcessesList.getAllReadyProcesses()) {
            if (proc != runningProcess) {
                proc.increaseWaitingTime();
                proc.increaseResponseTime();
            }
        }

    }

    public Process getRunningProcess() {
        return this.runningProcess;
    }

}
