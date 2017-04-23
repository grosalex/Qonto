package com.grosalex.qonto;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by grosalex on 23/04/17.
 */

public class User implements Parcelable {
    private static final String TAG = "User";
    private String website;
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
            website=json.getString("website");
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

    public String getWebsite() {
        return website;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);

    }

    public User(Parcel in) {
        id=in.readInt();


    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
