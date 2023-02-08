package com.flipkart.bean;
/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Notification, after Payment done
 *
 */
public class Notification {

    private int studentId;
    private int notificationId;
    private String notificationMessage;
    private String referenceId;

    /**
     * Default constructor
     */
    public Notification(){}

    /**
     * Constructor to return notification
     * @param studentId
     * @param notificationId
     * @param notificationMessage
     * @param referenceId
     */
    public Notification(int studentId, int notificationId, String notificationMessage, String referenceId) {
        this.studentId = studentId;
        this.notificationId = notificationId;
        this.notificationMessage = notificationMessage;
        this.referenceId = referenceId;
    }


    /**
     * Method to get student id
     * @return
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Method to set student id
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Method to get notification id
     * @return
     */
    public int getNotificationId() {
        return notificationId;
    }

    /**
     * Method to set notification id
     * @param notificationId
     */
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Method to get notification message
     * @return
     */
    public String getNotificationMessage() {
        return notificationMessage;
    }

    /**
     * Method to set notification message
     * @param notificationMessage
     */
    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    /**
     * Method to get reference id
     * @return
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Method to set reference id
     * @param referenceId
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

}
