/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main stage of the front-end program for interfacing with the electronic system.
 *
 * @since September 27, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("scene/sicu_sms.fxml"))));
    
        primaryStage.setTitle("SICU Stress Measurement System");
        primaryStage.getIcons()
                .add(new Image("http://s3.amazonaws.com/libapps/customers/558/images/CWRU_Logo.jpg"));
        
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
