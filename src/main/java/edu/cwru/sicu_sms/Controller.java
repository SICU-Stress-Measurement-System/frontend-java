/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import jssc.SerialPortList;

import java.io.FileWriter;

/**
 * The controller for the front-end program.
 *
 * @since October 13, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public class Controller {
    
    @FXML private Menu connectMenu;
    @FXML private ToggleGroup connectGroup;
    @FXML private ToggleButton recordButton;
    
    private ObservableList<String> serialPortList;
    
    private FileWriter fileWriter;
    
    /**
     * Initialize the list of detected ports.
     *
     * @return <code>true</code> if at least one serial port was detected; <code>false</code> otherwise
     */
    private boolean detectSerialPorts() {
        serialPortList = FXCollections.observableArrayList();
        serialPortList.addAll(SerialPortList.getPortNames());
        return !serialPortList.isEmpty();
    }
    
    /**
     * Disconnect from all serial ports.
     *
     * @return <code>true</code> if the serial ports were successfully disconnected; <code>false</code> if there were no ports connected to begin with, or if something went wrong
     */
    private boolean disconnect() {
        boolean success = false;
        try {
            System.out.print("Disconnecting from serial port " + eegPort.getPortName() + "...");
            success = eegPort.closePort();
            eegPort = null;
            if (success) System.out.println("\t->\tSuccessfully disconnected!");
        } catch (Exception e) {
            System.out.println("\t->\tAlready disconnected!");
        }
        return success;
    }  // TODO: reimplement for all serial ports
    
    /**
     * Get whether data recording is currently toggled 'on' in the front-end.
     *
     * @return <code>true</code> if the 'record' toggle button has been pushed; <code>false</code> if no data recording is currently happening
     */
    private boolean isRecording() {
        return recordButton.isSelected();
    }
    
    @FXML
    public void connect(ActionEvent actionEvent) {
        // TODO: figure out how to get item text from action event
    }
    
    @FXML
    public void onMouseEnteredRecordButton() {
        recordButton.setText((isRecording() ? "Stop" : "Start") + " Recording");
    }
    
    @FXML
    public void onMouseExitedRecordButton() {
        recordButton.setText("Record" + (isRecording() ? "ing..." : ""));
    }
    
    @FXML
    public void onMousePressedRecordButton() {
        recordButton.setStyle("-fx-background-color: darkred");
    }
    
    @FXML
    public void onMouseReleasedRecordButton() {
        recordButton.setStyle("-fx-background-color: red");
    }
    
    @FXML
    public void onConnectMenuValidation(Event event) {
        connectMenu.getItems().clear();
        String[] portNames = SerialPortList.getPortNames();
        if (portNames.length == 0) {
            MenuItem dummy = new MenuItem("<no ports available>");
            dummy.setDisable(true);
            connectMenu.getItems().add(dummy);
            return;
        }
        for (String portName : portNames) {
            connectMenu.getItems().add(new RadioMenuItem(portName));
        }
    }
    
    @FXML
    public void record() {
        if (isRecording()) {  // start recording...
            // TODO: Run thread for saving data to file.
        }
        else {  // stop recording...
            // TODO: End thread for saving data to file.
        }
        onMouseEnteredRecordButton();  // indicate what next click would do
    }
    
    @FXML
    public void confirmExit() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            disconnect();
            Platform.exit();
        }
    }
    
}
