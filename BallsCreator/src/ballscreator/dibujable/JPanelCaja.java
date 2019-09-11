/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballscreator.dibujable;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author aborbon
 */
public class JPanelCaja extends JPanel implements ComponentListener{
    
    ArrayList<BolaMovibleDibujable> bolas;

    public JPanelCaja() {
        super();
        bolas = new ArrayList();
        this.addComponentListener(this);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
       super.paintComponent(g);
       g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
       for (BolaMovibleDibujable bola : bolas) {
            g.setColor(bola.getColor());//.setFill(color);
            g.fillOval(bola.getCentro().getX(), bola.getCentro().getY(), 2 * bola.getRadio(), 2 * bola.getRadio());
           //bola.dibujar(this);
       }
    }
    
    public void addBall(BolaMovibleDibujable bola) {
        bolas.add(bola);
    }
    
    public void setMaxX (int maxX) {
        for (BolaMovibleDibujable bola : bolas) {
            bola.setMaxx(maxX);
        }
    }
    
    public void setMaxY (int maxY) {
        for (BolaMovibleDibujable bola : bolas) {
            bola.setMaxy(maxY);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.setMaxX(this.getWidth());
        this.setMaxY(this.getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
