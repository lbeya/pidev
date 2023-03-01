/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealtroc;

import entities.MailSender;
import entities.SMSsender;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Eya
 */
public class Dealtroc extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();


//   String to = "zhiri.medamine@gmail.com";
//   String from ="lbeya99@gmail.com";
//   String subject = "Deal Troc";
//   String body = "Someone commented article";
//   String passwd="ltusslpyminftuvk";
//   MailSender.sendEmail(to,from,passwd,subject,body);



    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
