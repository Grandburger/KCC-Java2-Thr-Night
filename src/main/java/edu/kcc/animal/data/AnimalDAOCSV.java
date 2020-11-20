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
                fixed = Boolean.toString(fields[5]);
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
                line = car.getLicensePlate() + ","
                        + car.getMake() + ","
                        + car.getModel() + ","
                        + car.getModelYear();
                writer.println(line);
            }
        } catch (FileNotFoundException ex) {
            throw new AnimalDataException(ex);
        }
    }
    
    private void verifyCarList() throws AnimalDataException {
        if(null == animals){
            readFromFile();
        }
    }

    @Override
    public void createCarRecord(Car car) throws AnimalDataException {
        verifyCarList();
        // Look to see if there is already a car with the same license plate
        // value
        Car checkCar = getCarByLicensePlate(car.getLicensePlate());
        // If there was a matching car, throw an exception.  The license plate
        // is used as a unique identifier in this example.
        if(null != checkCar){
            throw new AnimalDataException("License Plates must be unique.");
        }
        // No other car has the same license plate, so we can add this Car to
        // the data store.
        animals.add(car);
        saveToFile();
    }

    @Override
    public Car getCarByLicensePlate(String licensePlate) throws AnimalDataException {
        verifyCarList();
        Car car = null;
        for (Car car1 : animals) {
            // See if the car has a matching license plate
            if(car1.getLicensePlate().equals(licensePlate)){
                // found a match, so it is the car we want
                car = car1;
                // leave the loop
                break;
            }
        }
        return car;
    }

    @Override
    public ArrayList<Car> getAllCars() throws AnimalDataException {
        verifyCarList();
        return animals;
    }

    @Override
    public void updateCar(Car original, Car updated) throws AnimalDataException {
        verifyCarList();
        Car foundCar = null;
        for (Car car : animals) {
            if(car.equals(original)){
                // found a match!
                foundCar = car;
                break;
            }
        }
        if(null == foundCar){
            // did not find a match to the original!
            throw new AnimalDataException("Original record not found for update.");
        }
        // If no error, update all but the unique identifier
        foundCar.setMake(updated.getMake());
        foundCar.setModel(updated.getModel());
        foundCar.setModelYear(updated.getModelYear());
        saveToFile();
    }

    @Override
    public void deleteCar(Car car) throws AnimalDataException {
        deleteCar(car.getLicensePlate());
    }

    @Override
    public void deleteCar(String licensePlate) throws AnimalDataException {
        verifyCarList();
        Car foundCar = null;
        for (Car car : animals) {
            if(car.getLicensePlate().equals(licensePlate)){
                foundCar = car;
                break;
            }
        }
        if(null == foundCar){
            // did not find a match to the original!
            throw new AnimalDataException("Record not found for delete.");
        }
        // If no error, update all but the unique identifier
        animals.remove(foundCar);
        saveToFile();
    }
}
