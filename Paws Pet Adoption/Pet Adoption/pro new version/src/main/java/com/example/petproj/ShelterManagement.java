package com.example.petproj;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



import static com.example.petproj.PetRequest_Adopter_Controller.showNotification;
import static com.example.petproj.LoginController.admin_id;

public class ShelterManagement {

    @FXML
    private TextField shelterNameField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    public static ArrayList<Shelter> shelter = new ArrayList<Shelter>();
    private static final String shelterFilesPath = "./src/main/resources/files/";
    //ArrayList<Pets> pets

    private static String adminName, adminUserName, adminPassword, adminEmail, adminPhone;

    @FXML
    public void handleNext(ActionEvent event) {
        // Get input data
        String shelterName = shelterNameField.getText();
        String location = locationField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        // Validate input
        if (shelterName.isEmpty() || location.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showNotification( "All fields must be filled!");
            return;
        }

        if(shelter.stream().anyMatch(shelter1 -> shelter1.id != admin_id  && shelter1.name.equals(shelterName))) {
            showNotification("Shelter name already exists");
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


        UserManagement.addUser(adminName, adminUserName, adminPassword, adminEmail, adminPhone, "admin");
        ShelterManagement.addshelter(shelterName, location,phone, email);

        // Show success message
        showNotification( "Shelter's Data saved successfully!");
        Back(event);

    }


    public static void loadFilesData() {
        //  this.pets = pets;
        try(
                BufferedReader ShelterReader = new BufferedReader(new FileReader(shelterFilesPath + "shelter.txt")))

        {
            String line;
            while ((line = ShelterReader.readLine()) != null) {
                String[] singleShelterData = line.split(",");

                if (singleShelterData.length != 5) {
                    System.err.println("Invalid data format: " + line);
                    continue;
                }
                Shelter newShelter = new Shelter(singleShelterData[1], singleShelterData[2], singleShelterData[3] ,singleShelterData[4]);
                shelter.add(newShelter);
            }
            ShelterReader.close();
        } catch(IOException error)

        {
            System.out.println(error.getMessage());
        }
    }
    // int shelter_id,
    public static void addshelter(String name, String location, String contact_info,String email) {


        Shelter newShelter = new Shelter( name,location, contact_info, email);
        shelter.add(newShelter);

    }
    public static void saveToFiles() {
        try(BufferedWriter shelterWriter = new BufferedWriter(new FileWriter(shelterFilesPath + "shelter.txt", false))) {
            for (Shelter shelter : shelter) {
                shelterWriter.write(shelter.toString());
                shelterWriter.newLine();
            }
            shelterWriter.close();

        } catch (IOException e) {
            System.out.println("Writing to shelter file failed");
        }


    }
    @FXML
    private void Back(ActionEvent event) {

        try{
            FXMLLoader loginScene = new FXMLLoader(getClass().getResource("login.fxml"));// define the root you want to switch to
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // handling the switch event
            Scene scene = new Scene(loginScene.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        }catch (IOException e) {
            System.err.println("Error loading login scene : " + e.getMessage());
        }

    }




    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\d{11}$");
    }

    public static void carryAdminInfo(String name, String username, String password, String email,String phonenumber) {
        adminName = name;
        adminUserName = username;
        adminEmail = email;
        adminPassword = password;
        adminPhone = phonenumber;
    }
}



