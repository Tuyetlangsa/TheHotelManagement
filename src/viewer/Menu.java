/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;


import java.util.ArrayList;
import static util.DataInput.getAnInteger;

/**
 *
 * @author long
 */
public class Menu extends ArrayList<String> {
   private String menuTitle;
    
    

    
    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }
        

    public void addNewOption(String newOption) {
        this.add(newOption);        
    }
    
   
    public void printMenu() {
        if (this.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println("\n------------------------------------");
        System.out.println("Welcome to " + menuTitle);
        for (String x : this)
            System.out.println(this.indexOf(x)+ 1+ ". "+ x);  
    }
    

    public int getChoice() {
        int maxOption = this.size();
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        String errorMsg = "You are required to choose the option 1.." + maxOption; 
        return getAnInteger(inputMsg, errorMsg, 1, maxOption);
   
    }
    
    
}
