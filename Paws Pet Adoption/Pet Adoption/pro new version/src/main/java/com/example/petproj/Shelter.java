package com.example.petproj;

public class Shelter {
    public int id ;
    public String name ;
    public String location;
    public String Email;
    public String phonenumber;
    private static int ShelterCounter = 0;

    public Shelter( String name, String location, String phonenumber, String email) {
        ShelterCounter++;
        this.id = ShelterCounter;
        this.name = name;
        this.location = location;
        this.phonenumber = phonenumber;
        this.Email = email;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + location + "," + phonenumber + "," + Email;
    }
}