package com.velxoz.finalproject.entity.ticket;

import com.google.gson.annotations.SerializedName;
import com.velxoz.finalproject.entity.user.RoleResponse;

import java.util.List;

public class Owner {
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
    private List<RoleResponse> roles = null;

    public Owner() {
    }

    public Owner(String id, String username, String firstName, String lastName, String mobileNumber, List<RoleResponse> roles) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.roles = roles;
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

    public List<RoleResponse> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleResponse> roles) {
        this.roles = roles;
    }
}
