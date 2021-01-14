package com.mailSender;

import java.util.Scanner;

/**
 * This project is a terminal-based program for sending emails extracted from an excel sheet.
 * This version of code only takes on smtp.gmail.com as host, therefore will only work if the
 * sender uses a gmail domain. You may however, revise the host on class sendMail.
 *
 *  Name: Mariefher Grace Z. Villanueva
 *  Course: BS Computer Science II
 *  Affiliation: University of the Philippines Visayas
 */


public class Main {

    public static void main(String[] args) throws Exception {
        // gets email
        validateEmail valid = new validateEmail();  //email validator
        System.out.println("Please sign in using your gmail account.\nEmail: ");
        Scanner mailObj = new Scanner(System.in);
        String user = mailObj.nextLine();

        if(!valid.check(user)){         //email is invalid if it has less than 3 chars or more than 30 or no '@'domain
            throw new Exception("Invalid email!");
        }

        //gets password
        System.out.println("Password: ");
        Scanner pwObject = new Scanner(System.in);
        String password = pwObject.nextLine();

        //gets name of the file
        System.out.println("Enter name of file (with extension .xlsx): ");
        Scanner obj = new Scanner(System.in);
        String file = obj.nextLine();

        //Enter range of rows you want to send
        System.out.println("From row: ");
        Scanner rowObj = new Scanner(System.in);
        int startRow = rowObj.nextInt()-1;
        int printRow = startRow + 1;        //just for printing purposes

        System.out.println("To: ");
        Scanner rowObjTwo = new Scanner(System.in);
        int endRow = rowObjTwo.nextInt()-1;
        int printRowTwo = endRow + 1;      //just for printing purposes


        //Sending emails
        int columnForSubject = 11;
        int columnForEmail = 8;
        int columnForName = 9;
        System.out.println("Extracting emails from rows " + printRow + " to " + printRowTwo + " of " + " file " + file + "...");


        //creating necessary objects in each class
        sendMail mail = new sendMail(user,password);
        extractEmail fromFile = new extractEmail();
        createSubject subject = new createSubject();

        //while iterating through excel file
        while (startRow <= endRow) {
            String email = fromFile.ReadCellData(startRow, columnForEmail, file);   //extracts email from excel file
            String sub = subject.GenerateSubject(fromFile.ReadCellData(startRow, columnForSubject, file));   //creates subject
            String name = fromFile.ReadCellData(startRow, columnForName, file);     //gets name if there is any
            String message;

            createObject object = new createObject(sub,email,name);  //create individual objects for each email
            if (valid.check(email)) { //check is email is valid, if not skip
                if(!object.name.equals("")){
                    //An actual template that I use for work
                    //if database contains name
                   message =
                            "Hi " + name + "," +
                            "\n\nMy name is <my boss' name> and I work as a blogger and content marketer in the property niche. \n" +
                            "\n" +
                            "In an effort to build online exposure, I'm looking for quality publications, such as yours, that I can partner with. " +
                            "Do you accept any form of advertisement on your blog?\n" +
                            "\n" +
                            "Ideally, I am looking to get a link from your site to one of mine (also a quality publication, relevant to your niche). " +
                            "That link doesn't need to be in a new article, you could simply add it to an old article already published on your site." +
                            "\n\nThanks\n<my boss' name>";
                } else {
                    //if not
                    message =
                            "Hi, " +
                            "\n\nMy name is <my boss' name> and I work as a blogger and content marketer in the property niche. \n" +
                            "\n" +
                            "In an effort to build online exposure, I'm looking for quality publications, such as yours, that I can partner with. " +
                            "Do you accept any form of advertisement on your blog?\n" +
                            "\n" +
                            "Ideally, I am looking to get a link from your site to one of mine (also a quality publication, relevant to your niche). " +
                            "That link doesn't need to be in a new article, you could simply add it to an old article already published on your site." +
                            "\n\nThanks\n<my boss' name>";
                }
                mail.send(email,message,sub);
            }
            startRow += 1;
        }


    }
}
