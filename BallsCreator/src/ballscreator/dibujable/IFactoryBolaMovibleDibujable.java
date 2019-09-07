/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballscreator.dibujable;

import java.awt.Color;
import model.movible.BolaMovible.Direccion;
import model.bola.IBola;
import model.punto.Punto;

/**
 *
 * @author aborbon
 */
public interface IFactoryBolaMovibleDibujable {
    public IBola construirBolaMovibleDibujable(Punto centro, int radio, int velocidad, Direccion direccion, Color color);
    public IBola construirBolaMovibleDibujable(int x, int y, int radio, int velocidad, Direccion direccion, Color color);
}
