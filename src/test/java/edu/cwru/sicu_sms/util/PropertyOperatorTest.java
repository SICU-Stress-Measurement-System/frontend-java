/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link PropertyOperator}.
 *
 * @since December 14, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
class PropertyOperatorTest {
    
    private static final String LOAD_FILE = "util/TestProperties.properties";
    private static final String SAVE_FILE = "util/TestProperties.properties";
    
    private Properties propertiesToTest;
    
    @BeforeEach
    void setUp() {
        propertiesToTest = new Properties();
        propertiesToTest.setProperty("key_string", "value_string");
        propertiesToTest.setProperty("key_int",    "380");
        propertiesToTest.setProperty("key_float",  "216.368");
    }
    
    @AfterEach
    void tearDown() {
        propertiesToTest.clear();
        propertiesToTest = null;
    }
    
    @Test
    void loadProperties() {
        Properties propertiesToLoad = PropertyOperator.loadProperties(LOAD_FILE);
        
        // TODO: needs to be implemented
        
        assert false;
    }
    
    @Test
    void saveProperties() {
        Properties propertiesToSave = propertiesToTest;
        propertiesToSave.setProperty("new_key", "new_value");
        
        boolean success = PropertyOperator.saveProperties(
                propertiesToSave, SAVE_FILE, "New Description!");
        
        assert success;
    }
    
}