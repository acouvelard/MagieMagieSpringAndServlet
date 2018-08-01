/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.services;

import atos.magie_magie.dao.CarteDAO;
import atos.magie_magie.dao.JoueurDAO;
import atos.magie_magie.dao.PartieDAO;
import atos.magie_magie.dao.PartieDAOCrud;
import atos.magie_magie.entity.Carte;
import atos.magie_magie.entity.Joueur;
import atos.magie_magie.entity.Partie;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class PartieService {

    @Autowired
    private PartieDAOCrud daoCrud;
    private PartieDAO dao = new PartieDAO();
    private JoueurDAO joueurDAO = new JoueurDAO();
    private CarteDAO carteDAO = new CarteDAO();
    
    public Joueur finDePartie (long partieId) {
        List<Joueur> joueurs = joueurDAO.rechercheTousLesJoueursPourUnePartie(partieId);
        
        List<Joueur> joueursEnAttente = joueurDAO.rechercherJoueursPasLaMainEtSommeil(partieId);
        
        Joueur joueurGagnant = null;
        
        if (joueursEnAttente.size() == 0) {
            for (Joueur joueur : joueursEnAttente) {
                joueur.setNbPartiesJouees(joueur.getNbPartiesJouees() + 1);
                if (joueur.getEtat() == Joueur.EtatJoueur.A_LA_MAIN) {
                    joueur.setEtat(Joueur.EtatJoueur.GAGNE);
                    joueur.setNbPartiesGagnees(joueur.getNbPartiesGagnees() + 1);
                    joueurGagnant = joueur;
                }
                joueurDAO.modifier(joueur);
            }
        }
        return joueurGagnant;
    }

    public Partie demarrerPartie(long partieId) {

        Partie partieQuiDemarre = dao.rechercherParId(partieId);

        for (Joueur joueur : partieQuiDemarre.getJoueurs()) {

            if (joueur.getOrdre() == 1) {
                joueur.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
                joueurDAO.modifier(joueur);
            }

            for (int i = 0; i < 7; i++) {
                Carte nouvelleCarte = new Carte();
                Carte.Ingredient[] ingredients = Carte.Ingredient.values();

                int carteRand = new Random().nextInt(ingredients.length);
                nouvelleCarte.setIngre(ingredients[carteRand]);
                nouvelleCarte.setJoueurProprietaire(joueur);
                joueur.getCartes().add(nouvelleCarte);
                carteDAO.ajouterCarte(nouvelleCarte);
            }
        }

        return partieQuiDemarre;
    }

    public List<Partie> listerPartiesNonDemarrees() {

        return daoCrud.listerPartieNonDemarrees();
    }

    public Partie creerNouvelleParite(String nom) {

        Partie p = new Partie();
        p.setNom(nom);
        dao.ajouter(p);

        return p;
    }

}
