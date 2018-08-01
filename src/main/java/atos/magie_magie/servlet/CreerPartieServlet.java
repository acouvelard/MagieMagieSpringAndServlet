/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.servlet;

import atos.magie_magie.entity.Partie;
import atos.magie_magie.services.PartieService;
import atos.magie_magie.spring.AutowireServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "CreerPartie", urlPatterns = {"/creerPartie"})
public class CreerPartieServlet extends AutowireServlet {

    private PartieService service = new PartieService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String nom = req.getParameter("nomNouvellePartie");

        Partie newPartie = service.creerNouvelleParite(nom);

        req.getSession().setAttribute("idPartie", newPartie.getId());

        resp.sendRedirect("rejoindrePartie.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("creerNouvellePartie.jsp");
    }
    
    
    


}
