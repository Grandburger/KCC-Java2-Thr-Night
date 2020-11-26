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
    
} //end of interface 