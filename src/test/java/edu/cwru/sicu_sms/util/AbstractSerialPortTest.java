package edu.cwru.sicu_sms.util;

import jssc.SerialPortEvent;
import jssc.SerialPortException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

/**
 * Test class for {@link AbstractSerialPort}.
 *
 * @since December 13, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
class AbstractSerialPortTest {
    
    private class TestSerialPort extends AbstractSerialPort {
        
        private Logger logger;
        
        TestSerialPort() {
            super("util/TestSerialPort.properties");
            logger = Logger.getLogger(getClass().getName());
        }
    
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                logger.finest(serialPort.readString());
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }
    
    private TestSerialPort serialPort;
    
    @BeforeEach
    void setUp() {
        serialPort = new TestSerialPort();
    }
    
    @AfterEach
    void tearDown() {
        boolean success = serialPort.closePort();
        serialPort = null;
        if (!success) {
            System.err.println("Serial port failed to close! Exiting...");
            System.exit(1);
        }
    }
    
    @Test
    void openPort() {
        boolean success = serialPort.openPort();
        assert (success);
    }
    
    @Test
    void readData() {
        openPort();  // TODO: not sure if test method will do the job; might have to reopen port
        
        // TODO: finish implementing test method
    }
    
}