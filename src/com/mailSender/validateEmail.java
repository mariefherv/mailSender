package com.mailSender;

/**
 * Validates email under conditions that there are 3-30 characters before the '@' domain and if it includes @ char itself.
 */

public class validateEmail {

    public boolean check(String email) {
        //An email must have 6-30 chars based on gmail
        //Update: changed it to 3 because there are emails (especially work emails) that allow it

        //Finding the index of char 'a' to take out length of user ex: sample in 'sample@gmail.com'
        if (email.contains("@")) {
            int ind = email.indexOf("@");
            String user = email.substring(0, ind);

            return user.length() >= 3 && user.length() <= 30;
        } else {
            return false;
        }

    }

}


