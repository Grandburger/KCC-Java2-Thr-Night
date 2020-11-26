/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc.taskhandler;

import edu.kcc.animal.Animal;
import java.util.List;

/**
 * This class is made to handle getting all Animals.
 * @author Cash Carlson
 */
public class ShowAllAnimals {
    
    public void handleTask(DAOPattern dao) {
        UIUtility.showSectionTitle("Show All Animal Records");
        
        try {
            List<Animal> animals = dao.getAllAnimals();
            for (Animal animal : animals) {
                UIUtility.showMessage("\t" + animal);
            }
        } catch (AnimalException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), true);
        }
        
        UIUtility.showMessage("\nShow All Animals complete.");
        UIUtility.pressEnterToContinue();
    }
    
    public List<Animal> getAllAnimals(DAOPattern dao) throws AnimalException {
        return dao.getAllAnimals();
    }
}
