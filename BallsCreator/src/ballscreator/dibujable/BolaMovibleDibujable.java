/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballscreator.dibujable;

import java.awt.Color;
import java.awt.Graphics;
import model.movible.BolaMovible;
import model.punto.Punto;
import javax.swing.JPanel;

/**
 *
 * @author aborbon
 */
public class BolaMovibleDibujable extends BolaMovible implements IDibujable{
    
    Color color;

    public BolaMovibleDibujable(Punto centro, int radio, int velocidad, Direccion direccion, Color color) {
        super(centro, radio, velocidad, direccion);
        this.color = color;
    }
    
    public BolaMovibleDibujable(int xCentro, int yCentro, int radio, int velocidad, Direccion direccion, Color color) {
        super(xCentro, yCentro, radio, velocidad, direccion);
        this.color = color;
    }
    
    public BolaMovibleDibujable(BolaMovible bola, Color color) {
        super(bola.getCentro(), bola.getRadio(), bola.getVelocidad(), bola.getDireccion());
        this.color = color;
    }

    @Override
    public synchronized void dibujar(JPanel pnlDraw) {
        
        int x = this.getCentro().getX() - this.getRadio();
        int y = this.getCentro().getY() - this.getRadio();
        Graphics graphics = pnlDraw.getGraphics();
        graphics.setColor(color);//.setFill(color);
        graphics.fillOval(x, y, 2 * this.getRadio(), 2 * this.getRadio());
        
        //System.out.println("Me estoy dibujando con valores: " + x + ", " + y + ", " + (x + 2 * rad) + ", " + ( y + 2 * rad ));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public BolaMovibleDibujable clone() {
        return new BolaMovibleDibujable(this.getCentro(), this.getRadio(), this.getVelocidad(), this.getDireccion(), color);
    }

    @Override
    public BolaMovibleDibujable deepclone() {
        return new BolaMovibleDibujable(this.getCentro().deepclone(), this.getRadio(), this.getVelocidad(), this.getDireccion(), color);
    }
    
}
