/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bola;

import model.punto.Punto;

/**
 *
 * @author aborbon
 */
public interface IBola {
    public Punto getCentro();
    public void setCentro(Punto centro);
    public int getRadio();
    public void setRadio(int radio);
}
