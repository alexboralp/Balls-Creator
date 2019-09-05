/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.caja;

import model.bola.BolaMovible;
import ballscreator.dibujable.BolaMovibleDibujable;
import model.bola.IBola;
import java.util.ArrayList;

/**
 *
 * @author alexander
 */
public class SingletonCaja{
    private static SingletonCaja caja;
    
    private int minx, miny, maxx, maxy;
    private final ArrayList<IBola> bolas;
    
    private SingletonCaja(){
        minx = 0;
        miny = 0;
        maxx = 800;
        maxy = 800;
        bolas = new ArrayList();
    }
    
    public static SingletonCaja getCaja(){
        if ( caja == null)
            caja = new SingletonCaja();
        
        return caja;
    }

    public int getMinx() {
        return minx;
    }

    public void setMinx(int minx) {
        this.minx = minx;
        bolas.forEach((bola) -> {
            ((BolaMovible)bola).setMinx(minx);
        });
    }

    public int getMiny() {
        return miny;
    }

    public void setMiny(int miny) {
        this.miny = miny;
        bolas.forEach((bola) -> {
            ((BolaMovible)bola).setMiny(miny);
        });
    }

    public int getMaxx() {
        return maxx;
    }

    public void setMaxx(int maxx) {
        this.maxx = maxx;
        bolas.forEach((bola) -> {
            ((BolaMovible)bola).setMaxx(maxx);
        });
    }

    public int getMaxy() {
        return maxy;
    }

    public void setMaxy(int maxy) {
        this.maxy = maxy;
        bolas.forEach((bola) -> {
            ((BolaMovible)bola).setMaxy(maxy);
        });
    }
    
    public void addBola(BolaMovible bola){
        bola.setMinx(minx);
        bola.setMaxx(maxx);
        bola.setMiny(miny);
        bola.setMaxy(maxy);
        bolas.add(bola);
    }
    
    public void borrarBolas(){
        bolas.clear();
    }

    public synchronized void dibujarBolas() {
        bolas.forEach((bola) -> {
            ((BolaMovibleDibujable)bola).dibujar();
        });
    }
}
