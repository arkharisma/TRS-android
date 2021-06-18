package com.velxoz.finalproject.entity.auth.signup;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpRequest {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private List<String> role;
    @SerializedName("username")
    private String username;

    public SignUpRequest() {
    }

    public SignUpRequest(String firstName, String lastName, String mobileNumber, String password, List<String> role, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
