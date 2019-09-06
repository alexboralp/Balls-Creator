/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballscreator.dibujable;

import model.bola.BolaMovible;
import model.prototype.IPrototype;
import model.punto.Punto;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aborbon
 */
public class BolaMovibleDibujable extends BolaMovible implements IDibujable, Runnable{
    Color color;
    GraphicsContext2D gc;

    public BolaMovibleDibujable(Punto centro, int radio, int velocidad, Direccion direccion, Color color, GraphicsContext gc) {
        super(centro, radio, velocidad, direccion);
        this.color = color;
        this.gc = gc;
    }
    
    public BolaMovibleDibujable(int xCentro, int yCentro, int radio, int velocidad, Direccion direccion, Color color, GraphicsContext gc) {
        super(xCentro, yCentro, radio, velocidad, direccion);
        this.color = color;
        this.gc = gc;
    }

    @Override
    public synchronized void dibujar() {
        
        double x = (double)this.centro.getX() - radio;
        double y = (double)this.centro.getY() - radio;
        gc.setFill(color);
        gc.fillOval(x, y, 2 * radio, 2 * radio);
        
        //System.out.println("Me estoy dibujando con valores: " + x + ", " + y + ", " + (x + 2 * rad) + ", " + ( y + 2 * rad ));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    
    @Override
    public void run() {
        while(true){
            moverEnCaja();
            
            try {
                sleep(1000/Math.abs(velocidad));
            } catch (InterruptedException ex) {
                Logger.getLogger(BolaMovibleDibujable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public IPrototype clone() {
        return new BolaMovibleDibujable(centro, radio, velocidad, direccion, color, gc);
    }

    @Override
    public IPrototype deepclone() {
        return new BolaMovibleDibujable((Punto)centro.deepclone(), radio, velocidad, direccion, color, gc);
    }
    
}
