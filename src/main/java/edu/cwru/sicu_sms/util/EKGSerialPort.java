/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms.util;

import jssc.SerialPortEvent;

/**
 * This class is used to configure and manage the serial port for receiving EKG channel data.
 *
 * @since December 12, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public final class EKGSerialPort extends AbstractSerialPort {
    
    public EKGSerialPort() {
        super(EKGSerialPort.class.getSimpleName());
    }
    
    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        // TODO
    }
    
}
