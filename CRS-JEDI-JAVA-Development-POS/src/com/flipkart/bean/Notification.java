package com.flipkart.bean;

public class Notification {

    private int studentId;
    private int notificationId;
    private String notificationMessage;
    private String referenceId;

    public Notification(){

    }
    public Notification(int studentId, int notificationId, String notificationMessage, String referenceId) {
        this.studentId = studentId;
        this.notificationId = notificationId;
        this.notificationMessage = notificationMessage;
        this.referenceId = referenceId;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

}
