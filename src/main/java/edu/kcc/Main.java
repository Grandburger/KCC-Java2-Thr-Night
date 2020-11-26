/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc;

import edu.kcc.animal.ui.UIUtility;
import edu.kcc.animal.Animal;

/**
 *
 * @author k0519415
 * @author Nathaniel Webber
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UIUtility.showMessage("Program starting...");
        
        String menuTitle = "Main Menu";
        String[] menuOptions = {
            "1) Add an Animal",
            "2) Find an Animal",
            "3) Show All Animals",
            "4) Update an Animal",
            "5) Delete an Animal",
            "6) Exit"
        };
        String prompt = "Your choice:";
        String errorMessage = "Invalid option.  Please try again.";
        String userChoice;
        // AnimalDAO dao = AnimalDAO.getAnimalDAO();
        
        boolean working = true;
        while(working) {
            userChoice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions);
            switch(userChoice) {
                case "1":
                    // Add an Animal
                    System.out.println("You added an Animal");
                    break;
                case "2":
                    // Find an Animal
                    System.out.println("You found the Animal");
                    break;
                case "3":
                    // Show all Animals
                    System.out.println("You're looking at all the Animals");
                    break;
                case "4":
                    // Update an Animal
                    System.out.println("You have updated the Animal");
                    break;
                case "5":
                    // Delete an Animal
                    System.out.println("You have deleted the Animal");
                    break;
                case "6":
                    working = false;
                    break;
                default:
                    UIUtility.showErrorMessage(errorMessage, true);  
            }
        }
        UIUtility.showMessage("\nProgram complete.");
    }
    
}
