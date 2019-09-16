package model.pool;

import ballscreator.dibujable.BolaMovibleDibujable;
import java.awt.Color;
import model.movible.BolaMovible;
import model.punto.Punto;

public class ExecutorTaskFactory  implements IPoolableObjectFactory<BolaMovibleDibujable>{

    @Override
    public BolaMovibleDibujable createNew() {
        return new BolaMovibleDibujable();
    }

}