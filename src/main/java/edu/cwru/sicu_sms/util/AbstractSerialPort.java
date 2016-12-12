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
import jssc.SerialPortException;

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
    
    ///////////////////////////  GETTERS  ///////////////////////////////
    
    private String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    private int getPropertyAsInt(String key) {
        return Integer.parseInt(getProperty(key));
    }
    
    public String getPortName() {
        return getProperty("portName");
    }
    
    public String getNickname() {
        return getProperty("nickname");
    }
    
    public int getBaudRate() {
        return getPropertyAsInt("baudRate");
    }
    
    public int getDataBits() {
        return getPropertyAsInt("dataBits");
    }
    
    public int getStopBits() {
        return getPropertyAsInt("stopBits");
    }
    
    public int getParity() {
        return getPropertyAsInt("parity");
    }
    
    public int getMask() {
        return getPropertyAsInt("mask");
    }
    
    ///////////////////////////  SETTERS  ///////////////////////////////
    
    private void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
    
    private void setPropertyAsInt(String key, int value) {
        setProperty(key, String.valueOf(value));
    }
    
    public void setPortName(String value) {
        setProperty("portName", value);
    }
    
    public void setNickname(String value) {
        setProperty("nickname", value);
    }
    
    public void setBaudRate(int value) {
        setPropertyAsInt("baudRate", value);
    }
    
    public void setDataBits(int value) {
        setPropertyAsInt("dataBits", value);
    }
    
    public void setStopBits(int value) {
        setPropertyAsInt("stopBits", value);
    }
    
    public void setParity(int value) {
        setPropertyAsInt("parity", value);
    }
    
    public void setMask(int value) {
        setPropertyAsInt("mask", value);
    }
    
    // TODO: Setters should write updated properties back to .properties file!
    
    ////////////////////////  OTHER METHODS  ////////////////////////////
    
    public boolean openPort() {
        boolean success = false;
        try {
            serialPort.openPort();
            serialPort.setParams(
                    getBaudRate(),
                    getDataBits(),
                    getStopBits(),
                    getParity());
            serialPort.setEventsMask(
                    getMask());
            serialPort.addEventListener(this);
            success = true;
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        return success;
    }
    
}
