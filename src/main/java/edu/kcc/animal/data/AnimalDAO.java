package edu.kcc.animal.data;

import edu.kcc.animal.Animal;
import java.util.ArrayList;

/**
 *
 * @author PSUser
 */
public interface AnimalDAO {
    void createAnimalRecord(Animal animal) throws AnimalDataException;
    
    Animal getAnimalById(String id) throws AnimalDataException;
    
    ArrayList<Animal> getAllAnimals() throws AnimalDataException;
    
    void updateAnimal(Animal original, Animal updated) throws AnimalDataException;
    
    void deleteAnimal(Animal animal) throws AnimalDataException;
    
    void deleteAnimal(String id) throws AnimalDataException;
} //end of interface 