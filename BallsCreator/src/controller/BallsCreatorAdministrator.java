/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ballscreator.MainFrame;

/**
 *
 * @author aborbon
 */
public class BallsCreatorAdministrator {
    MainFrame mainFrame;

    public BallsCreatorAdministrator() {
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
