/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.dao;

import atos.magie_magie.entity.Partie;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */

public interface PartieDAOCrud extends CrudRepository<Partie, Long>{
    
    /**
     * Liste des parties dont aucun joueur n'est à l'état A_LA_MAIN ou GAGNE.
     * @return 
     */
    @Query("SELECT p FROM Partie p EXCEPT SELECT p FROM Partie p JOIN p.joueurs j WHERE j.etat = atos.magie_magie.entity.Joueur.EtatJoueur.A_LA_MAIN OR j.etat = atos.magie_magie.entity.Joueur.EtatJoueur.GAGNE")
    public List<Partie> listerPartieNonDemarrees();

}
