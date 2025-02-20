package com.example.petproj;

public abstract class User {


    public String name;
    public String userName;
    public String email;
    public String phoneNum;
    public User(){}
    public User(String name, String userName, String email, String phoneNum) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.phoneNum = phoneNum;
    }




    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    // Ahmed Abdelgawad : added because in the adoption class you were changing the name of the adopter in the
    // adoption not the userName
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public abstract String getEmail();

    public abstract String getPhoneNum();

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
