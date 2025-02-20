package com.example.petproj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AdoptionManagement {

    public static ArrayList<Adoption> adoptions = new ArrayList<Adoption>();


    // load adoption process from "adoption.txt"
    public static void loadFilesData(String AdoptionDataFileName) {
        // load admins
        try (BufferedReader adoptionReader = new BufferedReader(new FileReader("src/main/resources/files/" + AdoptionDataFileName))) {
            String line;
            while ((line = adoptionReader.readLine()) != null) {

                String[] singleAdoptionData = line.split(",");
                Adoption newAdoption = new Adoption(singleAdoptionData[1], singleAdoptionData[2], singleAdoptionData[3], singleAdoptionData[4], singleAdoptionData[5], singleAdoptionData[6], singleAdoptionData[7], singleAdoptionData[8],singleAdoptionData[9],Integer.parseInt(singleAdoptionData[10]), Boolean.parseBoolean(singleAdoptionData[11]), Boolean.parseBoolean(singleAdoptionData[12]));
                adoptions.add(newAdoption);


            }
            adoptionReader.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }



    // save adoption process to "adoption.txt"
    public static void saveToFiles() {
        try(BufferedWriter adoptionWriter = new BufferedWriter(new FileWriter( "src/main/resources/files/Adoption.txt", false))) {
            for (Adoption adoption : adoptions) {
                adoptionWriter.write(adoption.toString());
                adoptionWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Writing to Adoption file failed");
        }}

    public static void addAdoption(Adoption adoption) {
        adoptions.add(adoption);
    }



}
