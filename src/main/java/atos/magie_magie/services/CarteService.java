/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.services;

import atos.magie_magie.dao.CarteDAOCrud;
import atos.magie_magie.entity.Carte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class CarteService implements ICarteService {
    
//    private CarteDAO dao = new CarteDAO();
    @Autowired
    private CarteDAOCrud daoCrud;
    
    @Override
    public Carte recupererCarteViaId (long carteId) {
        
        return daoCrud.findOne(carteId);
    }
    
}
