/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.servlet;

import atos.magie_magie.entity.Partie;
import atos.magie_magie.entity.Partie_;
import atos.magie_magie.services.PartieService;
import atos.magie_magie.spring.AutowireServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "ListeParties", urlPatterns = {"/listeParties"})
public class ListePartiesServlet extends AutowireServlet {
    
    @Autowired
    private PartieService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Partie> parties = service.listerPartiesNonDemarrees();
        
        req.setAttribute("listeParties", parties);
        
        req.getRequestDispatcher("listerParties.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.getSession().setAttribute("idPartie", Partie_.id);
        resp.sendRedirect("rejoindrePartie");
    }
    
}

