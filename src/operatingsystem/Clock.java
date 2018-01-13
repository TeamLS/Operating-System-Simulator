/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import static operatingsystem.Main.newProcessList;

/* Κλάση που αναπαριστά το ρολόι του συστήματος */
class Clock {

    /* αποθηκεύει τον τρέχων αριθμό χτύπων ρολογιού που έχουν παρέλθει */
    protected static int ticks;

    /* constructor της κλάσης */
    public Clock() {

        this.ticks = 0;

    }

    /* αύξηση των χτύπων του ρολογιού (+1) */
    public void Time_Run() {

        //System.out.println("Time: " + this.ShowTime());
        //System.out.println("Current process:");
        //System.out.println(Main.cpu.getRunningProcess());
       
        this.ticks++;

        newProcessList.update();


    }

    /* επιστροφή της ώρας βάσει της μεταβλητής ticks */
    public int ShowTime() {

        return this.ticks;
    }
}
