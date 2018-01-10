/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

/* Η συγκεκριμένη κλάση αναπαριστά μια διεργασία του συστήματος */
public class Process {

    /* περιέχει την χρονική στιγμή άφιξης της διεργασίας στο σύστημα */
    private final int arrivalTime;
    /* περιέχει το συνολικό χρόνο απασχόλησης της CPU από τη διεργασία */
    private final int cpuTotalTime;
    /* περιέχει τον εναπομείναντα χρόνο απασχόλησης της CPU από τη διεργασία */
    private int cpuRemainingTime;
    /* περιέχει την τρέχουσα κατάσταση της διεργασίας: 0 – Created/New, 1 – Ready/Waiting, 2 – Running, 3 –
Terminated */
    private int currentState;
    private int pid;

    /* constructor – αρχικοποίηση των πεδίων */
    public Process(int pid, int arrivalTime, int cpuBurstTime) {

        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.cpuTotalTime = cpuBurstTime;
        this.cpuRemainingTime = cpuBurstTime;
        this.currentState = ProcessState.NEW;

    }

    /* θέτει την κατάσταση της διεργασίας ίση με την παράμετρο newState (αλλαγή της κατάστασής της) */
    public void setProcessState(int newState) {

        this.currentState = newState;

    }

    /* αλλάζει τον εναπομείναντα χρόνο απασχόλησης της CPU από τη διεργασία */
    public void changeCpuRemainingTime(int newCpuRemainingTime) {

        this.cpuRemainingTime = newCpuRemainingTime;
    }

    public void decreaseCpuRemainingTime() {
        if (this.cpuRemainingTime > 0) {
            this.cpuRemainingTime--;
        }
    }

    public int getTotalTime() {

        return this.cpuTotalTime;
    }

    public int getRemainingTime() {

        return this.cpuRemainingTime;
    }

    public int getArrivalTime() {

        return this.arrivalTime;
    }


    public int getCurrentState() {
        return this.currentState;
    }

    @Override
    public String toString() {

        // StringBuilder info  = new StringBuilder();
        return "PID: " + this.pid + '\n'
                + "Current State: " + this.currentState + '\n'
                + "Arrival Time: " + this.arrivalTime + '\n'
                + "CPU Total Time: " + this.cpuTotalTime + '\n'
                + "CPU Remaining Time: " + this.cpuRemainingTime + '\n';

    }

}
