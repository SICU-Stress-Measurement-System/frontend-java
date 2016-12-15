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

import java.util.Properties;

/**
 * This class is an abstraction of a serial port comprising all the necessary parameters and core functionality needed for the client application.
 *
 * @since December 12, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
abstract class AbstractSerialPort implements SerialPortEventListener {
    
    SerialPort serialPort;
    Properties properties;
    
    /**
     * Constructs the underlying serial port with the specified list of properties.
     *
     * @param filename the name of the serial port <tt>.properties</tt> file
     */
    AbstractSerialPort(String filename) {
        properties = PropertyOperator.loadProperties(filename);
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
    
    /**
     * Opens the underlying serial port.
     *
     * @return <code>true</code> if the serial port was successfully opened; <code>false</code> otherwise
     */
    public boolean openPort() {
        boolean success = false;
        final SerialPort newPort = new SerialPort(getPortName());
        try {
            newPort.openPort();
            newPort.setParams(getBaudRate(), getDataBits(), getStopBits(), getParity());
            newPort.setEventsMask(getMask());
            newPort.addEventListener(this);
            
            serialPort = newPort;
            success = true;
        }
        catch (SerialPortException e) {
            e.printStackTrace();
        }
        return success;
    }
    
    /**
     * Replaces the current event listener of the underlying serial port with the one provided.
     *
     * @param listener the event listener to add
     * @return {@code true} if the event listener was successfully reconfigured; {@code false} otherwise
     */
    public boolean setEventListener(SerialPortEventListener listener) {
        boolean success = false;
        try {
            serialPort.removeEventListener();
            serialPort.addEventListener(listener);
            success = true;
        }
        catch (SerialPortException e) {
            e.printStackTrace();
        }
        return success;
    }
    
    /**
     * Reads ____ data in the input buffer of the underlying serial port.
     * TODO: Should know how many bytes to read at a time!
     *
     * @return a byte array of data
     */
    public byte[] readBytes() {
        byte[] bytes = null;
        try {
            bytes = serialPort.readBytes();
        }
        catch(SerialPortException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    
    /**
     * TODO
     * @return
     */
    public int[] readIntArray() {
        int[] intArray = null;
        try {
            intArray = serialPort.readIntArray();
        }
        catch (SerialPortException e) {
            e.printStackTrace();
        }
        return intArray;
    }
    
    /**
     * Closes the underlying serial port.
     *
     * @return <code>true</code> if the serial port was successfully closed; <code>false</code> otherwise
     */
    public boolean closePort() {
        boolean success = false;
        try {
            serialPort.removeEventListener();
            if (serialPort.isOpened())
                serialPort.closePort();
            
            serialPort = null;
            success = true;
        }
        catch (SerialPortException e) {
            e.printStackTrace();
        }
        return success;
    }
    
}
