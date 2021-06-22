package com.velxoz.finalproject.entity.ticket;

import com.google.gson.annotations.SerializedName;

public class Agency {
    @SerializedName("id")
    private String id;
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("details")
    private String details;
    @SerializedName("owner")
    private Owner owner;

    public Agency() {
    }

    public Agency(String id, String code, String name, String details, Owner owner) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.details = details;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
