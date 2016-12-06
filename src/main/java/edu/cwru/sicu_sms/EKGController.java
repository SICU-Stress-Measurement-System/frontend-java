/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms;

import jssc.SerialPortEvent;

/**
 * A sub-controller used by {@link MainController} to manage EKG biosignal processing.
 *
 * @since December 3, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
class EKGController extends BiosigController {
    
    EKGController() {
        super(9600, 1);
    }
    
    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        
    }
}
