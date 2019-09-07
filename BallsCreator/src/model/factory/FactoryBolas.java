/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factory;

import model.bola.Bola;
import model.bola.IBola;
import model.movible.BolaMovible;
import model.movible.BolaMovible.Direccion;
import model.punto.Punto;

/**
 *
 * @author aborbon
 */
public class FactoryBolas {

    public static IBola construirBola(Punto centro, int radio) {
        return new Bola(centro, radio);
    }

    public static IBola construirBola(int xCentro, int yCentro, int radio) {
        return new Bola(FactoryPuntos.crearPunto(xCentro, yCentro), radio);
    }

    public static IBola construirBolaMovible(int xCentro, int yCentro, int radio, int velocidad, Direccion direccion) {
        return new BolaMovible(FactoryPuntos.crearPunto(xCentro, yCentro), radio, velocidad, direccion);
    }
    
}
