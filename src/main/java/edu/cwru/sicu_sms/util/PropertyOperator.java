/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class provides support for reading property files (i.e. marked with the <code>.properties</code> extension).
 *
 * @since December 13, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public class PropertyOperator {
    
    private static final ClassLoader LOADER = PropertyOperator.class.getClassLoader();
    
    private static InputStream inStream;
    private static FileOutputStream outStream;
    private static Properties properties;
    
    /**
     * Loads the property list from the specified file.
     *
     * @param filename the system-dependent name of the <tt>.properties</tt> file to be loaded
     *
     * @return a reference to the {@code Properties} object instantiated in this process, populated with key-value pairs loaded from the file, or left unpopulated if the file was not found
     *
     * @implNote This method is defined with the {@code synchronized} modifier, making it thread-safe.
     */
    public static synchronized Properties loadProperties(String filename) {
        properties = new Properties();
        try {
            inStream = LOADER.getResourceAsStream(filename);
            properties.load(inStream);
            inStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
    /**
     * Saves the property list in the specified file, with the given description.
     *
     * @param properties the property list
     * @param filename   the system-dependent name of the <code>.properties</code> file to be loaded
     * @param comment    a description of the property list to be inserted as a header comment
     *
     * @return {@code true} if this procedure was successful; {@code false} otherwise
     *
     * @implNote This method is defined with the {@code synchronized} modifier, making it thread-safe.
     */
    public static synchronized boolean saveProperties(Properties properties, String filename, String comment) {
        boolean success = false;
        try {
            outStream = new FileOutputStream(filename);
            properties.store(outStream, comment);
            outStream.close();
            
            success = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
    
}
