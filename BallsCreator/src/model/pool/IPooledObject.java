package model.pool;

public interface IPooledObject {
    public boolean validate();
    public void invalidate();
}