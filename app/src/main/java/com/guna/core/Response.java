package com.guna.core;

/**
 * Created by Gunaseelan on 10-12-2015.
 * Response entity is used to get response from network operations.
 */
public class Response {

    private String result;
    private boolean status;
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
