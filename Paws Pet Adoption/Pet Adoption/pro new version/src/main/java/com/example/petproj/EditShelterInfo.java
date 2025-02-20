package com.example.petproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.petproj.LoginController.admin_id;
import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;
import static com.example.petproj.ShelterManagement.shelter;


public class EditShelterInfo implements Initializable {


    @FXML
    private TextField shelterNameField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;


    private Shelter selectedShelter;





    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedShelter = shelter.stream().filter(shelter -> shelter.id==admin_id).findFirst().get();
        shelterNameField.setText(selectedShelter.name);

        emailField.setText(selectedShelter.Email);
        if(emailField.getText().equals("*")) {
            emailField.setText("");
        }

        locationField.setText(selectedShelter.location);
        phoneField.setText(selectedShelter.phonenumber);

    }



    @FXML
    public void handleUpdate(ActionEvent event) {
        if (selectedShelter == null) {
            showNotification("No shelter selected for editing.");
            return;
        }

        String newName = shelterNameField.getText();
        String location = locationField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        // Validate input
        if (location.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showNotification("All fields must be entered");
            return;
        }

        if (!isValidEmail(email)) {
            showNotification("Invalid email format.");
            return;
        } else {
            boolean otherShelterWithSameEmailFound =  shelter.stream().anyMatch(shelter1 -> shelter1.id != admin_id  && shelter1.Email.equals(email));

            if(otherShelterWithSameEmailFound) {
                showNotification("Email Exists for other Shelter");
                return;
            }
        }

        if (!isValidPhoneNumber(phone)) {
            showNotification("Phone num must be 11 digits.");
            return;
        }else {
            boolean otherShelterWithSamePhone =  shelter.stream().anyMatch(shelter1 -> shelter1.id != admin_id  && shelter1.phonenumber.equals(phone));

            if(otherShelterWithSamePhone) {
                showNotification("Phone num already exists");
                return;
            }
        }

        // Update the selected shelter's information (except name)
        selectedShelter.name = newName;
        selectedShelter.location = location;
        selectedShelter.Email = email;
        selectedShelter.phonenumber = phone;


        // Show success notification
        showNotification("Shelter information edited successfully!");

        // Navigate back to the shelter management screen
        Back(event);
    }



    @FXML
    private void Back(ActionEvent event) {
        // move to next scene
        try{ FXMLLoader Adopter_Scene2 = new FXMLLoader(getClass().getResource("Admin_scene2.fxml"));// define the root you want to switch to
           Stage stage= (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            Scene scene = new Scene(Adopter_Scene2.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        }catch (IOException e) {
            System.err.println("Error loading Admin Scene2 : " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\d{11}$");
    }


}
