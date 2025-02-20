package com.example.petproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import static com.example.petproj.LoginController.user_username;
import static com.example.petproj.LoginController.user_id;
import static com.example.petproj.UserManagement.adopters;
import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;

public class EditAccController implements Initializable  {

    @FXML
    private Button backBtn;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumField;
    @FXML
    private Button confirmChangesBtn;


    private String currentName;
    private String currentPassword;
    private String currentEmail;
    private String currentPhoneNum;

    private Stage stage;
    private Scene scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        confirmChangesBtn.setVisible(false);
        Adopter currentAdopter = null;

        for (Adopter ad : adopters) {
            if(ad.getUserName().equals(user_username) && ad.getID() == user_id) {
                currentAdopter = ad;
            }
        }

        
        nameField.setText(currentAdopter.getName());
        passField.setText(currentAdopter.getPassword());
        emailField.setText(currentAdopter.getEmail());
        phoneNumField.setText(currentAdopter.getPhoneNum());

        currentName = nameField.getText();
        currentPassword = passField.getText();
        currentEmail = emailField.getText();
        currentPhoneNum = phoneNumField.getText();


        nameField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled());
        passField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled());
        emailField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled());
        phoneNumField.textProperty().addListener((observable, oldValue, newValue) -> checkAllFieldsFilled());


    }

    private void checkAllFieldsFilled() {
       boolean allFilledAndAtLeastOneDiff = (!nameField.getText().trim().isEmpty() && !nameField.getText().equals(currentName)) ||
               (!passField.getText().trim().isEmpty() && !passField.getText().equals(currentPassword)) ||
               (!emailField.getText().trim().isEmpty() && !emailField.getText().equals(currentEmail)) ||
               (!phoneNumField.getText().trim().isEmpty() && !phoneNumField.getText().equals(currentPhoneNum));

       confirmChangesBtn.setVisible(allFilledAndAtLeastOneDiff);
    }



    @FXML
    private void confirmChangeHandle(ActionEvent event) {
        if(!emailField.getText().equals(currentEmail)) {
            if (isValidEmailFormat(emailField.getText())) {
                boolean doesEmailExistsForOtherUser = adopters.stream().anyMatch(adopter -> adopter.getID() != user_id && adopter.getEmail().equals(emailField.getText())&& !adopter.isDeleted);
                if (doesEmailExistsForOtherUser) {
                    showNotification("Entered Email Exists for Other User");
                    return;
                }
            } else {
                showNotification("Email Format Is Not Correct");
                return;
            }
        }

        if (!phoneNumField.getText().equals(currentPhoneNum)) {
            if (isValidPhoneNumber(phoneNumField.getText())) {
                boolean doesPhoneNumberExistsForOtherUser = adopters.stream().anyMatch(adopter -> adopter.getID() != user_id && adopter.getPhoneNum().equals(phoneNumField.getText())&& !adopter.isDeleted);
                if(doesPhoneNumberExistsForOtherUser) {
                    showNotification("Phone Num Is Used For Other User");
                    return;
                }
            } else {
                showNotification("Phone Num Format Is Incorrect");
                return;
            }
        }

        adopters.get(user_id-1).setName(nameField.getText());
        adopters.get(user_id-1).setPassword(passField.getText());
        adopters.get(user_id-1).setPhoneNum(phoneNumField.getText());
        adopters.get(user_id-1).setEmail(emailField.getText());

        showNotification("Info Updated Successfully");
        Back(event);
    }

    private boolean isValidEmailFormat(String email) {
        boolean validEmailFormat = email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

        return validEmailFormat;
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\d{11}$");
    }

    @FXML
    private void Back(ActionEvent event) {
        // move to next scene
        try{
            FXMLLoader Admin_Scene2 = new FXMLLoader(getClass().getResource("Adopter_Scene2.fxml"));// define the root you want to switch to
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // handling the switch event
            scene = new Scene(Admin_Scene2.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        }catch (IOException e) {
            System.err.println("Error loading Admin Scene2 : " + e.getMessage());
        }
    }


}
