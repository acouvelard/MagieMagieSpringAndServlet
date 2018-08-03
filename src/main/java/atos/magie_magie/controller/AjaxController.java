/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.controller;

import atos.magie_magie.dto.JoueurQuiALaMainDTO;
import atos.magie_magie.services.JoueurService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrateur
 */
@Controller
public class AjaxController {
    
    @Autowired
    private JoueurService service;
    
    @RequestMapping(value = "/ajax-qui-a-la-main", method = RequestMethod.GET)
    @ResponseBody
    public JoueurQuiALaMainDTO determineJoueurQuiALaMain(HttpSession session) {
        
        //session.getAttribute("");
        
        JoueurQuiALaMainDTO dto = new JoueurQuiALaMainDTO();
        dto.setIdJoueur(1L);
        dto.setNomJoueur("Joueur1");
        
        //service.recupJoueurQuiALaMain(0);
        
        return dto;
        
    }
}
