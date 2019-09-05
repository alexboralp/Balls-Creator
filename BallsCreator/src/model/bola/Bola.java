/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bola;

import model.factory.FactoryPuntos;
import model.prototype.IPrototype;
import model.punto.Punto;

/**
 *
 * @author aborbon
 */
public class Bola implements IBola, IPrototype<Bola>{
    private Punto centro;
    private int radio;

    public Bola() {
        this.centro = new Punto(0,0);
        this.radio = 0;
    }
    
    public Bola(Punto centro, int radio){
        this.centro = centro;
        this.radio = radio;
    }
    
    public Bola(int xCentro, int yCentro, int radio){
        this.centro = FactoryPuntos.crearPunto(xCentro, yCentro);
        this.radio = radio;
    }

    @Override
    public Punto getCentro() {
         return centro;
    }
    
    @Override
    public void setCentro(Punto centro) {
        this.centro = centro;
    }

    @Override
    public int getRadio() {
        return radio;
    }

    @Override
    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public Bola clone() {
        return new Bola(centro, radio);
    }

    @Override
    public Bola deepclone() {
        return new Bola((Punto)centro.deepclone(), radio);
    }
    
}