/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package russie2018;

import java.sql.Date;
import entities.* ;
import service.*;
/**
 *
 * @author melek
 */
public class Russie2018 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Stade s1 = new Stade("Stade Krestovski","Saint-Pétersbourg","Futbol'naya Alleya, 1, Sankt-Peterburg, Russie, 197110",new Date(2016,1,1),"gazon",56196,"image.png");
       Stade s2 = new Stade("Stade Loujniki","Moscou","Loujetskaïa naberejnaïa Moscou 119048",new Date(1956,7,31),"gazon",81000,"image.png");
       Stade s3 = new Stade("Otkrytie Arena","Moscou","Volokolamskoye sh., 69, Moskva, Russie, 125424",new Date(2007,6,2),"gazon",45360,"image.png");
       Stade s5 = new Stade(3,"Stade olympique Ficht","Sotchi","Olympic Ave, Adler, Krasnodarskiy kray, Russie, 354340",new Date(2014,2,7),"gazon",40000,"image.png");       
      // StadeService.insererStade(s1);
      // StadeService.insererStade(s2);
      // StadeService.insererStade(s3);
      

      StadeService.DeletStadeByNom("Stade Krestovski");
        
        
       // StadeService.updateStade(s5);
       
       StadeService.selectStade().forEach((s) -> {
            System.out.println(s);
        });
      // Ticket t1 =new Ticket(50000,"Stade Krestovski","Tunisie vs Angleterre",new Date(2016,1,1),"gradin",4000);
      // TicketService.insererTicket(t1);
      // TicketService.DeletTicketByRef(1);
       
       TicketService.selectTicket().forEach((t) -> {
            System.out.println(t);
        });
    }
    
}
