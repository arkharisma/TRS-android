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
    private Object object;

    public GetResponse() {
    }

    public GetResponse(Boolean success, String message, List<DataGetResponse> data, Object object) {
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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
