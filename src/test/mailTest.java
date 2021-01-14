package test;

import com.mailSender.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class mailTest {

    public validateEmail forTest;       //classes we're going to use
    public extractEmail sample;
    public createSubject subject;


    @BeforeEach
    void setUp() {
        forTest = new validateEmail();      //objects that will be used all throughout the test
        sample = new extractEmail();
        subject = new createSubject();
    }

    @Test
    @DisplayName("Valid Email")             //checks if email is valid
    void validateEmail() {
        String email = "sample@gmail.com";
        boolean valid = forTest.check(email);

        assertTrue(valid);
    }

    @Test
    @DisplayName("Invalid number of chars (<6)")
    void validateEmailInvalidCharLessThanSix() {
        String email = "a@gmail.com";
        boolean valid = forTest.check(email);

        assertFalse(valid);
    }

    @Test
    @DisplayName("Invalid number of chars (>30)")
    void validateEmailInvalidCharMoreThanThirty() {
        String email = "thissamplehasmorethan30charslolmybad@gmail.com";
        boolean valid = forTest.check(email);

        assertFalse(valid);
    }

    @Test
    @DisplayName("Invalid domain (no '@')")
    void validateEmailInvalidNoAt() {
        String email = "sample";
        boolean valid = forTest.check(email);

        assertFalse(valid);
    }

    @Test
    @DisplayName("Extracting info from spreadsheet")
    void extractInfo() {
        String file = "Sample.xlsx";
        sample.ReadCellData(2, 8, file);
    }

    @Test
    @DisplayName("Verifying extracted mail")
    void verifyExtracted() {
        String file = "Sample.xlsx";
        boolean valid = forTest.check(sample.ReadCellData(2, 8, file));
        assertTrue(valid);
    }

    @Test
    @DisplayName("Verifying extracted mail: must be invalid")
    void verifyExtractedFail() {
        String file = "Sample.xlsx";
        boolean valid = forTest.check(sample.ReadCellData(6, 8, file));
        assertFalse(valid);
    }

    @Test
    @DisplayName("Try iteration")
    void printEmails() {
        int startRow = 2; //index based so always -1 when inputting row and column numbers
        int endRow = 7;
        int column = 8;

        while (startRow <= endRow) {
            String file = "Sample.xlsx";
            String email = sample.ReadCellData(startRow, column, file);
            if (forTest.check(email)) {
                System.out.println(email);
            }
            startRow += 1;
        }

    }

    @Test
    @DisplayName("Subject creator")
    void createSubjectTest() {
        String file = "Sample.xlsx";
        String correct = subject.GenerateSubject(sample.ReadCellData(2, 11, file));
        assertEquals(correct, "Contributing to Java Project");
    }

    @Test
    @DisplayName("Iterating pt 2")
    void iteratingSubjects() {
        int startRow = 2; //index based so always -1 when inputting row and column numbers
        int endRow = 7;
        int column = 11;
        String file = "Sample.xlsx";

        while (startRow <= endRow) {
            String sub = subject.GenerateSubject(sample.ReadCellData(startRow, column, file));
            if (forTest.check(sample.ReadCellData(startRow, 8,file))) {
                System.out.println(sub);
            }
            startRow += 1;
        }
    }
    @Test
    @DisplayName("Object")
    void createObjectTest(){
        String file = "Sample.xlsx";
        String email = sample.ReadCellData(3, 8, file);
        String sub = subject.GenerateSubject(sample.ReadCellData(3, 11,file));
        String name = sample.ReadCellData(3, 9, file);

        createObject object = new createObject(sub,email,name);
        String correct = "Sending " + object.subject + " to " + object.email;

        assertEquals(correct, "Sending Contributing to Marot to mariefher66@gmail.com");

    }

    @Test
    @DisplayName("Send Object")
    void sendingToObjectTest(){
        int startRow = 2; //index based so always -1 when inputting row and column numbers
        int endRow = 7;
        int columnForSubject = 11;
        int columnForEmail = 8;
        String file = "Sample.xlsx";

        while (startRow <= endRow) {
            String email = sample.ReadCellData(startRow, columnForEmail,file);
            String sub = subject.GenerateSubject(sample.ReadCellData(startRow, columnForSubject,file));
            String name = sample.ReadCellData(3, 9, file);
            createObject object = new createObject(sub,email,name);
            if (forTest.check(sample.ReadCellData(startRow, 8,file))) {
                System.out.println("Sending " + object.subject + " to " + object.email);
            }
            startRow += 1;
        }
    }
    @Test
    @DisplayName("User")
    void setUpUser(){
        //first, we're going to need a session object (host name, username, pw)
        String user = "javatestingforproj@gmail.com"; //an email i actually created for this purpose; you can check out for your self
        String password = "CMSC22Project";

        setupUser acc = new setupUser(user,password);
        assertEquals(acc.getUser(), "javatestingforproj@gmail.com");

    }
    @Test
    @DisplayName("User pw")
    void setUpUserPw(){
        //first, we're going to need a session object (host name, username, pw)
        String user = "javatestingforproj@gmail.com"; //an email i actually created for this purpose; you can check out for your self
        String password = "CMSC22Project";

        setupUser acc = new setupUser(user,password);
        assertEquals(acc.getPassword(), "CMSC22Project");

    }


    @Test
    @DisplayName("Sending Email")
    void sendEmailTest(){
        String recipient = "javatestingforproj@protonmail.com"; //password is still CMSC22Project
        String message = "This is a test";
        String sub = "Java test";

        String user = "javatestingforproj@gmail.com"; //an email i actually created for this purpose; you can check out for your self
        String password = "CMSC22Project";

        sendMail mail = new sendMail(user,password);

        mail.send(recipient,message,sub);

    }

    @Test
    @DisplayName("Send to multiple emails")
    void sendMultipleEmailTest(){
        String message = "Hi! love u :>>> \n\nrip with love";
        int startRow = 2; //index based so always -1 when inputting row and column numbers
        int endRow = 8;
        int columnForSubject = 11;
        int columnForEmail = 8;
        String file = "Sample.xlsx";

        String user = "javatestingforproj@gmail.com"; //an email i actually created for this purpose; you can check out for your self
        String password = "CMSC22Project";
        String host = "smtp.gmail.com"; //i'm going to use gmail smtp bc it's what i use

        sendMail mail = new sendMail(user,password);

        while (startRow <= endRow) {
            String email = sample.ReadCellData(startRow, columnForEmail, file);
            String sub = subject.GenerateSubject(sample.ReadCellData(startRow, columnForSubject, file));
            String name = sample.ReadCellData(3, 9, file);
            createObject object = new createObject(sub,email,name);
            if (forTest.check(sample.ReadCellData(startRow, 8, file))) {
                mail.send(email,message,sub);
            }
            startRow += 1;
        }

    }
    @Test
    @DisplayName("Object with Name")
    void createObjectTestWithName(){
        String file = "Sample.xlsx";
        String email = sample.ReadCellData(2, 8, file);
        String sub = subject.GenerateSubject(sample.ReadCellData(2, 11,file));
        String name = sample.ReadCellData(2, 9, file);

        createObject object = new createObject(sub,email,name);
        String correct = object.name;

        assertEquals(correct, "Java");
    }
    @Test
    @DisplayName("Object with Null Name")
    void createObjectTestWithNullName(){
        String file = "Sample.xlsx";
        String email = sample.ReadCellData(2, 8, file);
        String sub = subject.GenerateSubject(sample.ReadCellData(2, 11,file));
        String name = sample.ReadCellData(3, 9, file);

        createObject object = new createObject(sub,email,name);
        String correct = object.name;

        assertEquals(correct, "");
    }

}