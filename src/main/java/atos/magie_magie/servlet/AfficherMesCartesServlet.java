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
@WebServlet(name = "AfficherMesCartesServlet", urlPatterns = {"/afficherCartes"})
public class AfficherMesCartesServlet extends AutowireServlet {
    
    @Autowired
    private JoueurService service = new JoueurService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long joueurId = (long) req.getSession().getAttribute("idJoueurMoi");
        
        req.setAttribute("mesCartes", service.afficherCartes(joueurId));
        
        req.getRequestDispatcher("plateauJeu.jsp").forward(req, resp);
        
    }
}
