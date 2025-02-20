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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.example.petproj.AdoptionManagement.adoptions;
import static com.example.petproj.LoginController.user_id;
import static com.example.petproj.LoginController.user_username;


public class MyOldRequests_Controller {
    @FXML
    private ImageView myimageview;
    @FXML
    private Button backbutton;
    @FXML
    private Button viewdetailsbutton;
    @FXML
    private ComboBox<String> pets_box;
    @FXML
    private Label adoption_label, breed_label, age_label, date_label, status_label;
    @FXML
    private TextField Breed_text, Age_text, Date_text, Status_text;



    public void initialize() {
        //hide details of pet
        set_visability(false);
        // show pets which accepted or rejected in Combobox
        pets_box.getItems().addAll(
                adoptions.stream().filter(adoption -> adoption.getAdopter().getID()==user_id && adoption.getStatus().equals("Accepted")||adoption.getStatus().equals("Rejected"))
                        .map(adoption -> adoption.get_Pet_type()+" "+ adoption.getPet().name) // Extract name of pet from each adoption process
                        .collect(Collectors.toList()) // Collect the mapped values into a List
        );
        backbutton.setOnAction(event -> Back2(event));

        viewdetailsbutton.setOnAction(event -> Details(event));


    }


    // to control the  visibility of details of pet
    private void set_visability(boolean b) {
        breed_label.setVisible(b);
        age_label.setVisible(b);
        date_label.setVisible(b);
        status_label.setVisible(b);
        Breed_text.setVisible(b);
        Age_text.setVisible(b);
        Date_text.setVisible(b);
        Status_text.setVisible(b);

    }

    @FXML
    private void Back2(ActionEvent event) {        // move to Adopter_scene2
        try {
            FXMLLoader Adopter_Scene2 = new FXMLLoader(getClass().getResource("Adopter_Scene2.fxml"));// define the root you want to switch to
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // handling the switch event
            Scene scene = new Scene(Adopter_Scene2.load()); // import the root to the scene
            stage.setScene(scene); // import the scene you want to show in the stage aka window bec the old scene holds the last root
            stage.show(); // show the stage
        } catch (IOException e) {
            System.err.println("Error loading Adopter Scene2 : " + e.getMessage());
        }

    }

    @FXML
    // to show details of pet when press the viewdetails button
    private void Details(ActionEvent event) {
        if(pets_box.getValue()==null)
            return;

        set_visability(true);

        adoptions.stream()
                //condition to show accepted or rejected pets only
                .filter(adoptions -> pets_box.getValue().contains(adoptions.getPet().name) && pets_box.getValue().contains(adoptions.get_Pet_type()) && adoptions.getStatus().equals("Accepted")||adoptions.getStatus().equals("Rejected") && adoptions.getAdopter().getID()==user_id )
                .findFirst()
                .ifPresent( adoptions -> {
                    Breed_text.setText(adoptions.getPet().breed);
                    Age_text.setText(adoptions.getPet().age);
                    Date_text.setText(adoptions.getAdoptionDate());
                    Status_text.setText(adoptions.getStatus());

                });

    }
}





