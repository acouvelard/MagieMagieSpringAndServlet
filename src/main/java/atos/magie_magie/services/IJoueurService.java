/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.services;

import atos.magie_magie.entity.Joueur;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public interface IJoueurService {

    public List afficherCartes(long joueurId);
    public List<Joueur> jeterUnSort(long partieId, Joueur joueurVictime, long idCarte1, long idCarte2, long carteEchangeeId);
    public void joueurSuivant(long partieId);
    public void passeSonTour(long partieId);
    public List rechercherJoueurEtatPasLaMainEtSommeil(long partieId);
    public Joueur recupJoueurQuiALaMain(long partieId);
    public Joueur recupJoueurViaId(long joueurId);
    public Joueur rejoindrePartie(String pseudo, String avatar, long idPartie);
    public List<Joueur> sortDivination(long partieId);
    public void sortHypnose(Joueur joueurVictime, Joueur joueurQuiALaMain, long carteEchangeeId);
    public void sortInvisibilite(long partieId, Joueur joueurQuiALaMain);
    public void sortPhiltreDAmour(Joueur joueurVictime, Joueur joueurQuiALaMain);
    public void sortSommeilProfond(Joueur joueurVictime);
    public List tousLesJoueursDeLaPartie(long partieId);
    
}
