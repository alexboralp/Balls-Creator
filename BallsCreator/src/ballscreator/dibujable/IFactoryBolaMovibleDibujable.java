/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballscreator.dibujable;

import model.bola.BolaMovible.Direccion;
import model.bola.IBola;
import model.punto.Punto;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author aborbon
 */
public interface IFactoryBolaMovibleDibujable {
    public IBola construirBolaMovibleDibujable(Punto centro, int radio, int velocidad, Direccion direccion, Color color, GraphicsContext gc);
    public IBola construirBolaMovibleDibujable(int x, int y, int radio, int velocidad, Direccion direccion, Color color, GraphicsContext gc);
}
