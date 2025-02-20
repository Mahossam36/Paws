package com.example.petproj;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;
import static com.example.petproj.UserManagement.admins;
import static com.example.petproj.UserManagement.adopters;


public class LoginController implements Initializable {


    @FXML
    TextField adminusername,userusername;

    @FXML
    PasswordField adminpassword,userpassword;
    @FXML
    Label l1;

    // new || this to handle the request
    public static String user_username;
    public static String admin_username;
    public static int user_id;
    public static int admin_id;

    private Stage stage;
    private Scene scene;
    private boolean x=false;
    String roletype;



    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition pause= null;
        // Delay for 3 seconds

        if(x==false){
// Start the pause
            x=true;
             pause = new PauseTransition(Duration.seconds(3));
            pause.play();
        }


        // After 3 seconds, switch to the next scene
        pause.setOnFinished(event -> {
            try {
                swap(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });



    }


    public void swap(ActionEvent event) throws IOException {
        if (l1==null)
            return;  // Return if the label is null, nothing to do
            try {
            FXMLLoader loginn = new FXMLLoader(getClass().getResource("login.fxml"));// define the root you want to switch to
            stage = (Stage) l1.getScene().getWindow(); // handling the switch event
            scene = new Scene(loginn.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        } catch (Exception e) {
            System.err.println( "Error Loading The Login  Page :" +e);
        }
    }


    public void sginin(ActionEvent event) throws IOException {

        FXMLLoader signin_page = new FXMLLoader(getClass().getResource("signin.fxml"));// define the root you want to switch to
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
        scene = new Scene(signin_page.load()); // import the root to the scene
        stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
        stage.show(); // show the stage

    }


    public void adminlogin(ActionEvent event) throws IOException {

        //admin login handling

        if(adminusername.getText().isEmpty() || adminpassword.getText().isEmpty())
        {
            showNotification("Pls Enter Username and Password");
        }
        else {
            boolean adminUserAndPasswordExists =
                    admins.stream().anyMatch(admin ->
                            admin.getUserName().equals(adminusername.getText()) &&
                            admin.getPassword().equals(adminpassword.getText()));
            boolean adminExists =
                    admins.stream().anyMatch(admin ->
                            admin.getUserName().equals(adminusername.getText()));

            if(adminExists==false)
                showNotification("Admin Not Found");
            else if (adminUserAndPasswordExists == false)
                showNotification("Pls Enter a Valid Password");
            else {
                showNotification("Login Successfuly");

                admin_id = admins.stream().filter(admin -> admin.getUserName().equals(adminusername.getText()) &&
                        admin.getPassword().equals(adminpassword.getText())).findFirst().get().getID();
                admin_username = adminusername.getText();

                // move to next scene
                try {
                    FXMLLoader Admin_Scene2 = new FXMLLoader(getClass().getResource("Admin_scene2.fxml"));// define the root you want to switch to
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // handling the switch event
                    scene = new Scene(Admin_Scene2.load()); // import the root to the scene
                    stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
                    stage.show(); // show the stage
                } catch (IOException e) {
                    System.err.println("Error loading Admin Scene2 : " + e.getMessage());
                }
            }


        }
    }

    public void userlogin(ActionEvent event) throws IOException {

        //user login handling

        if(userusername.getText().isEmpty() || userpassword.getText().isEmpty())
        {
            showNotification("Pls Enter Username and Password");
        }
        else {
            boolean UserAndPasswordExists =
                    adopters.stream().anyMatch(adopter ->
                            adopter.getUserName().equals(userusername.getText()) &&
                            adopter.getPassword().equals(userpassword.getText()) && adopter.isDeleted == false);
            boolean userExists =
                    adopters.stream().anyMatch(adopter ->
                            adopter.getUserName().equals(userusername.getText()) && adopter.isDeleted == false);

            if(userExists==false)
                showNotification("User Not Found");
            else if (UserAndPasswordExists == false)
                showNotification("Pls Enter a Valid Password");
            else {
                showNotification("Login Successfuly");

                user_id = adopters.stream().filter(adopter -> adopter.getUserName().equals(userusername.getText()) &&
                        adopter.getPassword().equals(userpassword.getText()) && adopter.isDeleted == false).findFirst().get().getID();
                user_username = userusername.getText();



                // move to next scene
                try {
                    FXMLLoader Adopter_Scene2 = new FXMLLoader(getClass().getResource("Adopter_Scene2.fxml"));// define the root you want to switch to
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // handling the switch event
                    scene = new Scene(Adopter_Scene2.load()); // import the root to the scene
                    stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
                    stage.show(); // show the stage
                } catch (IOException e) {
                    System.err.println("Error loading Adopter Scene2 : " + e.getMessage());
                }
            }

        }
    }


}
