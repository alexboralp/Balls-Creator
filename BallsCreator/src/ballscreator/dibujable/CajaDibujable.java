/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballscreator.dibujable;

import model.caja.SingletonCaja;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author alexander
 */
public class CajaDibujable extends Canvas{
    Color fondo = Color.BLACK;
    SingletonCaja caja;
    
    public CajaDibujable(){
        super();
        caja = SingletonCaja.getCaja();
        
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                borrar();
                caja.dibujarBolas();
            }

        };
        timer.start();
    }
    
    public void borrar(){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(fondo);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
}
