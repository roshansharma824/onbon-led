package com.stackfusion.onbon.data;

public class Footer{
    private String color;
    private String error_code;
    private String message;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_code() {
        return error_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}