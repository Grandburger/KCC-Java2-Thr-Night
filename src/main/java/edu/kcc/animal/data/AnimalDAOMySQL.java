/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc.animal.data;

import edu.kcc.animal.Animal;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Ramiro Pena
 */

public class AnimalDAOMySQL implements AnimalDAO {
    
    private static ArrayList<Animal> animals;
    private Connection buildConnection() throws SQLException {
        String databaseUrl = "localhost";
        String databasePort = "3306";
        String databaseName = "JavaII_Animal_DB";
        String userName ="root";
        String password = "passoword";
        
        String connectionString = "jdbc:mysql://" + databaseUrl + ":" 
                        + databasePort + "/" + databaseName + "?"
                        + "user=" + userName + "&"
                        + "password=" + password + "&"
                        + "useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(connectionString);
    }

    private void readFromDatabase() throws AnimalDataException {
        try (Connection connection = buildConnection()) {
            if (connection.isValid(2)) {
                animals = new ArrayList<>();
                CallableStatement callableStatement = 
                   connection.prepareCall("CALL sp_get_an_Animal();");
                ResultSet resultSet = callableStatement.executeQuery();
                
                String name;
                int id;
                String species;
                String gender;
                int age;
                boolean fixed;
                int legs;
                BigDecimal weight;
                LocalDate dateAdded;
                LocalDateTime lastFeedingTime;
                while(resultSet.next()){
                    id = resultSet.getInt("Animal_ID");
                    name = resultSet.getString("Animal_Name");
                    species = resultSet.getString("Animal_Species");
                    gender = resultSet.getString("Animal_Gender");
                    age = resultSet.getInt("Animal_Age");
                    fixed = resultSet.getBoolean("Animal_Fixed");
                    legs = resultSet.getInt("Animal_Legs");
                    weight = resultSet.getBigDecimal("Animal_Weight");
                    dateAdded = resultSet.getDate("Date_Added");
                    lastFeedingTime = resultSet.getTime("Last_Feed_Time");
                    animals.add(new Animal(id, name, species, gender, age, 
                            fixed, legs, weight, dateAdded, lastFeedingTime));
                }
                resultSet.close();
                callableStatement.close();
            }
        } catch(Exception exception) {
            System.out.println("Exception message: " + exception.getMessage());
            if (exception instanceof SQLException) {
                SQLException sqlException = (SQLException) exception;
                System.out.println("Error Code: " + sqlException.getErrorCode());
                System.out.println("SQL State: " + sqlException.getSQLState());
            }
        }

    }

    @Override
    public void createAnimalRecord(Animal animal) throws AnimalDataException {
        // Verifies that the animal isn't in the ArrayList before adding it
        verifyAnimalList();
        Animal checkAnimal = getAnimalByName(animal.getName());
        if(null != checkAnimal){
            throw new AnimalDataException("Animal IDs must be unique.");
        }
        animals.add(animal);
        // Creates new animal record in the database
        try{
            Connection conn = buildConnection();
            CallableStatement callableStatement
                    = conn.prepareCall("CALL sp_add_Animal(?,?,?,?);");
            callableStatement.setString(1, animal.getName());
            callableStatement.setString(2, animal.getSpecies());
            callableStatement.setString(3, animal.getGender());
            callableStatement.setInt(4, animal.getLegs());

            callableStatement.execute();
            callableStatement.close();
            conn.close();

        } catch(SQLException ex){
            throw new AnimalDataException(ex);
        }
    }

    @Override
    public Animal getAnimalById(String name) throws AnimalDataException {
        Animal animal = null;
        // Use this code to look up the animal from the ArrayList
        verifyAnimalList();
        for (Animal animal1 : animals) {
            if(animal1.getName().equals(name)){
                animal = animal1;
                break;
            }
        }
        // Use this code if you want to look the animal up from a database query
        try{
            Connection conn = buildConnection();
            CallableStatement callableStatement
                    = conn.prepareCall("CALL sp_get_Animal_by_Animal_Name(?);");
            callableStatement.setString(1, name);

            ResultSet resultSet = callableStatement.executeQuery();
            String species;
            String gender;
            int age;
            if(resultSet.next()){
                species = resultSet.getString("Make");
                gender = resultSet.getString("Animal_Gender");
                age = resultSet.getInt("Model_Year");
                animal = new Animal(id, name, species, gender, age);
            }
            callableStatement.close();
            conn.close();

        } catch(SQLException ex){
            throw new AnimalDataException(ex);
        }
        return animal;
    }
    
}
