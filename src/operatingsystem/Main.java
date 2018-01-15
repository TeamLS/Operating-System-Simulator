package operatingsystem;

import java.io.IOException;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Από την κλάση αυτή ξεκινά η εκτέλεση της εφαρμογής. Δημιουργεί το αρχείο με
τις διεργασίες, φορτώνει από αυτό όλες τις διεργασίες και προσομοιώνει την
δρομολόγηση με σειρά τους εξής αλγορίθμους: SJF preemptive, SJF non-preemptive,
Round Robin quantum=50, Round Robin quantum=300. Τέλος καταγράφει τα στατιστικά
από τις παραπάνω προσομοιώσεις και τα αποθηκεύει σε αρχείο. */
public class Main {

    public static Clock clock;
    public static CPU cpu;
    public static NewProcessTemporaryList newProcessList;
    public static ReadyProcessesList readyProcessesList;
    public static Statistics stats;

    /* Επιστρέφει true αν η cpu δεν έχει καμία διεργασία για εκτέλεση, 
    δεν υπάρχει καμία διεργασία στην ουρά έτοιμων διεργασιών και η ουρά νέων διεργασιών είναι άδεια. */
    public static boolean end() {
        return (cpu.getRunningProcess() == null) && (readyProcessesList.isEmpty()) && (newProcessList.isEmpty());
    }

    public static void main(String[] args) throws IOException {

        String inputFileName = "processes.txt"; //Αρχείο των στοιχείων των διεργασιών
        String outputFileName = "statistics.txt"; // Αρχείο στατιστικών εκτέλεσης
        
        ProcessGenerator processParse;        
        
        new ProcessGenerator(inputFileName, false); // Δημιουργία αρχείου εισόδου
        cpu = new CPU(); 
        newProcessList = new NewProcessTemporaryList();
        stats = new Statistics(outputFileName);
        clock = new Clock();
        readyProcessesList = new ReadyProcessesList();

        // ============== PREEMPTIVE SJF ==============//
        
        processParse = new ProcessGenerator(inputFileName, true); //Παραγωγή όλων των διεργασιών
        processParse.addProcessesToTemporayList(); // Προσθήκη των νέων διεργασιών στην ουρά νέων διεργασιών
        SJFScheduler sjfs = new SJFScheduler(true); // Προεκχωρίσιμος SJFS

        while (!end()) {
            sjfs.SJF(); //Δρομολόγηση διεργασίας 
            cpu.execute(); //Εκτέλεση διεργασίας 
        }

        //Εγγραφή των στατιστικών
        stats.WriteStatistics2File("Preemptive SJF"); 
        stats.printStatistics("Preemptive SJF"); 

        // ============== NON-PREEMPTIVE SJF ==============//
        
        clock.reset(); //Μηδενισμός ρολογιού
        stats.reset(); // Αρχικοποίηση παραμέτρων  αντικειμένου στατιστικών 
        
        processParse = new ProcessGenerator(inputFileName, true); //Παραγωγή όλων των διεργασιών
        processParse.addProcessesToTemporayList();  // Προσθήκη των νέων διεργασιών στην ουρά νέων διεργασιών
        sjfs.setIsPreemptive(false); // Μη- προεκχωρίσιμος SJFS

        while (!end()) {
            sjfs.SJF(); //Δρομολόγηση διεργασίας 
            cpu.execute(); //Εκτέλεση διεργασίας 
        }

        //Εγγραφή των στατιστικών
        stats.WriteStatistics2File("Non-Preemptive SJF");
        stats.printStatistics("Non-Preemptive SJF");

        // ============== RR WITH QUANTUM = 200 ==============//
        
        clock.reset(); //Μηδενισμός ρολογιού
        stats.reset(); // Αρχικοποίηση παραμέτρων  αντικειμένου στατιστικών 
        processParse = new ProcessGenerator(inputFileName, true); //Παραγωγή όλων των διεργασιών
        processParse.addProcessesToTemporayList();  // Προσθήκη των νέων διεργασιών στην ουρά νέων διεργασιών
        RRScheduler rrs = new RRScheduler(200); //Round Robin με quantum = 200

        while (!end()) {
            rrs.RR(); //Δρομολόγηση διεργασίας 
            cpu.execute(); //Εκτέλεση διεργασίας 
        }

         //Εγγραφή των στατιστικών
        stats.WriteStatistics2File("Round Robin with quantum = 200");
        stats.printStatistics("Round Robin with quantum = 200");

        // ============== RR WITH QUANTUM = 5000 ==============//
        
        clock.reset(); //Μηδενισμός ρολογιού
        stats.reset(); // Αρχικοποίηση παραμέτρων  αντικειμένου στατιστικών 
        
        processParse = new ProcessGenerator(inputFileName, true); //Παραγωγή όλων των διεργασιών
        processParse.addProcessesToTemporayList(); // Προσθήκη των νέων διεργασιών στην ουρά νέων διεργασιών
        rrs.setQuantum(5000); //Round Robin με quantum = 5000

        while (!end()) {
            rrs.RR(); //Δρομολόγηση διεργασίας 
            cpu.execute(); //Εκτέλεση διεργασίας 
        }

        //Εγγραφή των στατιστικών
        stats.WriteStatistics2File("Round Robin with quantum = 5000");
        stats.printStatistics("Round Robin with quantum = 5000");

    }

}