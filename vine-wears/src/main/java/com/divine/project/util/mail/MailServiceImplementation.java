package com.divine.project.util.mail;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;


public class MailServiceImplementation {


    public MailjetResponse sendMail(){


        return null;
    }


    public static void main(String[] args) throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;

        client = new MailjetClient(System.getenv("6d41470778ec09b5b0fbe15a08a5957e"), System.getenv("04b8fb854d3a3dbf36aa036aed66a4a3"), new ClientOptions("v3.1"));

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "divineobi13@gmail.com")
                                        .put("Name", "Divine"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "divineobi13@gmail.com")
                                                .put("Name", "Divine")))
                                .put(Emailv31.Message.SUBJECT, "Greetings from Vine wears.")
                                .put(Emailv31.Message.TEXTPART, "My first Mailjet email")
                                .put(Emailv31.Message.HTMLPART, "<h3>Dear customer 1, welcome to <a href='https://www.mailjet.com/'>Mailjet</a>!</h3><br /> We hope to serve you well!")
                                .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
