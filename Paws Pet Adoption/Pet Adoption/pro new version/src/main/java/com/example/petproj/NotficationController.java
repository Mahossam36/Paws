package com.example.petproj;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.petproj.AdoptionManagement.adoptions;
import static com.example.petproj.LoginController.user_id;
import static com.example.petproj.ShelterManagement.shelter;

public class NotficationController implements Initializable {
    ArrayList<Adoption> filterdnotfication = new ArrayList<>();
    @FXML
    private ListView<Adoption> NotficationList ;
    private Node notification;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterdnotfication = (ArrayList<Adoption>) adoptions.stream() // filter the notification according to the id "must be unboxed bec of .collect(Collectors.toList()); creat a new type of list not arraylist
                .filter(adoption -> adoption.getAdopter().getID()==user_id && !adoption.getSeen())//search for a matched id
                .collect(Collectors.toList());//put it in a new list
        NotficationList.getItems().addAll(filterdnotfication);// add the items of the id of the user to the listView

        NotficationList.setCellFactory(parma-> new ListCell<>(){
            @Override
            protected void updateItem(Adoption notification, boolean empty){
                super.updateItem(notification, empty);
                if (empty || notification == null) {
                    setText(null);
                    setGraphic(null);
                }
                else{
                    VBox layout = new VBox(5);

                    Label titleLabel = new Label("Pet Adoption Response From : " + shelter.get(notification.getPet().shelter_id-1).name); // Adoption.getShelter Returns null ????
                    Label messageLabel = new Label("Your Request For : "+notification.getPet().name+" Is "+notification.getStatus());
                    layout.getChildren().addAll(titleLabel, messageLabel);
                    setGraphic(layout);
                }
            }
        });
        filterdnotfication.stream()
                .filter(adoption -> adoption.getAdopter().getID()==user_id)
                .forEach(adoption -> adoption.setSeen(true));
    }
}
