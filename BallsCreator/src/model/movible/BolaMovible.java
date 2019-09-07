/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.movible;

import model.bola.Bola;
import model.punto.Punto;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aborbon
 */
public class BolaMovible extends Bola implements IMovible, Runnable{
    
    public enum Direccion {Angulo0, Angulo45, Angulo90, Angulo135, Angulo180, Angulo225, Angulo270, Angulo315}
    
    private int velocidad;
    private int pasox, pasoy;
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
        this.getCentro().setX( this.getCentro().getX() + pasox );
        this.getCentro().setY( this.getCentro().getY() + pasoy );
    }
    
    public synchronized void moverEnCaja() {
        
        mover();

        // Si se salió de los límites de la caja
        if ( ( this.getCentro().getX() <= minx && pasox < 0 ) ||
             ( this.getCentro().getX() >= maxx && pasox > 0 ) ){
            pasox *= -1;
        }
        
        if ( ( this.getCentro().getY() <= miny && pasoy < 0 ) ||
             ( this.getCentro().getY() >= maxy && pasoy > 0 ) ){
            pasoy *= -1;
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
                pasox = velocidad;
                pasoy = 0;
                break;
            case Angulo45:
                pasox = velocidad;
                pasoy = velocidad;
                break;
            case Angulo90:
                pasox = 0;
                pasoy = velocidad;
                break;
            case Angulo135:
                pasox = -1 * velocidad;
                pasoy = velocidad;
                break;
            case Angulo180:
                pasox = -1 * velocidad;
                pasoy = 0;
                break;
            case Angulo225:
                pasox = -1 * velocidad;
                pasoy = -1 * velocidad;
                break;
            case Angulo270:
                pasox = 0;
                pasoy = -1 * velocidad;
                break;
            case Angulo315:
                pasox = velocidad;
                pasoy = -1 * velocidad;
                break;
        }
    }
    
    
    @Override
    public void run() {
        while(true){
            moverEnCaja();
            
            try {
                sleep(1000/Math.abs(velocidad));
            } catch (InterruptedException ex) {
                Logger.getLogger(BolaMovible.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
