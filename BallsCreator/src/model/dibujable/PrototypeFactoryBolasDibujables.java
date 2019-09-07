/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dibujable;

import java.util.HashMap;
import model.prototype.IPrototype;

/**
 *
 * @author alexander
 */
public class PrototypeFactoryBolasDibujables{
    private static HashMap<String, BolaMovibleDibujable> prototipos = new HashMap();
    
    public static BolaMovibleDibujable getPrototype (String nombre){
        return prototipos.get(nombre).deepclone();
    }
    
    public static void addPrototype (String nombre, BolaMovibleDibujable prototype){
        prototipos.put(nombre, prototype);
    }
}
