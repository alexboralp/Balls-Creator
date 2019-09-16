/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.builder;

import ballscreator.dibujable.BolaMovibleDibujable;
import java.awt.Color;
import model.movible.BolaMovible.Direccion;
import model.punto.Punto;

/**
 *
 * @author oscar
 */
public final class BallBuilder implements IBuilder<BolaMovibleDibujable> {
    
    private Punto centro;
    private int radio;
    private int velocidad;
    private Direccion direccion;
    private Color color;

    public BallBuilder() {
    }

    public BallBuilder setCentro(Punto centro) {
        this.centro = centro;
        return this;
    }

    public BallBuilder setRadio(int radio) {
        this.radio = radio;
        return this;
    }

    public BallBuilder setVelocidad(int velocidad) {
        this.velocidad = velocidad;
        return this;
    }

    public BallBuilder setDireccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public BallBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    @Override
    public BolaMovibleDibujable build() {
        return new BolaMovibleDibujable(centro, radio, velocidad, direccion, color);
    }    
}