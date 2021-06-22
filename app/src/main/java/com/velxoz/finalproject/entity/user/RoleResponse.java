package com.velxoz.finalproject.entity.user;

import com.google.gson.annotations.SerializedName;

public class RoleResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public RoleResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
