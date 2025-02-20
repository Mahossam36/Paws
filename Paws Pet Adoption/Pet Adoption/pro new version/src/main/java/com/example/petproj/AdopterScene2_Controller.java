package com.example.petproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;

import static com.example.petproj.LoginController.user_id;
import static com.example.petproj.UserManagement.adopters;
import static com.example.petproj.AdoptionManagement.adoptions;
import static com.example.petproj.Petmanagement.pets;


public class AdopterScene2_Controller {
    private Stage stage;
    private Scene scene;



    @FXML
    private void handleMyOldRequests(ActionEvent event) {
        try {

            FXMLLoader AddPetScene = new FXMLLoader(getClass().getResource("MyOldRequests.fxml"));// define the root you want to switch to
            stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(AddPetScene.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage

        } catch (IOException e) {
            System.err.println("Error Old Requests: " + e.getMessage());
        }
    }

    @FXML
    private void handleSendRequests(ActionEvent event) {
        try {
            FXMLLoader AdopterRequests = new FXMLLoader(getClass().getResource("PetRequest_Adopter.fxml"));// define the root you want to switch to
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(AdopterRequests.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        } catch (IOException e) {
            System.err.println("Error loading Adoption Request of Adopter Scene: " + e.getMessage());
        }
    }


    @FXML
    private void handleLogoutRequest (ActionEvent event) {
        showNotification("Log Out Successfuly");
        try {
                FXMLLoader loginScene = new FXMLLoader(getClass().getResource("login.fxml"));// define the root you want to switch to
                stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
                scene = new Scene(loginScene.load()); // import the root to the scene
                stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
                stage.show(); // show the stage

        } catch (IOException e) {
                System.err.println("Error loading login Scene: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteAccount(MouseEvent event) {
        System.out.println("Delete adopter pressed");





        Stage smallerWindow = new Stage();


        Label messageLabel = new Label("Are you sure you want to delete your account?");
        messageLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");

        Button okButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");


        okButton.setOnAction(e -> {
            System.out.println("OK button pressed");
            smallerWindow.close();

            adopters.get(user_id-1).isDeleted = true;
            adoptions.stream()
                    .filter(adoption -> adoption.getAdopter().getID()==user_id)
                    .forEach(adoption -> {
                            adoption.setDeleted(true);
                            pets.get(adoption.getPet().id-1).wasRequested = false;
                    });

            Back(event);
            showNotification("Account Deleted Successfully");
        });

        cancelButton.setOnAction(e -> {
            smallerWindow.close();
        });


        HBox buttonLayout = new HBox(10, okButton, cancelButton);
        buttonLayout.setStyle("-fx-alignment: center;");


        VBox layout = new VBox(15, messageLabel, buttonLayout);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color :  #bbebff");


        Scene smallerScene = new Scene(layout, 400, 150);
        smallerWindow.setTitle("Confirmation");
        smallerWindow.setScene(smallerScene);

        smallerWindow.initModality(Modality.APPLICATION_MODAL);


        smallerWindow.showAndWait();
    }

    private void Back(MouseEvent event) {
        // move to next scene
        try{ FXMLLoader login_scene = new FXMLLoader(getClass().getResource("login.fxml"));// define the root you want to switch to
            stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(login_scene.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        }catch (IOException e) {
            System.err.println("Error loading login Scene : " + e.getMessage());
        }
    }


    @FXML
    private void handleEditAccount(ActionEvent event) {
        try{
            FXMLLoader editAccScene = new FXMLLoader(getClass().getResource("editAccScene.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(editAccScene.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Edit account error " + e.getMessage());
        }
    }

    //NEW
    @FXML
    private void NotficationView() throws IOException {
        //NEW

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Notification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(){{
            setWidth(300);  // Set width
            setHeight(400); // Set height
        }};
        stage.setTitle("Notification");
        stage.setScene(scene);
        stage.show();

    }
}


