/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bola;

import model.punto.Punto;
import model.movible.IMovible;

/**
 *
 * @author aborbon
 */
public class BolaMovible extends Bola implements IMovible{
    
    public enum Direccion {Angulo0, Angulo45, Angulo90, Angulo135, Angulo180, Angulo225, Angulo270, Angulo315}
    
    private int velocidad;
    private int velocidadx, velocidady;
    private Direccion direccion;
    private int minx, miny, maxx, maxy;

    public BolaMovible() {
        super();
        this.velocidad = 1;
        this.direccion = Direccion.Angulo0;
        minx = 0;
        miny = 0;
        maxx = 800;
        maxy = 800;
        setVelocidades(direccion);
    }
    
    
    
    public BolaMovible(Punto centro, int radio, int velocidad, Direccion direccion) {
        super(centro, radio);
        this.velocidad = velocidad;
        this.direccion = direccion;
        minx = 0;
        miny = 0;
        maxx = 800;
        maxy = 800;
        setVelocidades(direccion);
    }
    
    public BolaMovible(int xCentro, int yCentro, int radio, int velocidad, Direccion direccion) {
        super(xCentro, yCentro, radio);
        this.velocidad = velocidad;
        this.direccion = direccion;
        minx = 0;
        miny = 0;
        maxx = 800;
        maxy = 800;
        setVelocidades(direccion);
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
        setVelocidades(direccion);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
        setVelocidades(direccion);
    }
    
    public int getMinx() {
        return minx;
    }

    public void setMinx(int minx) {
        this.minx = minx;
    }

    public int getMiny() {
        return miny;
    }

    public void setMiny(int miny) {
        this.miny = miny;
    }

    public int getMaxx() {
        return maxx;
    }

    public void setMaxx(int maxx) {
        this.maxx = maxx;
    }

    public int getMaxy() {
        return maxy;
    }

    public void setMaxy(int maxy) {
        this.maxy = maxy;
    }
    
    @Override
    public synchronized void mover(){
        this.getCentro().setX( this.getCentro().getX() + velocidadx );
        this.getCentro().setY( this.getCentro().getY() + velocidady );
    }
    
    public synchronized void moverEnCaja() {
        
        mover();

        // Si se salió de los límites de la caja
        if ( ( this.getCentro().getX() <= minx && velocidadx < 0 ) ||
             ( this.getCentro().getX() >= maxx && velocidadx > 0 ) ){
            velocidadx *= -1;
        }
        
        if ( ( this.getCentro().getY() <= miny && velocidady < 0 ) ||
             ( this.getCentro().getY() >= maxy && velocidady > 0 ) ){
            velocidady *= -1;
        }
        
    }
    
    @Override
    public BolaMovible clone() {
        return new BolaMovible(this.getCentro(), this.getRadio(), velocidad, direccion);
    }

    @Override
    public BolaMovible deepclone() {
        return new BolaMovible(this.getCentro().deepclone(), this.getRadio(), velocidad, direccion);
    }
    
    private void setVelocidades(Direccion direccion) {
        switch (direccion) {
            case Angulo0:
                velocidadx = velocidad;
                velocidady = 0;
                break;
            case Angulo45:
                velocidadx = velocidad;
                velocidady = velocidad;
                break;
            case Angulo90:
                velocidadx = 0;
                velocidady = velocidad;
                break;
            case Angulo135:
                velocidadx = -1 * velocidad;
                velocidady = velocidad;
                break;
            case Angulo180:
                velocidadx = -1 * velocidad;
                velocidady = 0;
                break;
            case Angulo225:
                velocidadx = -1 * velocidad;
                velocidady = -1 * velocidad;
                break;
            case Angulo270:
                velocidadx = 0;
                velocidady = -1 * velocidad;
                break;
            case Angulo315:
                velocidadx = velocidad;
                velocidady = -1 * velocidad;
                break;
        }
    }
    
}
