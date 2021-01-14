package com.mailSender;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * Creates a session for sending the emails.
 *
 * All thanks to https://www.javatpoint.com/example-of-sending-email-using-java-mail-api
 * for providing the necessary dependencies and of course, the code samples!!!
 */

public class sendMail extends setupUser{
    public sendMail(String user, String password) {
        super(user,password);
    }

    /**
     * Sends email when called
     * @param toReceive Recipient email
     * @param msg Message content
     * @param subject Subject content
     */
    public void send(String toReceive, String msg, String subject){
            //getting session object
            Properties props = new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");       //I will use gmail because it's what I use.
            props.put("mail.smtp.port",587);                    //You can change this to 25 if it isn't working.
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");


            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(getUser(),getPassword());
                        }
                    });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(getUser()));
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(toReceive));
                message.setSubject(subject);
                message.setText(msg);

                Transport.send(message);

                System.out.println("Message sent successfully to " + toReceive + ".");

            } catch (MessagingException e) {e.printStackTrace();}
        }

    }
