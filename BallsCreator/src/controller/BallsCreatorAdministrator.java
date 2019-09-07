/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ballscreator.MainFrame;
import ballscreator.dibujable.BolaMovibleDibujable;
import java.awt.Color;
import java.util.ArrayList;
import model.movible.BolaMovible.Direccion;
import model.util.Util;

/**
 *
 * @author aborbon
 */
public class BallsCreatorAdministrator implements Runnable{
    MainFrame mainFrame;
    ArrayList<BolaMovibleDibujable> bolas;

    public BallsCreatorAdministrator() {    
        bolas = new ArrayList();
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    public void createBalls(int cantidad, Color color, String direccion, int velocidad, String metodo) {
        long starttime = System.nanoTime();
        
        Direccion dir;
        
        for (int i = 0; i < cantidad; i++){
            if ("ALEATORIO".equals(direccion)){
                int direc = Util.randomInt(1, 8);
                switch ( direc ){
                    case 1: dir = Direccion.Angulo0;
                            break;
                    case 2: dir = Direccion.Angulo45;
                            break;
                    case 3: dir = Direccion.Angulo90;
                            break;
                    case 4: dir = Direccion.Angulo135;
                            break;
                    case 5: dir = Direccion.Angulo180;
                            break;
                    case 6: dir = Direccion.Angulo225;
                            break;
                    case 7: dir = Direccion.Angulo270;
                            break;
                    default: dir = Direccion.Angulo315;
                            break;
                }
            }

            if ( velocidad == 0 )
                velocidad = Util.randomInt(1, 11);

            BolaMovibleDibujable bola;
            
            if ( constructor.equals(Factories.FACTORY.toString()) ){
                //System.out.println("Creando con Factory");
                IFactoryBolaMovibleDibujable fact = new FactoryBolaMovibleDibujable();
                
                bola = (BolaMovibleDibujable)fact.construirBolaMovibleDibujable(
                            Util.randomInt(caja.getMinx(), caja.getMaxx()), 
                            Util.randomInt(caja.getMiny(), caja.getMaxy()),
                            Util.randomInt(minTamanno, maxTamanno), 
                            vel, direccion, color, 
                            cnvCaja.getGraphicsContext2D());
                
            }else if ( constructor.equals(Factories.PROTOTYPE.toString()) ){
                //System.out.println("Creando con Prototype");
                
                bola = (BolaMovibleDibujable)PrototypeFactoryBolasDibujables.getPrototype("bola").deepclone();
                        
                bola.setCentro(new Punto(Util.randomInt(caja.getMinx(), caja.getMaxx()), 
                            Util.randomInt(caja.getMiny(), caja.getMaxy())));
                bola.setRadio(Util.randomInt(minTamanno, maxTamanno));
                bola.setVelocidad(vel);
                bola.setDireccion(direccion);
                bola.setColor(color);
            }else{
                //System.out.println("Creando con new");
                bola = new BolaMovibleDibujable(
                            Util.randomInt(caja.getMinx(), caja.getMaxx()), 
                            Util.randomInt(caja.getMiny(), caja.getMaxy()),
                            Util.randomInt(minTamanno, maxTamanno), 
                            vel, direccion, color, 
                            cnvCaja.getGraphicsContext2D());
            }
            
            caja.addBola(bola);
            new Thread(bola).start();
        }
        
        long endtime = System.nanoTime();
        long elapsedtime = endtime - starttime;
        
        System.out.println("Tiempo transcurrido del método " + constructor + " en nanosegundos: " + elapsedtime + ", en milisegundos:" + elapsedtime/1000000);
        
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
    public void run() {
        while(true) {
            mainFrame.dibujarBolas(bolas);
        }
    }
}
