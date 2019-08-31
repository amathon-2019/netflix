package com.example.netflix.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

import java.io.IOException;

public class EmailSender {

    static final String FROM = "giopaik@naver.com";
    private String TO;
    private String SUBJECT;
    // The HTML body for the email.
    private String HTMLBODY;
    // The email body for recipients with non-HTML email clients.
    private String TEXTBODY;

    public EmailSender(String TO, String SUBJECT, String HTMLBODY, String TEXTBODY){
        this.TO = TO;
        this.SUBJECT = SUBJECT;
        this.HTMLBODY = HTMLBODY;
        this.TEXTBODY = TEXTBODY;
    }

    public void sendEmail() throws IOException {

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            // Replace US_WEST_2 with the AWS Region you're using for
                            // Amazon SES.
                            .withRegion(Regions.US_WEST_2).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(TO))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(HTMLBODY))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(FROM);
                    // Comment or remove the next line if you are not using a
                    // configuration set
                    //.withConfigurationSetName(CONFIGSET);
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }
}
