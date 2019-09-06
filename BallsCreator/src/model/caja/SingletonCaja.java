/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.caja;

import model.bola.BolaMovible;
import java.util.ArrayList;

/**
 *
 * @author alexander
 */
public class SingletonCaja{
    private static SingletonCaja caja;
    
    private int minx, miny, maxx, maxy;
    private final ArrayList<BolaMovible> bolas;
    
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
        if (this.minx != minx) {
            this.minx = minx;
            bolas.forEach((bola) -> {
                bola.setMinx(minx);
            });
        }
    }

    public int getMiny() {
        return miny;
    }

    public void setMiny(int miny) {
        if (this.miny != miny) {
            this.miny = miny;
            bolas.forEach((bola) -> {
                bola.setMiny(miny);
            });
        }
    }

    public int getMaxx() {
        return maxx;
    }

    public void setMaxx(int maxx) {
        if (this.maxx != maxx) {
            this.maxx = maxx;
            bolas.forEach((bola) -> {
                ((BolaMovible)bola).setMaxx(maxx);
            });
        }
    }

    public int getMaxy() {
        return maxy;
    }

    public void setMaxy(int maxy) {
        if (this.maxy != maxy) {
            this.maxy = maxy;
            bolas.forEach((bola) -> {
                bola.setMaxy(maxy);
            });
        }
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
    
    public void moverBolas() {
        bolas.forEach((bola) -> {
            bola.moverEnCaja();
        });
    }
}
