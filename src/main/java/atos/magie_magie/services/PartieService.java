/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.services;

import atos.magie_magie.dao.CarteDAOCrud;
import atos.magie_magie.dao.JoueurDAOCrud;
import atos.magie_magie.dao.PartieDAOCrud;
import atos.magie_magie.entity.Carte;
import atos.magie_magie.entity.Joueur;
import atos.magie_magie.entity.Partie;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrateur
 */
@Service
public class PartieService implements IParteiService {

    @Autowired
    private PartieDAOCrud daoCrud;
//    private PartieDAO dao = new PartieDAO();
    @Autowired
    private JoueurDAOCrud joueurDaoCrud;
//    private JoueurDAO joueurDAO = new JoueurDAO();
    @Autowired
    private CarteDAOCrud carteDaoCrud;
//    private CarteDAO carteDAO = new CarteDAO();
    
    @Transactional
    @Override
    public Joueur finDePartie (long partieId) {
        List<Joueur> joueurs = joueurDaoCrud.findByPartieActuelleId(partieId);
        
        List<Joueur> joueursEnAttente = joueurDaoCrud.trouverJoueursEtatPasLaMainEtSommeilProfond(partieId);
        
        Joueur joueurGagnant = null;
        
        if (joueursEnAttente.size() == 0) {
            for (Joueur joueur : joueursEnAttente) {
                joueur.setNbPartiesJouees(joueur.getNbPartiesJouees() + 1);
                if (joueur.getEtat() == Joueur.EtatJoueur.A_LA_MAIN) {
                    joueur.setEtat(Joueur.EtatJoueur.GAGNE);
                    joueur.setNbPartiesGagnees(joueur.getNbPartiesGagnees() + 1);
                    joueurGagnant = joueur;
                }
                joueurDaoCrud.save(joueur);
            }
        }
        return joueurGagnant;
    }

    @Transactional
    @Override
    public Partie demarrerPartie(long partieId) {

        Partie partieQuiDemarre = daoCrud.findOne(partieId);

        for (Joueur joueur : partieQuiDemarre.getJoueurs()) {

            if (joueur.getOrdre() == 1) {
                joueur.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
                joueurDaoCrud.save(joueur);
            }

            for (int i = 0; i < 7; i++) {
                Carte nouvelleCarte = new Carte();
                Carte.Ingredient[] ingredients = Carte.Ingredient.values();

                int carteRand = new Random().nextInt(ingredients.length);
                nouvelleCarte.setIngre(ingredients[carteRand]);
                nouvelleCarte.setJoueurProprietaire(joueur);
                joueur.getCartes().add(nouvelleCarte);
                carteDaoCrud.save(nouvelleCarte);
            }
        }

        return partieQuiDemarre;
    }

    @Override
    public List<Partie> listerPartiesNonDemarrees() {

        return daoCrud.listerPartieNonDemarrees();
    }

    @Transactional
    @Override
    public Partie creerNouvelleParite(String nom) {

        Partie p = new Partie();
        p.setNom(nom);
        daoCrud.save(p);

        return p;
    }

}
