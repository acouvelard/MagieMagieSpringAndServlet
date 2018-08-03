/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.controller;

import atos.magie_magie.services.PartieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrateur
 */
@Controller
public class PartieController {
    
    @Autowired
    private PartieService service;
    
    @RequestMapping(value = {"/liste-parties", "/"}, method = RequestMethod.GET)
    public String listerPartiesNonDemarree(Model model) {
        
        model.addAttribute("partieNonDemarree", service.listerPartiesNonDemarrees());
        
        return "listerParties.jsp";
    }
    
    @RequestMapping(value = "/rejoindrePartie/{PartieId}", method = RequestMethod.GET)
    public String rejoindrePartieVue(@PathVariable("PatieId") long id){
        
        //mettre partie id en session ?
        
        return "rejoindrePartie.jsp";
    }
}
