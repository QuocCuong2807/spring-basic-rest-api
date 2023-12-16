package com.springteam.TechProduct.dto;

//responsible for response message error to client (include: Http status, message exception, time)
public class ErrorResponeDTO {
    private int status;
    private String message;
    private long timeStamp;

    //constructor

    public ErrorResponeDTO(){}

    public ErrorResponeDTO(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    //getter & setter


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    //to string


    @Override
    public String toString() {
        return "ErrorResponeDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
