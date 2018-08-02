/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magie_magie.services;

import atos.magie_magie.entity.Carte;

/**
 *
 * @author Administrateur
 */
public interface ICarteService {

    public Carte recupererCarteViaId(long carteId);
    
}
