package com.example.petproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;

public class AdminScene2 {
private Stage stage;
private Scene scene;


@FXML
private Button adoptionRequest,addpet,removepet,logout;

    @FXML
    private void handleAddPet(ActionEvent event) {
        try {
            FXMLLoader AddPetScene = new FXMLLoader(getClass().getResource("addPet.fxml"));// define the root you want to switch to
            stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(AddPetScene.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage

        } catch (IOException e) {
            System.err.println("Error loading AddPetScene: " + e.getMessage());
        }
    }

    @FXML
    private void handleRemovePet(ActionEvent event) {
        try {
            FXMLLoader AdminRequests = new FXMLLoader(getClass().getResource("RemovePet.fxml"));// define the root you want to switch to
            stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(AdminRequests.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        } catch (IOException e) {
            System.err.println("Error loading RemovePetScene: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdoptionRequest(ActionEvent event) {
        try {
            FXMLLoader AdminRequests = new FXMLLoader(getClass().getResource("PetRequest_Admin.fxml"));// define the root you want to switch to
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(AdminRequests.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage

        } catch (IOException e) {
            System.err.println("Error loading Adoption Request of Admin Scene: " + e.getMessage());
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
    private void handleeditshelterinfo (ActionEvent event) {
        try {
            FXMLLoader Editshelter = new FXMLLoader(getClass().getResource("EditShelterInfo.fxml"));// define the root you want to switch to
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(Editshelter.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage

        } catch (IOException e) {
            System.err.println("Error loading Editing shelter of Admin Scene: " + e.getMessage());
        }


    }
    @FXML
    private void handledelshelterinfo (ActionEvent event) {
        try {
            FXMLLoader Delshelter = new FXMLLoader(getClass().getResource("DeleteShelterInfo.fxml"));// define the root you want to switch to
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(Delshelter.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage

        } catch (IOException e) {
            System.err.println("Error loading Deleting shelter of Admin Scene: " + e.getMessage());
        }

    }


    @FXML
    private void handleEditPet(ActionEvent event) {
        try {
            FXMLLoader EditPet = new FXMLLoader(getClass().getResource("EditPet.fxml"));// define the root you want to switch to
            stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(EditPet.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage

        } catch (IOException e) {
            System.err.println("Error loading the Edit pet of Admin Scene: " + e.getMessage());
        }
    }
}
