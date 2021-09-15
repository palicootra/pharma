package com.example.ProjetFInal.utility;


public class Result {
    private String message;
    private int code;

    public Result(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public Result() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
