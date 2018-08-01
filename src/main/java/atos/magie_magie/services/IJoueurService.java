/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.services;

import atos.magie_magie.entity.Joueur;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrateur
 */
public interface IJoueurService {

    List afficherCartes(long joueurId);

    @Transactional
    List<Joueur> jeterUnSort(long partieId, Joueur joueurVictime, long idCarte1, long idCarte2, long carteEchangeeId);

    @Transactional
    void joueurSuivant(long partieId);

    @Transactional
    void passeSonTour(long partieId);

    List rechercherJoueurEtatPasLaMainEtSommeil(long partieId);

    Joueur recupJoueurQuiALaMain(long partieId);

    Joueur recupJoueurViaId(long joueurId);

    @Transactional
    Joueur rejoindrePartie(String pseudo, String avatar, long idPartie);

    List<Joueur> sortDivination(long partieId);

    @Transactional
    void sortHypnose(Joueur joueurVictime, Joueur joueurQuiALaMain, long carteEchangeeId);

    @Transactional
    void sortInvisibilite(long partieId, Joueur joueurQuiALaMain);

    @Transactional
    void sortPhiltreDAmour(Joueur joueurVictime, Joueur joueurQuiALaMain);

    @Transactional
    void sortSommeilProfond(Joueur joueurVictime);

    List tousLesJoueursDeLaPartie(long partieId);
    
}
