/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.servlet;

import atos.magie_magie.entity.Joueur;
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
@WebServlet(name = "RejoindrePartie", urlPatterns = {"/rejoindrePartie"})
public class RejoindrePartieServlet extends AutowireServlet {

    @Autowired
    private JoueurService service = new JoueurService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
               
        String pseudo = req.getParameter("nomJoueur");
        String avatar = req.getParameter("choixAvatar");
        
        long partieId = (long) req.getSession().getAttribute("idPartie");
              
        Joueur newJoueur = service.rejoindrePartie(pseudo, avatar, partieId );
        
        req.getSession().setAttribute("idJoueurMoi", newJoueur.getId());
        
        resp.sendRedirect("demarrerPartie");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("rejoindrePartie.jsp");
    }
    
    

}
