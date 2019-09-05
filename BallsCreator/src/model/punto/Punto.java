/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.punto;

import model.prototype.IPrototype;

/**
 *
 * @author aborbon
 */
public class Punto implements IPrototype<Punto>{
    int x, y;
    
    public Punto(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Punto clone() {
        return new Punto(x, y);
    }

    @Override
    public Punto deepclone() {
        return clone();
    }
    
    
    
}
