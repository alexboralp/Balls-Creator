/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballscreator.dibujable;

import model.factory.FactoryPuntos;
import model.factory.IFactoryPuntos;
import model.bola.BolaMovible;
import ballscreator.dibujable.BolaMovibleDibujable;
import model.bola.IBola;
import model.punto.Punto;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author aborbon
 */
public class FactoryBolaMovibleDibujable implements IFactoryBolaMovibleDibujable {

    @Override
    public IBola construirBolaMovibleDibujable(Punto centro, int radio, int velocidad, BolaMovible.Direccion direccion, Color color, GraphicsContext gc) {
        return new BolaMovibleDibujable(centro, radio, velocidad, direccion, color, gc);
    }

    @Override
    public IBola construirBolaMovibleDibujable(int xCentro, int yCentro, int radio, int velocidad, BolaMovible.Direccion direccion, Color color, GraphicsContext gc) {
        IFactoryPuntos creador = new FactoryPuntos();
        return new BolaMovibleDibujable(creador.crearPunto(xCentro, yCentro), radio, velocidad, direccion, color, gc);
    }
    
}
