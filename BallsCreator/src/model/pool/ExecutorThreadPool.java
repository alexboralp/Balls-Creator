package model.pool;

import ballscreator.dibujable.BolaMovibleDibujable;
import model.pool.AbstractObjectPool;

public class ExecutorThreadPool extends AbstractObjectPool<BolaMovibleDibujable>{

    public ExecutorThreadPool(int minInstances, int maxInstances, int waitTime, 
            IPoolableObjectFactory<BolaMovibleDibujable> poolableObjectFactory) {
        super(minInstances, maxInstances, waitTime, poolableObjectFactory);
    }
}
