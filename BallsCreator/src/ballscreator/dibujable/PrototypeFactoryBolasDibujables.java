/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballscreator.dibujable;

import java.util.HashMap;

/**
 *
 * @author alexander
 */
public class PrototypeFactoryBolasDibujables{
    private static HashMap<String, BolaMovibleDibujable> prototipos = new HashMap();
    
    public static BolaMovibleDibujable getPrototype (String nombre){
        if (prototipos.containsKey(nombre)) {
            return prototipos.get(nombre).deepclone();
        }
        return null;
    }
    
    public static void addPrototype (String nombre, BolaMovibleDibujable prototype){
        if (!prototipos.containsKey(nombre)) {
            prototipos.put(nombre, prototype);
        }
    }
}
