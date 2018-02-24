/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entités.Evaluation;
import Entités.Participation;
import java.util.List;

/**
 *
 * @author boussandel
 */
public interface IEvaluation {
    
    public void ajouterEvaluation(Evaluation e) ;
    public List<Evaluation> findAll();
    public double moyByName(int id);
    public List<Evaluation> evalueDéja(int id);
    
}
