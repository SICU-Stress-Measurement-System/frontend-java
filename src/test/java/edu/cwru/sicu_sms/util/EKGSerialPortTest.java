/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms.util;

import jssc.SerialPortEvent;
import org.junit.jupiter.api.*;

import java.util.Arrays;

/**
 * Test class for {@link EKGSerialPort}.
 *
 * @since December 14, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
class EKGSerialPortTest {
    
    private static long READ_DURATION = (long) 3e9;  // 3 seconds, expressed in nanoseconds
    
    private EKGSerialPort serialPort;
    
    @BeforeEach
    void setUp() {
        serialPort = new EKGSerialPort() {
            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                
            }
        };
        serialPort.openPort();
    }
    
    @AfterEach
    void tearDown() {
        serialPort.closePort();
    }
    
    @Test
    void testInTerminal() {
        int[] data;
        
        long startTime = System.nanoTime();
        do {
            data = serialPort.readIntArray();
            
            if (data != null && data.length > 0) {
                System.out.println(Arrays.toString(data));
            }
        } while (System.nanoTime() - startTime < READ_DURATION);
    }
    
}