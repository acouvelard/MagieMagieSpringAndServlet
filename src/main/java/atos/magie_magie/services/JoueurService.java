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
public class JoueurService implements IJoueurService {

//    private JoueurDAO dao = new JoueurDAO();
    @Autowired
    private JoueurDAOCrud daoCrud;
//    private PartieDAO partieDAO = new PartieDAO();
    @Autowired
    private PartieDAOCrud partieDaoCrud;
//    private CarteDAO carteDAO = new CarteDAO();
    @Autowired
    private CarteDAOCrud carteDaoCrud;
    
    @Override
    public List rechercherJoueurEtatPasLaMainEtSommeil(long partieId) {
        
        return daoCrud.trouverJoueursEtatPasLaMainEtSommeilProfond(partieId);
    }
    
    @Override
    public List tousLesJoueursDeLaPartie (long partieId) {
        
        return daoCrud.findByPartieActuelleId(partieId);
    }
    
    @Override
    public Joueur recupJoueurViaId (long joueurId) {
        
        return daoCrud.findOne(joueurId);
    }
    
    @Transactional
    @Override
    public void sortInvisibilite (long partieId, Joueur joueurQuiALaMain) {
        
        List<Joueur> joueurs = daoCrud.trouverJoueursEtatPasLaMainEtSommeilProfond(partieId);
        
        for (Joueur joueur : joueurs) {
            int r = new Random().nextInt(joueur.getCartes().size());
            Carte cartePrise = joueur.getCartes().get(r);
            joueur.getCartes().remove(cartePrise);
            daoCrud.save(joueur);
            cartePrise.setJoueurProprietaire(joueurQuiALaMain);
            carteDaoCrud.save(cartePrise);
            joueurQuiALaMain.getCartes().add(cartePrise);
            daoCrud.save(joueurQuiALaMain);


            if (joueur.getCartes().size() == 0) {
            joueur.setEtat(Joueur.EtatJoueur.PERDU);
            daoCrud.save(joueur);
        }
        }
        
        
    }
    
    @Transactional
    @Override
    public void sortPhiltreDAmour (Joueur joueurVictime, Joueur joueurQuiALaMain ) {
        
        List<Carte> cartesDeLaVictime = joueurVictime.getCartes();
        if (cartesDeLaVictime.size() == 1) {
            joueurQuiALaMain.getCartes().add((Carte) cartesDeLaVictime);
            cartesDeLaVictime.remove(cartesDeLaVictime);
            return;
        } 
        
        int moitiéDesCartesquiReste;
        if (cartesDeLaVictime.size()%2 == 0) {
            moitiéDesCartesquiReste = cartesDeLaVictime.size()/2;
        } else {
            moitiéDesCartesquiReste = cartesDeLaVictime.size()/2 + (1/2);
        }
        
        while (cartesDeLaVictime.size() != moitiéDesCartesquiReste) {            
            int r = new Random().nextInt(cartesDeLaVictime.size());
            Carte cartePrise = cartesDeLaVictime.get(r);
            cartesDeLaVictime.remove(cartePrise);
            daoCrud.save(joueurVictime);
            cartePrise.setJoueurProprietaire(joueurQuiALaMain);
            carteDaoCrud.save(cartePrise);
            joueurQuiALaMain.getCartes().add(cartePrise);
            daoCrud.save(joueurQuiALaMain);

        }
    }
    
    @Transactional
    @Override
    public void sortHypnose (Joueur joueurVictime, Joueur joueurQuiALaMain, long carteEchangeeId) {
        
        if (joueurVictime.getCartes().size() < 3) {
            List<Carte> cartes = joueurVictime.getCartes();
            for (Carte carte : cartes) {
                joueurVictime.getCartes().remove(carte);
                daoCrud.save(joueurVictime);    
                carte.setJoueurProprietaire(joueurQuiALaMain);
                carteDaoCrud.save(carte);
            }
            return;
        }
     
        
        for (int i = 0; i < 3; i++) {
            int r = new Random().nextInt(joueurVictime.getCartes().size());
            Carte cartePrise = joueurVictime.getCartes().get(r);
            joueurVictime.getCartes().remove(cartePrise);
            daoCrud.save(joueurVictime);
            cartePrise.setJoueurProprietaire(joueurQuiALaMain);
            carteDaoCrud.save(cartePrise);
            joueurQuiALaMain.getCartes().add(cartePrise);
            daoCrud.save(joueurQuiALaMain);
        }           


        
        Carte carteEchangee = carteDaoCrud.findOne(carteEchangeeId);
        
        joueurQuiALaMain.getCartes().remove(carteEchangee);
        daoCrud.save(joueurQuiALaMain);
        carteEchangee.setJoueurProprietaire(joueurVictime);
        carteDaoCrud.save(carteEchangee);
        joueurVictime.getCartes().add(carteEchangee);
        daoCrud.save(joueurVictime);


    }
    
    @Override
    public List<Joueur> sortDivination (long partieId) {
        
        List<Joueur> joueurs = daoCrud.trouverJoueursEtatPasLaMainEtSommeilProfond(partieId);
        
        return joueurs;
    }
    
    @Transactional
    @Override
    public void sortSommeilProfond (Joueur joueurVictime) {
        
        joueurVictime.setEtat(Joueur.EtatJoueur.SOMMEIL_PROFOND);
        daoCrud.save(joueurVictime);
        
    }

    @Transactional
    @Override
    public List<Joueur> jeterUnSort (long partieId, Joueur joueurVictime, long idCarte1, long idCarte2, long carteEchangeeId ) {
        
        Joueur joueurQuiALaMain = daoCrud.trouverjoueurQuiALaMain(partieId);
       
        Carte carte1 = carteDaoCrud.findOne(idCarte1);
        Carte carte2 = carteDaoCrud.findOne(idCarte2);

        List<Joueur> listJoueurs = null;
        
        //cartes correspondent à celle d'un sort
        if ((carte1.getIngre() == Carte.Ingredient.CORNE_DE_LICORNE && carte2.getIngre() == Carte.Ingredient.BAVE_DE_CRAPAUX)
            ||(carte2.getIngre() == Carte.Ingredient.CORNE_DE_LICORNE && carte1.getIngre() == Carte.Ingredient.BAVE_DE_CRAPAUX)) {
            sortInvisibilite(partieId, joueurQuiALaMain);
        } else if ((carte1.getIngre() == Carte.Ingredient.CORNE_DE_LICORNE && carte2.getIngre() == Carte.Ingredient.MANDRAGORE)
            ||(carte2.getIngre() == Carte.Ingredient.CORNE_DE_LICORNE && carte1.getIngre() == Carte.Ingredient.MANDRAGORE)) {
            sortPhiltreDAmour(joueurVictime, joueurQuiALaMain);
        } else if ((carte1.getIngre() == Carte.Ingredient.BAVE_DE_CRAPAUX && carte2.getIngre() == Carte.Ingredient.LAPIS_LAZULI)
            ||(carte2.getIngre() == Carte.Ingredient.BAVE_DE_CRAPAUX && carte1.getIngre() == Carte.Ingredient.LAPIS_LAZULI)) {
            sortHypnose(joueurVictime, joueurQuiALaMain, carteEchangeeId);
        } else if ((carte1.getIngre() == Carte.Ingredient.LAPIS_LAZULI && carte2.getIngre() == Carte.Ingredient.AILE_DE_CHAUVE_SOURIE )
            || (carte2.getIngre() == Carte.Ingredient.LAPIS_LAZULI && carte1.getIngre() == Carte.Ingredient.AILE_DE_CHAUVE_SOURIE )) {
            listJoueurs = sortDivination(partieId);
        } else if ((carte1.getIngre() == Carte.Ingredient.MANDRAGORE && carte2.getIngre() == Carte.Ingredient.AILE_DE_CHAUVE_SOURIE)
            ||(carte2.getIngre() == Carte.Ingredient.MANDRAGORE && carte1.getIngre() == Carte.Ingredient.AILE_DE_CHAUVE_SOURIE)) {
            sortSommeilProfond(joueurVictime);
        } else {
            System.out.println("Vous n'avez pas selectionné les bonnes cartes. Vous les avez perdues.");;
        }
        
        //test si joueurVictime à encore des cartes
        if (joueurVictime.getCartes().size() == 0) {
            joueurVictime.setEtat(Joueur.EtatJoueur.PERDU);
            daoCrud.save(joueurVictime);
        }
        
        //On retire les deux cartes utilisées
        joueurQuiALaMain.getCartes().remove(carte1);
        daoCrud.save(joueurQuiALaMain);
        carteDaoCrud.delete(carte1.getId());
        joueurQuiALaMain.getCartes().remove(carte2);
        daoCrud.save(joueurQuiALaMain);
        carteDaoCrud.delete(carte2.getId());
        

        joueurSuivant(partieId);
        
        return listJoueurs;

    }

    @Transactional
    @Override
    public void passeSonTour(long partieId) {
        
        Joueur joueur = daoCrud.trouverjoueurQuiALaMain(partieId);
 
        Carte nouvelleCarte = new Carte();
        Carte.Ingredient[] ingredients = Carte.Ingredient.values();
        int carteRand = new Random().nextInt(ingredients.length);
        nouvelleCarte.setIngre(ingredients[carteRand]);
        nouvelleCarte.setJoueurProprietaire(joueur);
        joueur.getCartes().add(nouvelleCarte);
        carteDaoCrud.save(nouvelleCarte);
     
        joueurSuivant(partieId);
        
    }
    
    @Transactional
    @Override
    public void joueurSuivant(long partieId) {
       
        Partie partie = partieDaoCrud.findOne(partieId);
        Joueur joueurQuiALaMain = recupJoueurQuiALaMain(partieId);

        if (daoCrud.trouverNbJoueurEtatPerdu(partieId) == daoCrud.countByPartieActuelleId(partieId) - 1) {
            joueurQuiALaMain.setEtat(Joueur.EtatJoueur.GAGNE); 
            daoCrud.save(joueurQuiALaMain);//partie finie !
            return;
        }

        long ordreMax = daoCrud.trouverOrdreMaxDesJoueurs(partieId);

        Joueur joueurEvalue = joueurQuiALaMain;
        
        //boucle
        while (true) {

            if (joueurEvalue.getOrdre() >= ordreMax) {
                joueurEvalue = daoCrud.findByOrdreAndPartieActuelleId(1, partieId);
            } else {
                joueurEvalue = daoCrud.findByOrdreAndPartieActuelleId(joueurEvalue.getOrdre()+1, partieId);
            }
            
            if (joueurEvalue.getId() == joueurQuiALaMain.getId()) {
                return;
            }

            if (joueurEvalue.getEtat() == Joueur.EtatJoueur.SOMMEIL_PROFOND) {
                joueurEvalue.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                daoCrud.save(joueurEvalue);
            } else if (joueurEvalue.getEtat() == Joueur.EtatJoueur.N_A_PAS_LA_MAIN) {
                joueurQuiALaMain.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                daoCrud.save(joueurQuiALaMain);
                joueurEvalue.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
                daoCrud.save(joueurEvalue);
                return;
            }
        }
    }

    @Override
    public Joueur recupJoueurQuiALaMain(long partieId) {

        Joueur joueur = daoCrud.trouverjoueurQuiALaMain(partieId);

        return joueur;
    }


    @Override
    public List afficherCartes(long joueurId) {

        return daoCrud.findOne(joueurId).getCartes();
    }

    @Transactional
    @Override
    public Joueur rejoindrePartie(String pseudo, String avatar, long idPartie) {

        // Recherche si joueur existe déjà
        Joueur joueur = daoCrud.findByPseudo(pseudo);

        if (joueur == null) {
            //Le joueur n'existe pas encore
            joueur = new Joueur();
            joueur.setPseudo(pseudo);
        }

        joueur.setAvatar(avatar);
        joueur.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
        long ordreJoueur = daoCrud.trouverOrdreNouveauJoueurParPartieId(idPartie);
        if(ordreJoueur == 0) {
            ordreJoueur = 1;
        }
        joueur.setOrdre(ordreJoueur);

        //Associe le joueur à la partie et vise-versa
        Partie partie = partieDaoCrud.findOne(idPartie);
        joueur.setPartieActuelle(partie);
        List<Joueur> listJoueurs = partie.getJoueurs();
        listJoueurs.add(joueur);

        daoCrud.save(joueur);

        return joueur;

    }

}
