/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ballscreator.MainFrame;
import ballscreator.dibujable.BolaMovibleDibujable;
import ballscreator.dibujable.FactoryBolaMovibleDibujable;
import ballscreator.dibujable.PrototypeFactoryBolasDibujables;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.builder.BallBuilder;
import model.movible.BolaMovible.Direccion;
import model.pool.ExecutorTask;
import model.pool.ExecutorTaskFactory;
import model.pool.ExecutorThreadPool;
import model.pool.PoolException;
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
    Thread thread;

    public BallsCreatorAdministrator() {
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
    
    public void createBalls(int cantidad, Color color, String direccion, int velocidad, String constructor) throws PoolException {
        long starttime = System.nanoTime();
        
        Direccion dir = strToDireccion(direccion);
        
        String nombrePrototipo = "" + cantidad + color.toString() + direccion + velocidad + constructor;
        PrototypeFactoryBolasDibujables.addPrototype(nombrePrototipo, new BolaMovibleDibujable(0,0,tamanno,velocidad,dir, color));
        
        final ExecutorThreadPool pool = new ExecutorThreadPool(0, cantidad, 1000*100, new ExecutorTaskFactory());
        
        int vel = velocidad;
        
        for (int i = 0; i < cantidad; i++){
            if ("Aleatorio".equals(direccion)){
                dir = strToDireccion(direccion);
            }

            if ( velocidad == 0 )
                vel = Util.randomInt(1, 11);

            BolaMovibleDibujable bola;
            
            if ( constructor.equals(Factories.FACTORY.toString()) ){
                //System.out.println("Creando con Factory");
                
                bola = FactoryBolaMovibleDibujable.construirBolaMovibleDibujable(
                            Util.randomInt(mainFrame.getminX(), mainFrame.getmaxX()), 
                            Util.randomInt(mainFrame.getminY(), mainFrame.getmaxY()),
                            tamanno, //Util.randomInt(minTamanno, maxTamanno), 
                            vel, dir, color);
                
            }else if ( constructor.equals(Factories.PROTOTYPE.toString()) ){
                //System.out.println("Creando con Prototype");
                
                bola = PrototypeFactoryBolasDibujables.getPrototype(nombrePrototipo).deepclone();
                        
                bola.setCentro(new Punto(Util.randomInt(mainFrame.getminX(), mainFrame.getmaxX()), 
                            Util.randomInt(mainFrame.getminY(), mainFrame.getmaxY())));
                /*if ( velocidad == 0 ) {
                    bola.setVelocidad(vel);
                }*/
            }else if ( constructor.equals(Factories.BUILDER.toString()) ){
                //System.out.println("Creando con Builder");
                //BolaMovibleDibujable(Punto centro, int radio, int velocidad, Direccion direccion, Color color)
                
                bola = new BallBuilder()
                        .setCentro(new Punto(Util.randomInt(mainFrame.getminX(), mainFrame.getmaxX()), 
                            Util.randomInt(mainFrame.getminY(), mainFrame.getmaxY())))
                        .setRadio(tamanno)
                        .setVelocidad(vel)
                        .setDireccion(dir)
                        .setColor(color)
                        .build();
            }else if ( constructor.equals(Factories.POOL.toString()) ){

                    bola = pool.getObject();
                    bola.setCentro(new Punto(Util.randomInt(mainFrame.getminX(), mainFrame.getmaxX()), 
                            Util.randomInt(mainFrame.getminY(), mainFrame.getmaxY())));
                    bola.setRadio(tamanno);
                    bola.setVelocidad(vel);
                    bola.setDireccion(dir);
                    bola.setColor(color);
                
            }else{
                //System.out.println("Creando con new");
                bola = new BolaMovibleDibujable(
                            Util.randomInt(mainFrame.getminX(), mainFrame.getmaxX()), 
                            Util.randomInt(mainFrame.getminY(), mainFrame.getmaxY()),
                            tamanno, //Util.randomInt(minTamanno, maxTamanno), 
                            vel, dir, color);
            }
                            
                bola.setMaxx(mainFrame.getmaxX());
                bola.setMaxy(mainFrame.getmaxY());
            
                new Thread(bola).start();
                mainFrame.addBall(bola);
        }
        
        long endtime = System.nanoTime();
        long elapsedtime = endtime - starttime;
        
        System.out.println("Tiempo transcurrido del método " + constructor + " en nanosegundos: " + elapsedtime + ", en milisegundos:" + elapsedtime/1000000);
        
        if (thread.isInterrupted() || !thread.isAlive()) {
            thread.start();
        }
    }

    @Override
    public void run() {
        while(true) {
            mainFrame.dibujarBolas();
        }
    }
    
    private Direccion strToDireccion(String dir) {
        Direccion direccion;
        
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
