package com.velxoz.finalproject.entity.auth.signup;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Object data;
    @SerializedName("object")
    private Object object;

    public SignUpResponse() {
    }

    public SignUpResponse(Boolean success, String message, Object data, Object object) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
