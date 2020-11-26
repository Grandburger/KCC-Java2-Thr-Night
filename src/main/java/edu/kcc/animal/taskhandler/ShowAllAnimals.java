/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc.animal.taskhandler;

import edu.kcc.animal.Animal;
import edu.kcc.animal.data.AnimalDAO;
import edu.kcc.animal.data.AnimalDataException;
import edu.kcc.animal.ui.UIUtility;
import java.util.List;

/**
 * This class is made to handle getting all Animals.
 * @author Cash Carlson
 */
public class ShowAllAnimals {
    
    public void handleTask(AnimalDAO dao) {
        UIUtility.showSectionTitle("Show All Animal Records");
        
        try {
            List<Animal> animals = dao.getAllAnimals();
            for (Animal animal : animals) {
                UIUtility.showMessage("\t" + animal);
            }
        } catch (AnimalDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), true);
        }
        
        UIUtility.showMessage("\nShow All Animals complete.");
        UIUtility.pressEnterToContinue();
    }
    
    public List<Animal> getAllAnimals(AnimalDAO dao) throws AnimalDataException {
        return dao.getAllAnimals();
    }
}
