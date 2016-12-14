/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class provides support for reading property files (i.e. marked with the <code>.properties</code> extension).
 *
 * @since December 13, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public class PropertyOperator {
    
    /**
     * Loads the property list from the specified file.
     *
     * @param filename the system-dependent name of the <code>.properties</code> file to be loaded
     *
     * @return a reference to the {@link Properties} object instantiated in this process, populated with key-value pairs loaded from the file, or left unpopulated if the file was not found
     *
     * @implNote This method is defined with the <code>synchronized</code> modifier, making it thread-safe.
     */
    public static synchronized Properties loadProperties(String filename) {
        Properties properties = new Properties();
        try {
            FileInputStream inStream = new FileInputStream(filename);
            properties.load(inStream);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
}
