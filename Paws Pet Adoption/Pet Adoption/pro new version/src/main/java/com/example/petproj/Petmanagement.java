package com.example.petproj;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class Petmanagement {
    public static ArrayList<Pets> pets = new ArrayList<Pets>();
    private static final String petsFilesPath = "./src/main/resources/files/";

        public static void loadFilesData(){

        try (
                BufferedReader petReader = new BufferedReader(new FileReader(petsFilesPath + "pets.txt"))) {
            String line;
            while ((line = petReader.readLine()) != null) {
                String[] singlePetData = line.split(",");
                Pets newPets = new Pets(singlePetData[1], singlePetData[2], singlePetData[3], singlePetData[4], singlePetData[5],singlePetData[6],Boolean.parseBoolean(singlePetData[7]),Boolean.parseBoolean(singlePetData[8]));
                pets.add(newPets);
            }
            petReader.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void addpet(String shelter_id, String name, String breed, String age, String health_status, String type,boolean wasRequested,boolean isDeleted) {

        Pets newPet = new Pets(name, breed, age, health_status, type,shelter_id,wasRequested,isDeleted);
        pets.add(newPet);

    }


    public static void saveToFiles() {
        try (BufferedWriter petsWriter = new BufferedWriter(new FileWriter(petsFilesPath + "pets.txt", false))) {
            for (Pets pets : pets) {
                petsWriter.write(pets.toString());
                petsWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Writing to pets file failed");
        }





    }



}



