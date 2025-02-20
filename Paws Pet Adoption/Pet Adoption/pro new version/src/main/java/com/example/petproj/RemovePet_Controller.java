package com.example.petproj;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.example.petproj.LoginController.admin_id;
import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;
import static com.example.petproj.Petmanagement.pets;
import static com.example.petproj.ShelterManagement.shelter;


public class RemovePet_Controller
{


    @FXML
    private ComboBox<String> pet_Type_Box, pet_Name_Box;

    @FXML
    private TextField shelterNameField, breedField, ageField;

    @FXML
    private Label breedText, ageText, shelterIDText;


    @FXML
    private Button deleteButton ,backButton;
    Integer shelterId;
    private Stage stage;
    private Scene scene;

    public void initialize() {
        //hide all elements
        set_visability(false);
        shelterNameField.setVisible(true);
        shelterIDText.setVisible(true);

        shelterId=admin_id;
        shelterNameField.setText(shelter.stream().filter(shelter -> shelter.id==admin_id)
                .map(shelter -> shelter.name).findFirst().orElse("null"));
        shelterNameField.setEditable(false);

        // Load pets type for the selected shelter
        pet_Type_Box.setVisible(true);
        pet_Name_Box.setVisible(false);
        pet_Type_Box.getItems().clear();
        pet_Type_Box.getItems().addAll("Cat", "Dog");




        pet_Type_Box.setOnAction(event -> handle_PetTypeBox_Action(event));
        pet_Name_Box.setOnAction(event -> handle_PetNameBox_Action(event));
        deleteButton.setOnAction(event -> Delete(event));
        backButton.setOnAction(event -> Back(event));
    }






    @FXML
    private void Back(ActionEvent event) {
        // move to next scene
        try{ FXMLLoader Adopter_Scene2 = new FXMLLoader(getClass().getResource("Admin_scene2.fxml"));// define the root you want to switch to
            stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(Adopter_Scene2.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        }catch (IOException e) {
            System.err.println("Error loading Admin Scene2 : " + e.getMessage());
        }
    }




    @FXML
    private void Delete(ActionEvent event) {
        showNotification("The Pet is Deleted");

        pets.stream()
                .filter(p -> p.name.equals(pet_Name_Box.getValue()) && p.shelter_id==shelterId && p.type.equals(pet_Type_Box.getValue())&&p.isDeleted==false)
                .findFirst()
                .ifPresent(pet -> {
                    pet.isDeleted=true;
                });

        Back(event);


    }


    @FXML
    private void handle_PetTypeBox_Action(ActionEvent event) {


        if (pet_Type_Box.getValue() != null) {
            // Hide all elements except the shelterID text, pet type box and pet name box
            set_visability(false);
            pet_Name_Box.setVisible(true);
            pet_Name_Box.getItems().clear();


            // Load pets for the selected shelter and type
            pet_Name_Box.getItems().addAll(
                    pets.stream()
                            .filter(p -> p.shelter_id == shelterId && p.type.equals(pet_Type_Box.getValue()) && p.wasRequested == false && p.isDeleted== false)
                            .map(p -> p.name)
                            .collect(Collectors.toList())
            );
        }
    }

    @FXML
    private void handle_PetNameBox_Action(ActionEvent event) {
        // Show all elements when a pet name is selected
        if(pet_Type_Box.getValue() == null || pet_Name_Box.getValue() == null)
            set_visability(false);
        else
            set_visability(true);

        // Populate text fields with selected pet details
        pets.stream()
                .filter(p -> p.name.equals(pet_Name_Box.getValue())&& p.type.equals(pet_Type_Box.getValue()) && p.shelter_id==shelterId && !p.wasRequested && !p.isDeleted)
                .findFirst()
                .ifPresent(pet -> {
                    breedField.setText(pet.breed);
                    ageField.setText(pet.age);

                });
    }


    private void set_visability(boolean visable) {
        breedField.setVisible(visable);
        ageField.setVisible(visable);
        breedText.setVisible(visable);
        ageText.setVisible(visable);
        deleteButton.setVisible(visable);

    }
}