/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc.animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author marchauschildt
 */
public class AnimalTest {

    private Animal animal;
    
    public void setUp() {
        animal = new Animal();
    }
    
    // TODO - Need testGetId method
   
    public void testGetName() {
        setUp();
        String expected = "Unknown";
        String actual = animal.getName();
        assertEquals(expected, actual);
    }
    
    // TODO - Need testGetSpecies method (Becky)
    
    // TODO - Need testGetGender method (Whitney)
    
    // TODO - Need testGetName method (Chase)
    
    // TODO - Need testGetAge method (Ramiro)
    public void testGetAge(){
        setUp();
        int expResult = 0;
        int result = animal.getAge();
        assertEquals(expResult, result);
    }
    
    // TODO - Need testGetFixed method (Calvin)
    /**
     * Test of getFixed method, of class Animal.
     */
    @org.junit.jupiter.api.Test
    public void testGetFixed() {
        System.out.println("getFixed");
        Animal instance = animal;
        boolean expResult = false;
        boolean result = instance.getFixed();
        assertEquals(expResult, result);
    }
    
    // TODO - Need testGetId method (Joseph)
    
    // TODO - Need testGeLegs method (Jory)
    
    // TODO - Need testGetWeight method (Chantal)
    
    // TODO - Need testGetDateAdded method
    
    // TODO - Need testGetLastFeedingTime method (Ramiro)
        public void testGetLastFeedingTime()
        {
            setUp();
            LocalDateTime expResult = LocalDateTime.of(2020, 10, 1, 23, 59);
            LocalDateTime result = animal.getLastFeedingTime();
            assertEquals(expResult, result);
        }
}
