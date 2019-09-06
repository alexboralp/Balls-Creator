/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ballscreator.MainFrame;
import model.caja.SingletonCaja;

/**
 *
 * @author aborbon
 */
public class BallsCreatorAdministrator {
    MainFrame mainFrame;
    
    SingletonCaja caja;

    public BallsCreatorAdministrator() {
        caja = SingletonCaja.getCaja();
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    public void setMaxX (int maxX) {
        caja.setMaxx(maxX);
    }
    
    public void setMaxY (int maxY) {
        caja.setMaxy(maxY);
    }
}
