/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dibujable;

import java.awt.Color;
import model.factory.FactoryPuntos;
import model.movible.BolaMovible;
import model.bola.IBola;
import model.punto.Punto;

/**
 *
 * @author aborbon
 */
public class FactoryBolaMovibleDibujable implements IFactoryBolaMovibleDibujable {

    @Override
    public IBola construirBolaMovibleDibujable(Punto centro, int radio, int velocidad, BolaMovible.Direccion direccion, Color color) {
        return new BolaMovibleDibujable(centro, radio, velocidad, direccion, color);
    }

    @Override
    public IBola construirBolaMovibleDibujable(int xCentro, int yCentro, int radio, int velocidad, BolaMovible.Direccion direccion, Color color) {
        return new BolaMovibleDibujable(FactoryPuntos.crearPunto(xCentro, yCentro), radio, velocidad, direccion, color);
    }
    
}
