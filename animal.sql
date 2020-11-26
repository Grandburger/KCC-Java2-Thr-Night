/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Author:  Ramiro Pena
 * Created: Nov 26, 2020
 */

DROP DATABASE IF EXISTS JavaII_Animal_DB;
CREATE DATABASE JavaII_Animal_DB;
USE JavaII_Animal_DB;

/* *****************************************************************************
	Create statement for table Animal
***************************************************************************** */
DROP TABLE IF EXISTS Animal;
CREATE TABLE Animal(
    Animal_ID INT NOT NULL,
    Animal_Name VARCHAR(100) NOT NULL,
    Date_Added DATE NOT NULL,
    Last_Feed_Time DATETIME NOT NULL,
    Number_Legs INT NOT NULL,
    Animal_Weight DECIMAL(6,2) NOT NULL,
    Animal_Fixed BIT NOT NULL,
    Animal_Gender VARCHAR(6) NOT NULL,
    Animal_Species VARCHAR(100) NOT NULL,
    Animal_Age INT NOT NULL,
    PRIMARY KEY(Animal_ID))
;

/* *****************************************************************************
	Build Stored Procedure sp_add_Animal
***************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_add_Animal$$
CREATE PROCEDURE sp_add_Animal(
    IN p_Animal_ID INT,
    IN p_Animal_Name VARCHAR(100),
    IN p_Date_Added DATE,
    IN p_Last_Feed_Time DATETIME,
    IN p_Number_Legs INT,
    IN p_Animal_Weight DECIMAL(6,2),
    IN p_Animal_Fixed BIT,
    IN p_Animal_Gender VARCHAR(6),
    IN p_Animal_Species VARCHAR(100),
    IN p_Animal_Age INT
)
BEGIN
    INSERT INTO Animal(
        Animal_ID,
        Animal_Name,
        Date_Added,
        Last_Feed_Time,
        Number_Legs,
        Animal_Weight,
        Animal_Fixed,
        Animal_Gender,
        Animal_Species,
        Animal_Age
    )
    VALUES (
        p_Animal_ID,
        p_Animal_Name,
        p_Date_Added,
        p_Last_Feed_Time,
        p_Number_Legs,
        p_Animal_Weight,
        p_Animal_Fixed,
        p_Animal_Gender,
        p_Animal_Species,
        p_Animal_Age
    );
END$$
DELIMITER ;

/* *****************************************************************************
	Build Stored Procedure sp_get_an_Animal
***************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_get_an_Animal$$
CREATE PROCEDURE sp_get_an_Animal( 
	IN p_original_Animal_ID INT
) 
BEGIN
	SELECT
        Animal_ID,
        Animal_Name,
        Date_Added,
        Last_Feed_Time,
        Number_Legs,
        Animal_Weight,
        Animal_Fixed,
        Animal_Gender,
        Animal_Species,
        Animal_Age
	FROM Animal
    WHERE Animal_ID = p_original_Animal_ID;
END$$
DELIMITER ;

/* *****************************************************************************
	Add some data
***************************************************************************** */
CALL sp_add_Animal(1234,'Pickles','2019-08-05', NOW(), 4, 12, 1, 'Female', 'Cat', '17');
CALL sp_add_Animal(4321,'Jack','2019-08-06', NOW(), 4, 125, 1, 'Male', 'Dog', '12');
CALL sp_add_Animal(321,'Goldy','2019-08-05', NOW(), 4, 70, 1, 'Female', 'Dog', '3');
/* *****************************************************************************
                               END OF SCRIPT	
***************************************************************************** */
