/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms;

import javafx.application.Platform;

import java.util.logging.Level;
import java.util.logging.Logger;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

/**
 * This class is an abstraction for building sub-controller classes to be most likely used by the main {@link Controller} to manage serial connections and other real-time activities pertaining to biomedical signal processing.
 *
 * @since December 3, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
abstract class BiosigController {
    
    private SerialPort serialPort;
    
    /**
     * Connect to the specified serial port to begin reading data from the biosignal stream.
     *
     * @param portName the name of the serial port
     *
     * @return <code>true</code> if the connection was successful; <code>false</code> otherwise
     */
    boolean connect(String portName) {
        boolean success = false;
        final SerialPort newPort = new SerialPort(portName);
        try {
            newPort.openPort();
            newPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            newPort.setEventsMask(SerialPort.MASK_RXCHAR);
            newPort.addEventListener(
                    (SerialPortEvent serialPortEvent) -> {
                        if (serialPortEvent.isRXCHAR())
                            onSerialPortEvent();
                    });
            this.serialPort = newPort;
            success = true;
        }
        catch (SerialPortException e) {
            logSerialPortException(e);
        }
        return success;
    }
    
    /**
     * Disconnect from the current serial port.
     *
     * @return <code>true</code> if this operation was successful; <code>false</code> if there was no serial port to disconnect, or if something went wrong
     */
    boolean disconnect() {
        boolean success = false;
        try {
            serialPort.removeEventListener();
            if (serialPort.isOpened()) serialPort.closePort();
            serialPort = null;
            success = true;
        } catch (NullPointerException e) {
            // serial port was null to begin with
        } catch (SerialPortException e) {
            logSerialPortException(e);
        }
        return success;
    }
    
    /**
     * Task to be performed when the serial port detects data bytes in the input buffer.
     */
    void onSerialPortEvent() {
        try {
            int byteCount = 1;  // TODO: Customize byteCount based on needs in subclasses.
            byte[] bytes = serialPort.readBytes(byteCount);
            Platform.runLater(() ->
                    updateSeriesWith(bytes[0]));
        }
        catch (SerialPortException e) {
            logSerialPortException(e);
        }
    }
    
    /**
     * Update the chart series with the given value, shifting all preceding data in the negative-time direction by one time step.
     *
     * @param newValue the new value to add to the chart series
     */
    void updateSeriesWith(float newValue) {
        // TODO: 12/4/2016
    }
    
    /**
     * Log the serial port exception thrown (and caught) in a quick, proper, and reusable way.
     *
     * @param thrown the serial port exception to be logged
     */
    void logSerialPortException(SerialPortException thrown) {
        Logger.getLogger(getClass().getName())
                .log(
                        Level.SEVERE,
                        thrown.getMessage(),
                        thrown
                );
    }
    
}
