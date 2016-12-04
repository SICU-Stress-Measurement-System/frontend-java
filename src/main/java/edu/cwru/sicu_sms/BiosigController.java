/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms;

import javafx.scene.chart.XYChart;
import jssc.SerialPort;

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
    
}
