package com.velxoz.finalproject.entity;

import com.google.gson.annotations.SerializedName;


public class ObjectResponse<T> {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private String data = null;
    @SerializedName("object")
    private T object;

    public ObjectResponse() {
    }

    public ObjectResponse(Boolean success, String message, String data, T object) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
