package com.velxoz.finalproject.entity.stop;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetResponse {
    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<DataGetResponse> data = null;
    @SerializedName("object")
    private String object;

    public GetResponse() {
    }

    public GetResponse(Boolean success, String message, List<DataGetResponse> data, String object) {
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

    public List<DataGetResponse> getData() {
        return data;
    }

    public void setData(List<DataGetResponse> data) {
        this.data = data;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
