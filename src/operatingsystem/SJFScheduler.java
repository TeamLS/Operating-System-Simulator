package operatingsystem;

import java.util.PriorityQueue;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/*  Αυτή η κλάση υλοποιεί την προεκχωρίσιμη και μη-προεκχωρίσιμη εκδοχή του αλγόριθμου SJFS. 
Ο αλγόριθμος SJFS ορίζει ως προτεραιότητα τον υπολοιπόμενο χρόνο κάθε διεργασίας.  Επομένως ο προεκχωρίσιμος SJFS κάθε φορά επιλέγει και αναθέτει στη CPU
την διεργασία  εκείνη με τον μικρότερο υπολοιπόμενο χρόνο, ενώ ο μη-προεκχωρίσιμος SJFS  κάνει την ίδια διαδικασία, με την μόνη διαφορά ότι το επόμενο context-switching
γίνεται όταν η διεργασία που έχει επιλεχθεί για την CPU έχει τερματιστεί. */
public class SJFScheduler {

    private boolean isPreemptive;

    SJFScheduler(boolean isPreemptive) {
        this.isPreemptive = isPreemptive;
        Main.readyProcessesList.defineQueueType(new PriorityQueue<>(new RemainingTimeComparator().reversed()));//Ορίζει το είδος της λίστας αναμονής με προτεραιότητα το συνολικό χρόνο της διεργασίας
    }

    /* τοποθετεί μια διεργασία στην κατάλληλη θέση της λίστας των έτοιμων διεργασιών */
    public void addProcessToReadyList(Process process) {
        Main.readyProcessesList.addProcess(process);
    }
    
    /* Αν η παράμετρος είναι True θέτει τον αλγόριθμο
    στην προεκχωρίση έκδοση του, διαφορετικά στην μη-προεκχωρίσιμη έκδοση. */
    public void setIsPreemptive (boolean isPreemptive){        
        this.isPreemptive = isPreemptive;
    }

    /* εκτελεί την εναλλαγή διεργασίας στη CPU με βάση τη λίστα έτοιμων διεργασιών και το είδος του
    αλγορίθμου δρομολόγησης (preemptive / non-preemptive) */
    public void SJF() {

        Process runningProcess = Main.cpu.getRunningProcess();

        if (isPreemptive) {

            // ================== Προεκχωρίσιμος SJFS ================== //
            
            if (runningProcess != null) {

                if (runningProcess.getCurrentState() == ProcessState.TERMINATED) {
                    
                    // Η διεργασία έχει τερματιστεί και επομένως γίνεται το context-switching
                    Main.cpu.removeProcessFromCpu(); 
                    Main.cpu.addProcess(Main.readyProcessesList.getAndRemoveProcess());

                } else {

                    Process proc = Main.readyProcessesList.getProcess();

                    // Ελέγχουμε αν η τρέχουσα διεργασία έχει μικρότερο υπολοιπόμενο χρόνο από όλες τις διεργασίες που βρίσκονται στη λίστα αναμονής
                    if (proc != null && (runningProcess.getRemainingTime() > Main.readyProcessesList.getProcess().getRemainingTime())) {

                        // Context Switching
                        Main.cpu.removeProcessFromCpu();
                        Main.cpu.addProcess(Main.readyProcessesList.getAndRemoveProcess());
                        Main.readyProcessesList.addProcess(runningProcess);

                    }
                }

            } else {
                //Δεν υπάρχει τρέχουσα διεργασία στη CPU και επομένως παίρνουμε αυτή με τον χαμηλότερο CPU χρόνο.
                Main.cpu.addProcess(Main.readyProcessesList.getAndRemoveProcess());
            }

            //Θέτουμε στη CPU το νέο χρόνο που θα συμβεί context-switching  ο οποίος είναι  το επόμενο κλικ του χρόνου
            Main.cpu.setTimeToNextContextSwitch(Main.clock.ShowTime());

        } else {
            
            // ================== Μη προεκχωρίσιμος SJFS ================== //
            
            Process nextProcess = null;

            if (runningProcess != null) {
                // Η CPU δεν έχει καμία διεργασία , επομένως ο αλγόριθμος παίρνει από τη λίστα διεργασίων την επόμενη διεργασία και την αναθέτει στη CPU.
                Main.cpu.removeProcessFromCpu();
            }
            
            //Υπάρχει διεργασία στη CPU και έχει τερματιστεί, οπότε αναθέτουμε την επόμενη διεργασία στη CPU
            nextProcess = Main.readyProcessesList.getAndRemoveProcess();

            if (nextProcess != null) {
                Main.cpu.addProcess(nextProcess);
                //Θέτουμε στη CPU το νέο χρόνο που θα συμβεί context-switching  ο οποίος ισούται με τον χρόνο που χρειάζεται για να τερματιστεί η συγκεκριμένη διεργασία
                Main.cpu.setTimeToNextContextSwitch(Main.clock.ShowTime() + nextProcess.getRemainingTime());
            }
        }

    }
}