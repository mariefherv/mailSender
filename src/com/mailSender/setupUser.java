package com.mailSender;

/**
 * Constructs the sender's credentials.
 */

public class setupUser {
    private final String user;
    private final String password;

    /**
     * Constructor for the sender's credentials
     * @param user Sender's email
     * @param password Sender's password
     */
    public setupUser(String user, String password) {
        this.user = user;
        this.password = password;

    }

    //getters
    public String getUser(){
        return user;
    }
    public String getPassword(){
        return password;
    }



}
