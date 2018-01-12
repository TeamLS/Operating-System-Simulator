/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operatingsystem;

import java.util.Comparator;

/**
 *
 * @author User
 */
public class ArrivalTimeComparator implements Comparator {

    @Override
    public int compare(Object proc, Object proc2) {
        
        Process pr = (Process) proc;
        Process pr2 =  (Process) proc2;
        
        return  pr2.getArrivalTime()- pr.getArrivalTime() ;
      
    }
    
}
