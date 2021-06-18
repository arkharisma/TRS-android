package com.velxoz.finalproject.entity.auth.signin;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignInResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName("roles")
    private List<String> roles = null;
    @SerializedName("tokenType")
    private String tokenType;
    @SerializedName("accessToken")
    private String accessToken;

    public SignInResponse() {
    }

    public SignInResponse(String id, String username, String firstName, String lastName, String mobileNumber, List<String> roles, String tokenType, String accessToken) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.roles = roles;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
