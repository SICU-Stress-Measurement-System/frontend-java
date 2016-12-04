/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

/**
 * A sub-controller used by the main {@link Controller} to manage EEG biosignal processing.
 *
 * @since December 3, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
class EEGController extends BiosigController {
    
    @FXML LineChart eegChart;
    
    EEGController() {
        super(null, null);
    }
    
}
