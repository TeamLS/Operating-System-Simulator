/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* Η συγκεκριμένη κλάση αναπαριστά μια γεννήτρια διεργασιών για την προσομοίωση */
public class ProcessGenerator {

    /* αρχείο που αποθηκεύονται τα δεδομένα των νέων διεργασιών */
    private File inputFile;

    /* constructor της κλάσης; αν readFile == false δημιουργεί το αρχείο inputFile με όνομα
filename για αποθήκευση, αλλιώς ανοίγει το αρχείο inputFile για ανάγνωση */
    public ProcessGenerator(String filename, boolean readFile) {
    }

    /* δημιουργία μιας νέας διεργασίας με (ψευδο-)τυχαία χαρακτηριστικά */
    public Process createProcess() {

        //create process  ( with Random() )
        return new Process(1, 2, 3);
    }

    /* αποθήκευση των στοιχείων της νέας διεργασίας στο αρχείο inputFile */
    public void StoreProcessToFile() {
    }

    /* ανάγνωση των στοιχείων νέων διεργασιών από το αρχείο inputFile */
    public List<Process> parseProcessFile() {
        return new ArrayList<>();
    }
}
