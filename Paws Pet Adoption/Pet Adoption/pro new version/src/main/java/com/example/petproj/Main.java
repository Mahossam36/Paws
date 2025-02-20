package com.example.petproj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        UserManagement.loadFilesData("admin.txt", "adopter.txt");
        AdoptionManagement.loadFilesData("Adoption.txt");
        Petmanagement.loadFilesData();
        ShelterManagement.loadFilesData();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SplashScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Paws");
        stage.getIcons().add(new Image("/images/petcareIcon.png"));
        stage.setScene(scene);
        stage.setResizable(false);



        stage.setOnCloseRequest(event -> {
            UserManagement.saveToFiles();
            AdoptionManagement.saveToFiles();
            Petmanagement.saveToFiles();
            ShelterManagement.saveToFiles();
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}