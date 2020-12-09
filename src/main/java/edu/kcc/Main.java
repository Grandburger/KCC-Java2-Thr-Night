/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc;

import edu.kcc.animal.ui.UIUtility;
import edu.kcc.animal.data.AnimalDAOMySQL;

/**
 *
 * @author Ramiro Pena
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        AnimalDAOMySQL animalServer = new AnimalDAOMySQL();
        UIUtility.showMessage("Program starting...");
        
        String menuTitle = "Main Menu";
        String[] menuOptions = {
            "1) Find an Animal",
            "2) Show Lookup History",
            "3) Exit"
        };
        String prompt = "Your choice:";
        String errorMessage = "Invalid option.  Please try again.";
        String userChoice;
        
        boolean working = true;
        while(working) {
            userChoice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions);
            switch(userChoice) {
                case "1":
                    animalServer.getAnimalFromServer(prompt);
                    break;
                case "2":
                    // Show Search History
                    System.out.println("You found the Animal");
                    break;
                case "3":
                    working = false;
                    break;
                default:
                    UIUtility.showErrorMessage(errorMessage, true);  
            }
        }
        UIUtility.showMessage("\nProgram complete.");
    }
    
}
