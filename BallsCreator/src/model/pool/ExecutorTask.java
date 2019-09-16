package model.pool;

import ballscreator.dibujable.BolaMovibleDibujable;
import java.awt.Color;
import model.movible.BolaMovible;
import model.punto.Punto;
import model.util.Util;

public class ExecutorTask implements IPooledObject {
    private int uses;
    private static int invalidate;
    private static int counter;
    //Punto centro, int radio, int velocidad, BolaMovible.Direccion direccion, Color color
    public BolaMovibleDibujable execute(Punto centro, int radio, int vel, BolaMovible.Direccion dir, Color color) {
        int c = ++counter ;
        uses++;
        System.out.println("execute Pool ==> " + c);
        return new BolaMovibleDibujable(centro,radio,vel, dir, color);
        //System.out.println("execute end ==> " + c);
    }

    @Override
    public boolean validate() {
        return uses < 2;
    }

    @Override
    public void invalidate() {
        invalidate++;
        System.out.println("Invalidate Counter ==> " + invalidate);
    }
}