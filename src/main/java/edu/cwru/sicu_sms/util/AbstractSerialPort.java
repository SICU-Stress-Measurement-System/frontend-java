/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms.util;

import jssc.SerialPort;
import jssc.SerialPortEventListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is an abstraction of a serial port comprising all the necessary parameters and core functionality needed for the client application.
 *
 * @since December 12, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
abstract class AbstractSerialPort implements SerialPortEventListener {
    
    final SerialPort serialPort;
    final Properties properties;
    
    /**
     * Constructs the underlying serial port with properties from the specified file.
     *
     * @param filename the relative path to the <code>.properties</code> file
     */
    AbstractSerialPort(String filename) {
        properties = new Properties();
        try {
            FileInputStream inStream = new FileInputStream(filename);
            properties.load(inStream);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serialPort = new SerialPort(getPortName());
    }
    
    private String propertyValue(String key) {
        return properties.getProperty(key);
    }
    
    private int propertyValueAsInt(String key) {
        return Integer.parseInt(propertyValue(key));
    }
    
    public String getPortName() {
        return propertyValue("portName");
    }
    
    public String getNickname() {
        return propertyValue("nickname");
    }
    
    public int getBaudRate() {
        return propertyValueAsInt("baudRate");
    }
    
    public int getDataBits() {
        return propertyValueAsInt("dataBits");
    }
    
    public int getStopBits() {
        return propertyValueAsInt("stopBits");
    }
    
    public int getParity() {
        return propertyValueAsInt("parity");
    }
    
    public int getMask() {
        return propertyValueAsInt("mask");
    }
    
}
