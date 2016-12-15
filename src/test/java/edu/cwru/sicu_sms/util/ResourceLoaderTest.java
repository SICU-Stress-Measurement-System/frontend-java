/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms.util;

import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Test class for loading <tt>.properties</tt> resource files from the classpath.
 *
 * @since December 15, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
class ResourceLoaderTest {
    
    String filename;
    Properties properties;
    InputStream inStream;
    
    @BeforeEach
    void setUp() {
        filename = "util/TestProperties.properties";
        properties = new Properties();
        inStream = null;
    }
    
    @AfterEach
    void tearDown() {
        properties = null;
        inStream = null;
    }
    
    @Test
    void useResourceStream() {
        inStream = getClass().getClassLoader().getResourceAsStream(filename);
        if (inStream == null) {
            printFound(false);
            return;
        }
        printFound(true);
        loadProperties();
        printProperties();
    }
    
    @Test
    void useFileInputStream() {
        useFileInputStream(filename);
        
        System.out.println("\n ** NOW TRYING WITH FULL FILEPATH **\n");
        filename = "src/test/resources/" + filename;
        useFileInputStream(filename);
    }
    
    void useFileInputStream(String filename) {
        try {
            inStream = new FileInputStream(filename);
            properties.load(inStream);
        } catch (IOException e) {
            printFound(false);
//            e.printStackTrace();
            return;
        }
        printFound(true);
        loadProperties();
        printProperties();
    }
    
    void printFound(boolean success) {
        System.out.println(success
                ? "Found file '" + filename + "'!"
                : "Couldn't find file '" + filename + "'"
        );
    }
    
    void loadProperties() {
        System.out.println("Loading properties...");
        try {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    void printProperties() {
        properties.keySet().forEach(key -> {
            Object value = properties.get(key);
            System.out.println("\t" + key + "=" + value);
        });
    }
    
}
