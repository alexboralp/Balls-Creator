/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import combostar.MainFrame;
import model.ComboBuilder;
import model.ComboBuilderFactory;
import model.Order;
import model.ProductFactory;

/**
 *
 * @author aborbon
 */
public class BallsCreatorAdministrator {
    MainFrame mainFrame;
    ProductFactory productFactory;
    ComboBuilderFactory comboBuilderFactory;
    ComboBuilder comboBuilder;
    Order order;

    public BallsCreatorAdministrator() {
        productFactory = new ProductFactory();
        comboBuilderFactory = new ComboBuilderFactory();
        order = new Order();
    }
    
    public void addProduct(String code) {
        comboBuilder.add(productFactory.get(code));
        mainFrame.setComboString(comboBuilder.toString());
    }
    
    public void fromCombo(String code) {
        comboBuilder = comboBuilderFactory.get(code);
        mainFrame.setComboString(comboBuilder.toString());
    }
    
    public void fromMainProduct(String code) {
        comboBuilder = new ComboBuilder("");
        comboBuilder.add(productFactory.get(code));
        mainFrame.setComboString(comboBuilder.toString());
    }
    
    public void addCombo() {
        if (!comboBuilder.isEmpty()) {
            order.addCombo(comboBuilder.build());
            mainFrame.setOrderString(order.toString());
        }
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        // Se crean las comidas principales
        productFactory.add("main" + "1", ProductFactory.createNewProduct("main" + "1", "Hamburguesa", 2500));
        productFactory.add("main" + "2", ProductFactory.createNewProduct("main" + "2", "Sandwich", 2200));
        productFactory.add("main" + "3", ProductFactory.createNewProduct("main" + "3", "Pollo frito", 1600));
        productFactory.add("main" + "4", ProductFactory.createNewProduct("main" + "4", "Wrap", 1500));
        productFactory.add("main" + "5", ProductFactory.createNewProduct("main" + "5", "Pizza", 2000));
        productFactory.add("main" + "6", ProductFactory.createNewProduct("main" + "6", "Hot Dog", 2100));
        
        this.mainFrame.addMainProduct(productFactory.get("main" + "1"));
        this.mainFrame.addMainProduct(productFactory.get("main" + "2"));
        this.mainFrame.addMainProduct(productFactory.get("main" + "3"));
        this.mainFrame.addMainProduct(productFactory.get("main" + "4"));
        this.mainFrame.addMainProduct(productFactory.get("main" + "5"));
        this.mainFrame.addMainProduct(productFactory.get("main" + "6"));
        
        // Se crean las bebidas
        productFactory.add("drink" + "1", ProductFactory.createNewProduct("drink" + "1", "Gaseosa", 1200));
        productFactory.add("drink" + "2", ProductFactory.createNewProduct("drink" + "2", "Café", 500));
        productFactory.add("drink" + "3", ProductFactory.createNewProduct("drink" + "3", "Té", 450));
        productFactory.add("drink" + "4", ProductFactory.createNewProduct("drink" + "4", "Natural", 1000));
        productFactory.add("drink" + "5", ProductFactory.createNewProduct("drink" + "5", "Frozen", 1300));
        productFactory.add("drink" + "6", ProductFactory.createNewProduct("drink" + "6", "Batido", 1500));
        
        this.mainFrame.addDrink(productFactory.get("drink" + "1"));
        this.mainFrame.addDrink(productFactory.get("drink" + "2"));
        this.mainFrame.addDrink(productFactory.get("drink" + "3"));
        this.mainFrame.addDrink(productFactory.get("drink" + "4"));
        this.mainFrame.addDrink(productFactory.get("drink" + "5"));
        this.mainFrame.addDrink(productFactory.get("drink" + "6"));
        
        // Se crean los adicionales
        productFactory.add("additional" + "1", ProductFactory.createNewProduct("additional" + "1", "Papas", 1000));
        productFactory.add("additional" + "2", ProductFactory.createNewProduct("additional" + "2", "Uvas", 1100));
        productFactory.add("additional" + "3", ProductFactory.createNewProduct("additional" + "3", "Patatas", 1500));
        productFactory.add("additional" + "4", ProductFactory.createNewProduct("additional" + "4", "Maíz", 800));
        productFactory.add("additional" + "5", ProductFactory.createNewProduct("additional" + "5", "Tres leches", 1500));
        productFactory.add("additional" + "6", ProductFactory.createNewProduct("additional" + "6", "Ensalada", 1200));
        productFactory.add("additional" + "7", ProductFactory.createNewProduct("additional" + "7", "Puré", 1300));
        
        this.mainFrame.addAdditional(productFactory.get("additional" + "1"));
        this.mainFrame.addAdditional(productFactory.get("additional" + "2"));
        this.mainFrame.addAdditional(productFactory.get("additional" + "3"));
        this.mainFrame.addAdditional(productFactory.get("additional" + "4"));
        this.mainFrame.addAdditional(productFactory.get("additional" + "5"));
        this.mainFrame.addAdditional(productFactory.get("additional" + "6"));
        
        comboBuilder = new ComboBuilder("combo" + "1");
        comboBuilderFactory.add("combo" + "1", comboBuilder.add(productFactory.get("main" + "1")).add(productFactory.get("drink" + "1")).add(productFactory.get("additional" + "1")));
        
        comboBuilder = new ComboBuilder("combo" + "2");
        comboBuilderFactory.add("combo" + "2", comboBuilder.add(productFactory.get("main" + "3")).add(productFactory.get("drink" + "1")).add(productFactory.get("additional" + "1")));
        
        comboBuilder = new ComboBuilder("combo" + "3");
        comboBuilderFactory.add("combo" + "3", comboBuilder.add(productFactory.get("main" + "6")).add(productFactory.get("drink" + "1")).add(productFactory.get("additional" + "7")));
        
        comboBuilder = new ComboBuilder("combo" + "4");
        comboBuilderFactory.add("combo" + "4", comboBuilder.add(productFactory.get("main" + "5")).add(productFactory.get("drink" + "1")));
        
        comboBuilder = new ComboBuilder("combo" + "5");
        comboBuilderFactory.add("combo" + "5", comboBuilder.add(productFactory.get("main" + "5")).add(productFactory.get("drink" + "1")).add(productFactory.get("additional" + "6")).add(productFactory.get("additional" + "1")));
        
        this.mainFrame.addCombo(comboBuilderFactory.get("combo" + "1"));
        this.mainFrame.addCombo(comboBuilderFactory.get("combo" + "2"));
        this.mainFrame.addCombo(comboBuilderFactory.get("combo" + "3"));
        this.mainFrame.addCombo(comboBuilderFactory.get("combo" + "4"));
        this.mainFrame.addCombo(comboBuilderFactory.get("combo" + "5"));
        
        comboBuilder = new ComboBuilder("");
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
