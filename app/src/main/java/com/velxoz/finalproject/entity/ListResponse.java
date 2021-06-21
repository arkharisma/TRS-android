package com.velxoz.finalproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse<T> {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<T> data = null;
    @SerializedName("object")
    private String object;

    public ListResponse() {
    }

    public ListResponse(Boolean success, String message, List<T> data, String object) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.object = object;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
