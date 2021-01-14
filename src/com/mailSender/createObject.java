package com.mailSender;

/**
 * Creates an object that takes value of certain cells as attributes for each row
 */

public class createObject {
    public String subject;
    public String email;
    public String name;

    /**
     * Constructor
     * @param subject Takes generated subject
     * @param email Takes the recipient email
     * @param name Takes the website name
     */
    public createObject(String subject, String email, String name) {
        this.subject = subject;
        this.email = email;
        this.name = name;
    }


}
