/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms;

import javafx.application.Platform;
import javafx.scene.chart.XYChart;
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
    
    SerialPort serialPort;
    final XYChart chart;
    final XYChart.Series series;
    
    BiosigController(XYChart chart,
                     XYChart.Series series)
    {
        this.chart = chart;
        this.series = series;
    }
    
    boolean connect(String portName) {
        boolean success = false;
        final SerialPort serialPort = new SerialPort(portName);
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            serialPort.addEventListener((SerialPortEvent serialPortEvent) -> {
                if (serialPortEvent.isRXCHAR()) onSerialPortEvent();
            });
            this.serialPort = serialPort;
            success = true;
        }
        catch (SerialPortException e) {
            logSerialPortException(e);
        }
        return success;
    }
    
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
    
    void logSerialPortException(SerialPortException spe) {
        // TODO: 12/4/2016
    }
    
}
