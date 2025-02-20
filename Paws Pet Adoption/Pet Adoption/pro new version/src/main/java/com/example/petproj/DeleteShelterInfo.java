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

import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;
import static com.example.petproj.ShelterManagement.shelter;
import static com.example.petproj.LoginController.admin_id;

public class DeleteShelterInfo implements Initializable {

    private Shelter selectedShelter;


    @FXML
    private ComboBox<String> fieldComboBox;

    @FXML
    private TextField delemailField;

    @FXML
    private TextField delphoneField;

    @FXML
    private TextField dellocationField;

    @FXML
    private Button deleteButton,back;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedShelter = shelter.stream().filter(shelter -> shelter.id==admin_id).findFirst().get();

        // Populate the field combo box
        fieldComboBox.getItems().addAll("Email", "Phone", "Location");

        delemailField.setText(selectedShelter.Email);
        delphoneField.setText(selectedShelter.phonenumber);
        dellocationField.setText(selectedShelter.location);

        if(delemailField.getText().equals("*")) {
            delemailField.setText("");
        }
    }


    @FXML
    public void handleDelete(ActionEvent event) {
        String selectedField = fieldComboBox.getValue();


        if (selectedField == null) {
            showNotification("Please select a field to delete.");
            return;
        }

        for (Shelter s : ShelterManagement.shelter) {
            if (s.id==admin_id) {
                switch (selectedField) {
                    case "Email":
                        s.Email = "*";
                        delemailField.clear();
                        showNotification("Email deleted successfully for");
                        break;
                    case "Phone":
                        s.phonenumber = "";
                        delphoneField.clear();
                        showNotification("Phone deleted successfully for");
                        break;
                    case "Location":
                        s.location = "";
                        dellocationField.clear();
                        showNotification("Location deleted successfully");
                        break;
                }
                break;
            }
        }

        Back(event);
    }

    private void clearFields() {
        delemailField.clear();
        delphoneField.clear();
        dellocationField.clear();
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
}
