/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.prototype;

/**
 *
 * @author alexander
 * @param <T>
 */
public interface IPrototype<T extends IPrototype> extends Cloneable {
    public T clone();
    public T deepclone();
}
