/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entités.Evaluation;
import Entités.Evenement;
import Entités.Participation;
import Entités.Utilisateur;
import Services.EvaluationService;
import Services.EvenementServices;
import Services.ParticipationService;
import Services.UserService;
import java.sql.Date;

/**
 *
 * @author boussandel
 */
public class Dhie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         /* String str="2015-03-31";  
    Date date=Date.valueOf(str);//converting string into sql date
       
     Services.EvenementServices es = new EvenementServices();

Evenement e = new Evenement("louay","dldl","dldld", date, "ddss","dlsds","smdds","lsdmdsm", 0, 0, 0); 
        
       es.modifierEvenement(e, 54);
     // es.supprimerEvenement(10);
     //es.ajouterEvenement(e);
        */
        
        
        //es.findAll();
        //Participation  p= new Participation(12,02,40,50); 
       // ParticipationServices ps = new ParticipationServices(); 
        //ps.ajouterParticipation(p);
        
        //EvaluationEvenement ee = new EvaluationEvenement(12,20,"dhia","eddine"); 
        //Services.EvaluationServices evs = new EvaluationServices(); 
        //evs.ajouter_Evaluation(ee);
       
/*
     Utilisateur u ; 
        u = new  Utilisateur("nomm","prenom","email","password","telephone","nationalite","role","login","photo");
           Services.UserService us = new UserService(); 
           us.add(u);
            us.delete(1);
           us.findById(1);
            us.getAll();
            us.authentication("dhia", "dhia") ; */
            
    /* Evenement e = new Evenement(5,"journee","dhdhdh",date , "jhhhhh","hdhdhdh","dhdhdhd","djdjhdhdh","dhdhdhdh",52,10,16) ; 
        Evenement e2 = new Evenement(15,"journee","dhdhdh",date , "jhhhhh","hdhdhdh","dhdhdhd","djdjhdhdh","dhdhdhdh",52,10,16) ;
       Services.EvenementServices es = new EvenementServices(); 
       es.ajouterEvenement(e2);*/
   Evaluation r = new Evaluation(34, 59, 0, "nomParticipant", "prenomParticipant", "emailParticipant", "nomEvenement");
       Services.EvaluationService see = new EvaluationService();
       see.ajouterEvaluation(r);
       see.evalueDéja(34);
       
     //  Services.EvaluationService ess2 = new EvaluationService(); 
       //ess2.moyByName("dhia");       
       /* Participation p = new Participation(10, 10);
        ParticipationService ps = new ParticipationService();
        ps.ajouterParticipation(p);*/
      //  ps.participeDéja(2);
        
        //Services.EvenementServices es = new EvenementServices(); 
        //es.decrementation_nbrPlaces(70,23);
        //es.decrEtChangementDetat(70);
        
        
      
    }
    
    }
    

