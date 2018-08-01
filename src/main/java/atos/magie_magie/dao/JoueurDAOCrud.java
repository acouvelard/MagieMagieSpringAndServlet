/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.dao;

import atos.magie_magie.entity.Joueur;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface JoueurDAOCrud extends CrudRepository<Joueur, Long>{
    
    public Joueur findByPseudo(String pseudo);
    
    /**
     * Rechercher tous les joueurs pour une partie
     * @param partieId
     * @return 
     */
    public List<Joueur> findByPartieActuelleId(long partieId);
    
    @Query("SELECT MAX (j.ordre)+1 FROM Joueur j JOIN j.partieActuelle p WHERE p.id=?1")
    public long trouverOrdreNouveauJoueurParPartieId(long partieId);
    
    @Query("SELECT j FROM Joueur j JOIN j.partieActuelle pa WHERE pa.id = ?1 AND j.etat = atos.magie_magie.entity.Joueur.EtatJoueur.N_A_PAS_LA_MAIN AND j.etat = atos.magie_magie.entity.Joueur.EtatJoueur.SOMMEIL_PROFOND")
    public List<Joueur> trouverJoueursEtatPasLaMainEtSommeilProfond(long partieId);
    
    public Joueur findByOrdreAndPartieActuelleId(long ordre, long partieId);
    
    @Query("SELECT MAX(j.ordre) FROM Joueur j JOIN j.partieActuelle pa WHERE pa.id = ?1")
    public long trouverOrdreMaxDesJoueurs(long partieId);
    
    @Query("SELECT COUNT(j.id) FROM Joueur j JOIN j.partieActuelle pa WHERE pa.id = ?1 AND j.etat = atos.magie_magie.entity.Joueur.EtatJoueur.PERDU")
    public long trouverNbJoueurEtatPerdu(long partieId);
    
    @Query("SELECT j FROM Joueur j JOIN j.partieActuelle pa WHERE pa.id = ?1 AND j.etat = atos.magie_magie.entity.Joueur.EtatJoueur.A_LA_MAIN ")
    public Joueur trouverjoueurQuiALaMain(long partieId);
    
    public long countByPartieActuelleId(long partieId);
    
}
