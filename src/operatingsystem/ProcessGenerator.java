package operatingsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// Χρίστος Γκόγκος (2738), Αθανάσιος Μπόλλας (2779), Δημήτριος Σβίγγας (2618), Αναστάσιος Τεμπερεκίδης (2808)

/* Η συγκεκριμένη κλάση αναπαριστά μια γεννήτρια διεργασιών για την προσομοίωση */
class ProcessGenerator {

    /* αρχείο που αποθηκεύονται τα δεδομένα των νέων διεργασιών */
    private BufferedReader reader;
    private BufferedWriter writer;
    private File inputFile;
    private ArrayList<Process> processes;
    private final Random rand = new Random();
    private int n;
    private int prev = 0; //Ο χρόνος άφοιξης της προηγούμενηυς διεργασίας. Την χρονική στιγμή 0 δεν ήρθε καμία διεργασία.

    private final int cpuBurstTime = 60000; //60000 μπαίνει για να διαρκεί μια διεργασία από 0 εως 1 λεπτό. (60000 millisecond == 1 λεπτό).
    private final int cpuArrivalTime = 2000; //2000 είναι τα περισσότερα millisecond "διαφοράς άφοιξης" που μπορεί να έχει η μια διεργασία από την προηγούμενη.

    /* constructor της κλάσης; αν readFile == false δημιουργεί το αρχείο inputFile με όνομα
    filename για αποθήκευση, αλλιώς ανοίγει το αρχείο inputFile για ανάγνωση */
    public ProcessGenerator(String filename, boolean readFile) throws IOException {

        inputFile = new File(filename);

        if (readFile == false) {

            inputFile.createNewFile();
            inputFile.setReadable(true);
            inputFile.setWritable(true);

            n = rand.nextInt(200) + 50; //Δημιουργεί τυχαία από 50 εως 250 διεργασίες.

            writer = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < n; i++) {
                StoreProcessToFile();
            }
            writer.close();

        } else {

            if (inputFile.exists() == false) {
                System.err.println("Error: file " + inputFile.getName() + " not found");
                System.exit(2);
            }

            reader = new BufferedReader(new FileReader(filename));

            processes = new ArrayList<>();
            processes = parseProcessFile();

            reader.close();
            
        }

    }

    /* δημιουργία μιας νέας διεργασίας με (ψευδο-)τυχαία χαρακτηριστικά */
    public Process createProcess(int pid) {

        prev = (rand.nextInt(cpuArrivalTime)) + prev;
        return new Process(pid, prev, rand.nextInt(cpuBurstTime));
    }

    /* αποθήκευση των στοιχείων της νέας διεργασίας στο αρχείο inputFile */
    public void StoreProcessToFile() throws IOException {

        prev += (rand.nextInt(cpuArrivalTime)); //Το cpuArrivalTime είναι σταθερό στα 2 δευτερόλεπτα, δηλαδή μια νέα διεργασία δεν θα αργεί να φτάσει στο σύστημα πάνω από 2 δευτερόλεπτα από την προηγούμενη.
        writer.write(Integer.toString(prev)); //Χρόνος άφοιξης.
        writer.newLine();

        writer.write(Integer.toString(rand.nextInt(cpuBurstTime))); //Το cpuBurstTime είναι σταθερό στα 60 δευτερόλεπτα, δηλαδή μια διεργασία δεν θα μπορεί να χρειαστή την cpu για πάνω από 1 λεπτό.
        writer.newLine();

    }

    /* ανάγνωση των στοιχείων νέων διεργασιών από το αρχείο inputFile */
    public ArrayList<Process> parseProcessFile() throws FileNotFoundException, IOException {

        int temp = 1;
        String nextArrivalTime = reader.readLine();
        String nextCpuBurstTime = reader.readLine();

        if ((nextArrivalTime != null) && (nextCpuBurstTime != null)) {
            do {
                processes.add(new Process(temp, Integer.parseInt(nextArrivalTime), Integer.parseInt(nextCpuBurstTime)));
                temp++;
                nextArrivalTime = reader.readLine();
                nextCpuBurstTime = reader.readLine();
            } while ((nextArrivalTime != null) && (nextCpuBurstTime != null));
        }

        return processes; //Τοποθέτηση των νέων διεργασιών στην λίστα.
        
    }

    public void addProcessesToTemporayList() {
        for (Process proc : processes) {
            Main.newProcessList.addNewProcess(proc);
        }
    }

}
