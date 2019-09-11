/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ballscreator.MainFrame;
import ballscreator.dibujable.BolaMovibleDibujable;
import ballscreator.dibujable.FactoryBolaMovibleDibujable;
import ballscreator.dibujable.IFactoryBolaMovibleDibujable;
import ballscreator.dibujable.PrototypeFactoryBolasDibujables;
import java.awt.Color;
/*import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;*/
import model.movible.BolaMovible.Direccion;
import model.punto.Punto;
import model.util.Util;

/**
 *
 * @author aborbon
 */
public class BallsCreatorAdministrator implements Runnable{
    public enum Factories { FACTORY, PROTOTYPE, BUILDER, POOL, NONE };
    
    private final int tamanno = 5;
    
    private MainFrame mainFrame;
    //private ArrayList<BolaMovibleDibujable> bolas;
    Thread thread;

    public BallsCreatorAdministrator() {    
        //bolas = new ArrayList();
        thread = new Thread(this);
    }
    
    public void startThread() {
        thread.start();
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    public void createBalls(int cantidad, Color color, String direccion, int velocidad, String constructor) {
        long starttime = System.nanoTime();
        
        Direccion dir = strToDireccion(direccion);
        
        String nombrePrototipo = "" + cantidad + color.toString() + direccion + velocidad + constructor;
        PrototypeFactoryBolasDibujables.addPrototype(nombrePrototipo, new BolaMovibleDibujable(0,0,tamanno,velocidad,dir, color));
        
        int vel = velocidad;
        
        for (int i = 0; i < cantidad; i++){
            if ("Aleatorio".equals(direccion)){
                dir = strToDireccion(direccion);
            }

            if ( velocidad == 0 )
                vel = Util.randomInt(1, 11);

            BolaMovibleDibujable bola;
            
            if ( constructor.equals(Factories.FACTORY.toString()) ){
                System.out.println("Creando con Factory");
                IFactoryBolaMovibleDibujable fact = new FactoryBolaMovibleDibujable();
                
                bola = (BolaMovibleDibujable)fact.construirBolaMovibleDibujable(
                            Util.randomInt(mainFrame.getminX(), mainFrame.getmaxX()), 
                            Util.randomInt(mainFrame.getminY(), mainFrame.getmaxY()),
                            tamanno, //Util.randomInt(minTamanno, maxTamanno), 
                            vel, dir, color);
                
            }else if ( constructor.equals(Factories.PROTOTYPE.toString()) ){
                System.out.println("Creando con Prototype");
                
                bola = PrototypeFactoryBolasDibujables.getPrototype(nombrePrototipo).deepclone();
                        
                bola.setCentro(new Punto(Util.randomInt(mainFrame.getminX(), mainFrame.getmaxX()), 
                            Util.randomInt(mainFrame.getminY(), mainFrame.getmaxY())));
                //bola.setRadio(tamanno); //Util.randomInt(minTamanno, maxTamanno));
                if ( velocidad == 0 ) {
                    bola.setVelocidad(vel);
                }
                //bola.setDireccion(dir);
                //bola.setColor(color);
            }else{
                System.out.println("Creando con new");
                bola = new BolaMovibleDibujable(
                            Util.randomInt(mainFrame.getminX(), mainFrame.getmaxX()), 
                            Util.randomInt(mainFrame.getminY(), mainFrame.getmaxY()),
                            tamanno, //Util.randomInt(minTamanno, maxTamanno), 
                            vel, dir, color);
            }
            bola.setMaxx(mainFrame.getmaxX());
            bola.setMaxy(mainFrame.getmaxY());
            
            //bolas.add(bola);
            new Thread(bola).start();
            mainFrame.addBall(bola);
        }
        
        long endtime = System.nanoTime();
        long elapsedtime = endtime - starttime;
        
        System.out.println("Tiempo transcurrido del método " + constructor + " en nanosegundos: " + elapsedtime + ", en milisegundos:" + elapsedtime/1000000);
        
        thread.start();
    }
    
    /*public void setMaxX (int maxX) {
        for (BolaMovibleDibujable bola : bolas) {
            bola.setMaxx(maxX);
        }
    }
    
    public void setMaxY (int maxY) {
        for (BolaMovibleDibujable bola : bolas) {
            bola.setMaxy(maxY);
        }
    }*/

    @Override
    public void run() {
        while(true) {
            mainFrame.dibujarBolas();
            /*try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BallsCreatorAdministrator.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }
    
    private Direccion strToDireccion(String dir) {
        Direccion direccion = Direccion.Angulo0;
        
        switch (dir) {
            case "Aleatorio":
                int direc = Util.randomInt(1, 8);
                switch ( direc ){
                        case 1: direccion = Direccion.Angulo0;
                                break;
                        case 2: direccion = Direccion.Angulo45;
                                break;
                        case 3: direccion = Direccion.Angulo90;
                                break;
                        case 4: direccion = Direccion.Angulo135;
                                break;
                        case 5: direccion = Direccion.Angulo180;
                                break;
                        case 6: direccion = Direccion.Angulo225;
                                break;
                        case 7: direccion = Direccion.Angulo270;
                                break;
                        default: direccion = Direccion.Angulo315;
                                break;
                    }
                break;
            case "0": direccion = Direccion.Angulo0;
                                break;
            case "45": direccion = Direccion.Angulo45;
                    break;
            case "90": direccion = Direccion.Angulo90;
                    break;
            case "135": direccion = Direccion.Angulo135;
                    break;
            case "180": direccion = Direccion.Angulo180;
                    break;
            case "225": direccion = Direccion.Angulo225;
                    break;
            case "270": direccion = Direccion.Angulo270;
                    break;
            default: direccion = Direccion.Angulo315;
        }
        return direccion;
    }
}
