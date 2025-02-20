package com.example.petproj;


import javax.swing.text.StyledEditorKit;

public class Adopter extends User {
    private int ID;
    private String password;
    private static int adopterCounter = 0;
    public boolean isDeleted;
    // should have an array of previously adopted pets (to be implemented later)
    public Adopter(){
        super();
    }

    public Adopter(String name, String userName, String password, String email, String phoneNum, boolean deleted) {
        super(name, userName, email, phoneNum);
        adopterCounter++;
        this.ID = adopterCounter;
        this.password = password;
        this.isDeleted = deleted;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        if(email == null) {
            return "Adopter has no Email set";
        }
        return email;
    }

    // ahmed abdelgawad : added to edit password
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        if(phoneNum == null) {
            return "Adopter has no Phone Num set";
        }
        return phoneNum;
    }

    public static int getAdopterCounter() {
        return adopterCounter;
    }

    // NOTE : the same order of data will be stored in files
    @Override
    public String toString() {
        return ID + "," + name + "," + userName + "," + password + "," + email + "," + phoneNum + "," + isDeleted;
    }
}
