package com.perera.android_app2;


import java.io.Serializable;

public class UserModel implements Serializable {

    private String _id,name, email, contactNo, password;

    public UserModel() {
    }

    public UserModel(String id, String name, String email, String contactNo) {
        this._id = id;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}





