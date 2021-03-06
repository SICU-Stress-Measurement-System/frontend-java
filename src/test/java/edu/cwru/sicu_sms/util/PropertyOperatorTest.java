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
    
    private static final String TEST_RESOURCES_ROOT = "src/test/resources/";
    
    private String filename = "util/TestProperties.properties";
    
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
        // TODO: restore original file state
    }
    
    @Test
    void loadProperties() {
        Properties loadedProperties = PropertyOperator.loadProperties(filename);
        
        assertEquals(propertiesToTest.size(), loadedProperties.size());
        
        loadedProperties.keySet().forEach(key -> {
            Object value = loadedProperties.get(key);
            System.out.println(key + "=" + value);
            
            assert propertiesToTest.containsKey(key);
            Object expectedValue = propertiesToTest.get(key);
            
            assertEquals(expectedValue, value);
        });
    }
    
    @Test
    void saveProperties() {
        Properties propertiesToSave = propertiesToTest;
        propertiesToSave.setProperty("new_key", "new_value");
        
        boolean success = PropertyOperator.saveProperties(
                propertiesToSave,
                TEST_RESOURCES_ROOT + filename,
                "Test Properties - UPDATED");
        
        assert success;
    }
    
}