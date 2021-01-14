package com.mailSender;

/**
 * Generates subject based from the information extracted from the Excel sheet
 */

public class createSubject{
    public String GenerateSubject(String websiteName) {
        return "Contributing to " + websiteName;
    }



}
