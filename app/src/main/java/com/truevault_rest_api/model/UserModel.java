package com.truevault_rest_api.model;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.truevault_rest_api.manager.SharedPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ramesh on 9/1/16.
 */
public class UserModel {

    private static String TAG = UserModel.class.getName();
    public static String USER_ID = "user_id";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    public static String FULL_NAME = "full_name";
    public static String PHONE = "phone";
    public static String EMAIL = "email";
    public static String GENDER = "gender";

    private String userId;
    private String password;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("gender")
    private String gender;
    @SerializedName("image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static UserModel fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, UserModel.class);
    }
    public JSONObject toJson() {
        String jsonRepresentation = new Gson().toJson(this, UserModel.class);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonRepresentation);
        } catch (JSONException e) {
            Log.e(TAG, "Error converting to JSON : " + e.getMessage());
        }
        return jsonObject;
    }

    public void storeUserInSp() {
        storeUserInSp(this);
    }
    public static void storeUserInSp(UserModel user) {
        SharedPreferenceManager.getInstance().putString(FULL_NAME, user.getFullName());
        SharedPreferenceManager.getInstance().putString(PHONE, user.getPhone());
        SharedPreferenceManager.getInstance().putString(EMAIL, user.getEmail());
        SharedPreferenceManager.getInstance().putString(GENDER, user.getGender());
    }
}
