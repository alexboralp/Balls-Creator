package model.pool;

import java.awt.Color;
import model.movible.BolaMovible;
import model.punto.Punto;

public interface IPoolableObjectFactory<T extends IPooledObject> {
    public T createNew();
}