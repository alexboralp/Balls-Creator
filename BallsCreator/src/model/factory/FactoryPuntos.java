/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factory;

import model.punto.Punto;

/**
 *
 * @author aborbon
 */
public class FactoryPuntos {

    public static Punto crearPunto(int x, int y) {
        return new Punto(x, y);
    }
    
}
