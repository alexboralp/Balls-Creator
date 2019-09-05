/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

/**
 *
 * @author alexander
 */
public class Util {
    public static int randomInt(int min, int max){
        return min + (int)(Math.random() * (max - min));
    }
}
