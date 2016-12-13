/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms.plot;

import edu.cwru.sicu_sms.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

/**
 * This class is delegated to managing the "Channel Data" plot in the front-end program.
 *
 * @since December 5, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
class TemporalPlot {
    
    @FXML private LineChart temporalChart;
    private final NumberAxis xAxis, yAxis;
    private final ObservableList<LineChart.Series> seriesList;
    
    TemporalPlot() {
        xAxis = new NumberAxis("Time (sec)", -Constants.TIME_WINDOW, 0, 1);
        yAxis = new NumberAxis();
        seriesList = FXCollections.emptyObservableList();
    }
    
}
