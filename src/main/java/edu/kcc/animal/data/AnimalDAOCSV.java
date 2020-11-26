package edu.kcc.animal.data;

import edu.kcc.animal.Animal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class AnimalDAOCSV implements AnimalDAO{
    
    private static final String FILE_NAME = "animals.csv";
    private static ArrayList<Animal> animals;

    private void readFromFile() throws AnimalDataException {
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            animals = new ArrayList<>();
            int lineCount = 0;
            String line;
            String[] fields;
            String id;
            String name;
            String species;
            String gender;
            int age;
            boolean fixed;
            int legs;
            BigDecimal weight;
            LocalDate dateAdded;
            LocalDateTime lastFeedingTime;
            while(in.hasNextLine()){
                lineCount++;
                line = in.nextLine();
                fields = line.split(",");
                id = fields[0];
                name = fields[1];
                species = fields[2];
                gender = fields[3];
                try{
                    age = Integer.parseInt(fields[4]);
                } catch(NumberFormatException nfe){
                    throw new AnimalDataException(nfe.getMessage()
                            + "CSV Line " + lineCount);
                }
                fixed = Boolean.parseBoolean(fields[5]);
                try{
                    legs = Integer.parseInt(fields[6]);
                } catch(NumberFormatException nfe){
                    throw new AnimalDataException(nfe.getMessage()
                            + "CSV Line " + lineCount);
                }
                weight = new BigDecimal(fields[7]);
                dateAdded = LocalDate.parse(fields[8]);
                lastFeedingTime = LocalDateTime.parse(fields[9]);
                animals.add(new Animal(id, name, species, gender, age, fixed, 
                        legs, weight, dateAdded, lastFeedingTime));
            }
        } catch(FileNotFoundException fnfe){
            throw new AnimalDataException(fnfe);
        }
    }

    private void saveToFile() throws AnimalDataException {
        try(PrintWriter writer = new PrintWriter(new File(FILE_NAME))){
            String line;
            for (Animal animal : animals) {
                line = animal.getId() + ","
                        + animal.getName() + ","
                        + animal.getSpecies() + ","
                        + animal.getGender() + ","
                        + animal.getFixed() + ","
                        + animal.getLegs() + ","
                        + animal.getWeight() + ","
                        + animal.getDateAdded() + ","
                        + animal.getLastFeedingTime();
                writer.println(line);
            }
        } catch (FileNotFoundException ex) {
            throw new AnimalDataException(ex);
        }
    }
    
    private void verifyAnimalList() throws AnimalDataException {
        if(null == animals){
            readFromFile();
        }
    }

    @Override
    public void createAnimalRecord(Animal animal) throws AnimalDataException {
        verifyAnimalList();
        // Look to see if there is already a animal with the same animal id
        // value
        Animal checkAnimal = getAnimalById(animal.getId());
        // If there was a matching animal, throw an exception.  The animal id
        // is used as a unique identifier in this example.
        if(null != checkAnimal){
            throw new AnimalDataException("Animal Id must be unique.");
        }
        // No other animal has the same animal id, so we can add this Animal to
        // the data store.
        animals.add(animal);
        saveToFile();
    }

    public Animal getAnimalById(int animalId) throws AnimalDataException {
        verifyAnimalList();
        Animal animal = null;
        for (Animal animal1 : animals) {
            // See if the animal has a matching animal id
            if(animal1.getId().equals(animalId)){
                // found a match, so it is the animal we want
                animal = animal1;
                // leave the loop
                break;
            }
        }
        return animal;
    }

    @Override
    public ArrayList<Animal> getAllAnimals() throws AnimalDataException {
        verifyAnimalList();
        return animals;
    }

    @Override
    public void updateAnimal(Animal original, Animal updated) throws AnimalDataException {
        verifyAnimalList();
        Animal foundAnimal = null;
        for (Animal animal : animals) {
            if(animal.equals(original)){
                // found a match!
                foundAnimal = animal;
                break;
            }
        }
        if(null == foundAnimal){
            // did not find a match to the original!
            throw new AnimalDataException("Original record not found for update.");
        }
        // If no error, update all but the unique identifier
        foundAnimal.setId(updated.getId());
        foundAnimal.setName(updated.getName());
        foundAnimal.setSpecies(updated.getSpecies());
        foundAnimal.setGender(updated.getGender());
        foundAnimal.setAge(updated.getAge());
        foundAnimal.setFixed(updated.getFixed());
        foundAnimal.setLegs(updated.getLegs());
        foundAnimal.setWeight(updated.getWeight());
        foundAnimal.setDateAdded(updated.getDateAdded());
        foundAnimal.setLastFeedingTime(updated.getLastFeedingTime());
        saveToFile();
    }
    
    @Override
    public void deleteAnimal(Animal animal) throws AnimalDataException {
        deleteAnimal(animal.getId());
    }

    @Override
    public void deleteAnimal(String animalName) throws AnimalDataException {
        verifyAnimalList();
        Animal foundAnimal = null;
        for (Animal animal : animals) {
            if(animal.getId().equals(animalName)){
                foundAnimal = animal;
                break;
            }
        }
        if(null == foundAnimal){
            // did not find a match to the original!
            throw new AnimalDataException("Record not found for delete.");
        }
        // If no error, update all but the unique identifier
        animals.remove(foundAnimal);
        saveToFile();
    }

    @Override
    public Animal getAnimalById(String id) throws AnimalDataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
