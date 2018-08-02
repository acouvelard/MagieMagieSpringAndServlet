/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.services;

import atos.magie_magie.entity.Joueur;
import atos.magie_magie.entity.Partie;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public interface IParteiService {

    public Partie creerNouvelleParite(String nom);
    public Partie demarrerPartie(long partieId);
    public Joueur finDePartie(long partieId);
    public List<Partie> listerPartiesNonDemarrees();
    
}
