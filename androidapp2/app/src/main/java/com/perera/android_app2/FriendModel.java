package com.perera.android_app2;

import java.io.Serializable;

import cz.msebera.android.httpclient.HttpEntity;

public class FriendModel  implements Serializable {
    private String _id,firstName, secondName, phone;


    public FriendModel() {
        this._id ="0";
//        this.firstName = firstName;
//        this.secondName = secondName;
//        this.phone = phone;
    }

    public FriendModel(String id, String firstName, String secondName, String phone) {
        this._id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
