package com.example.petproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.petproj.LoginController.admin_id;
import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;
import static com.example.petproj.Petmanagement.pets;

public class EditPetController implements Initializable{
    private Stage stage;
    private Scene scene;
    @FXML
    private ComboBox <String> PetType,Available_Pets;
    @FXML
    private TextField PetHealth;
    @FXML
    private TextField PetAge;
    String petage , pethealth;

    @FXML
    private Button submit1 , back ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PetType.getItems().addAll("Cat", "Dog");
    }
    @FXML
    private void handle_Available_Pets_Action(ActionEvent event) {
        Available_Pets.getItems().clear();
        Available_Pets.getItems().addAll(
                pets.stream()
                        .filter(p ->   p.shelter_id==admin_id && p.type.equals(PetType.getValue()) && !p.wasRequested && !p.isDeleted)
                        .map(p -> p.name)
                        .collect(Collectors.toList())
        );

    }
    public void handle_TextFields(ActionEvent event){
        Pets SelectedPet = pets.stream().filter(p ->   p.shelter_id==admin_id && p.type.equals(PetType.getValue()) && p.name.equals(Available_Pets.getValue()) && !p.wasRequested && !p.isDeleted)
                .findFirst().get();


        PetHealth.setText(SelectedPet.health_status);
        PetAge.setText(SelectedPet.age);
    }

    public void submit (ActionEvent event){

    pethealth=PetHealth.getText();
    petage=PetAge.getText();

    pets.stream()
            .filter(p ->  p.shelter_id==admin_id && p.type.equals(PetType.getValue())&& p.name.equals(Available_Pets.getValue()) && !p.wasRequested && !p.isDeleted )
            .findFirst()
            .ifPresent(pet -> {
                if(!Available_Pets.getValue().trim().isEmpty()&&
                        !PetAge.getText().trim().isEmpty() &&
                        !PetHealth.getText().trim().isEmpty() &&
                        PetType.getValue() != null) {
                    pet.age = petage;
                    pet.health_status = pethealth;
                    showNotification("The Pet has been Edited ");
                    Back(event);
                }
                else
                    showNotification("please fill up all the fields ");
            });

}


    @FXML
    private void Back(ActionEvent event) {
        // move to next scene
        try{ FXMLLoader Admin_Scene2 = new FXMLLoader(getClass().getResource("Admin_scene2.fxml"));// define the root you want to switch to
            stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(Admin_Scene2.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        }catch (IOException e) {
            System.err.println("Error loading Admin Scene2 : " + e.getMessage());
        }
    }

}
