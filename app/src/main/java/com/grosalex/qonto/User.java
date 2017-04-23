package com.grosalex.qonto;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by grosalex on 23/04/17.
 */

public class User {
    private static final String TAG = "User";
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    public User(JSONObject json){
        Log.d(TAG, "User: "+json.toString());

        try {
            id=json.getInt("id");
            name=json.getString("name");
            username=json.getString("username");
            email=json.getString("email");
            phone=json.getString("phone");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static String getTAG() {
        return TAG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
