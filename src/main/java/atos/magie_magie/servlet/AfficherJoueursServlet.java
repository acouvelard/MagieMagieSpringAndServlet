/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.servlet;

import atos.magie_magie.services.JoueurService;
import atos.magie_magie.spring.AutowireServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "afficherJoueursServlet", urlPatterns = {"/afficherJoueurs"})
public class AfficherJoueursServlet extends AutowireServlet {

    @Autowired
    private JoueurService service = new JoueurService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long partieId = (long) req.getSession().getAttribute("idPartie");

        req.setAttribute("afficherJoueurs", service.tousLesJoueursDeLaPartie(partieId));
        
        req.getRequestDispatcher("plateauJeu.jsp").forward(req, resp);
    }
/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        long idJoueur = Long.parseLong(req.getParameter("joueursEnnemis"));
        
        service.recupJoueurViaId(idJoueur);
        
    }
    
 */   
    
    
}
