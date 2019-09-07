/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolasencaja;

import model.movible.BolaMovible.Direccion;
import model.dibujable.BolaMovibleDibujable;
import ballscreator.dibujable.CajaDibujable;
import model.caja.SingletonCaja;
import model.dibujable.FactoryBolaMovibleDibujable;
import model.dibujable.IFactoryBolaMovibleDibujable;
import model.dibujable.PrototypeFactoryBolasDibujables;
import model.punto.Punto;
import model.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.paint.Color;

/**
 *
 * @author alexander
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private CajaDibujable cnvCaja;
    @FXML
    private Spinner spnCantidad;
    @FXML
    private Spinner spnVelocidad;
    @FXML
    private ComboBox cmbConstructor;
    @FXML
    private ComboBox cmbDireccion;
    @FXML
    private ColorPicker clrColor;
    
    SingletonCaja caja;
    
    public enum Factories { FACTORY, PROTOTYPE, NONE };
    private final int minVelocidad = 0;
    private final int maxVelocidad = 30;
    private final int minCantidad  = 1;
    private final int maxCantidad  = 1000000;
    private final int minTamanno   = 2;
    private final int maxTamanno   = 6;
    
    
    @FXML
    private void handleBtnCrearAction(ActionEvent event) {
        int cantidad  = (int) spnCantidad.getValue();
        int velocidad = (int) spnVelocidad.getValue();
        int vel = velocidad;
        Color color   = clrColor.getValue();
        String dir = (String)cmbDireccion.getValue();
        if ( dir == null || "".equals(dir) )
            dir = "ALEATORIO";
        Direccion direccion;
        String constructor = (String)cmbConstructor.getValue();
        if (null == constructor || "".equals(constructor))
            constructor = "FACTORY";
        
        if ("ARRIBA".equals(dir))
            direccion = Direccion.ARRIBA;
        else if ("ABAJO".equals(dir))
            direccion = Direccion.ABAJO;
        else if ("DERECHA".equals(dir))
            direccion = Direccion.DERECHA;
        else if ("IZQUIERDA".equals(dir))
            direccion = Direccion.IZQUIERDA;
        else {
            int direc = Util.randomInt(1, 4);
            switch ( direc ){
                case 1: direccion = Direccion.ARRIBA;
                        break;
                case 2: direccion = Direccion.ABAJO;
                        break;
                case 3: direccion = Direccion.DERECHA;
                        break;
                case 4: direccion = Direccion.IZQUIERDA;
                        break;
                default: direccion = Direccion.ARRIBA;
                        break;
            }
        }
        
        long starttime = System.nanoTime();
        
        for (int i = 0; i < cantidad; i++){
            if ("ALEATORIO".equals(dir)){
                int direc = Util.randomInt(1, 5);
                switch ( direc ){
                    case 1: direccion = Direccion.ARRIBA;
                            break;
                    case 2: direccion = Direccion.ABAJO;
                            break;
                    case 3: direccion = Direccion.DERECHA;
                            break;
                    case 4: direccion = Direccion.IZQUIERDA;
                            break;
                    default: direccion = Direccion.ARRIBA;
                            break;
                }
            }

            if ( velocidad == 0 )
                vel = Util.randomInt(minVelocidad + 1, maxVelocidad);

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        for (Factories fact : Factories.values())
            cmbConstructor.getItems().add(fact.toString());
        cmbConstructor.setValue(Factories.FACTORY.toString());
        
        for (Direccion dir : Direccion.values())
            cmbDireccion.getItems().add(dir.toString());
        cmbDireccion.getItems().add("ALEATORIO");
        cmbDireccion.setValue("ALEATORIO");
                
        spnCantidad.setValueFactory(new IntegerSpinnerValueFactory(minCantidad, maxCantidad,1));
        spnVelocidad.setValueFactory(new IntegerSpinnerValueFactory(minVelocidad, maxVelocidad,0));
        
        caja = SingletonCaja.getCaja();
        caja.setMaxx((int)cnvCaja.getWidth());
        caja.setMaxy((int)cnvCaja.getHeight());
        
        PrototypeFactoryBolasDibujables.addPrototype("bola", new BolaMovibleDibujable(0,0,1,1,Direccion.ABAJO, Color.WHITE, cnvCaja.getGraphicsContext2D()));
    }    
    
}
