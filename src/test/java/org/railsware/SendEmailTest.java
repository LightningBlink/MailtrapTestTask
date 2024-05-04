package org.railsware;

import com.google.gson.Gson;
import org.railsware.entity.Attachment;
import org.railsware.entity.MailtrapEmail;
import org.railsware.entity.User;
import org.railsware.service.MailTrapClient;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class SendEmailTest {

    private MailtrapEmail email;
    private MailTrapClient mailTrapClient;


    @BeforeClass
    public void setup() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/test_obj.json"));
        Gson gson = new Gson();
        this.email = gson.fromJson(bufferedReader, MailtrapEmail.class);
        this.mailTrapClient = MailTrapClient.create("1e84be0b30c80ac8e4b7a26539b463cf");
    }


    @Test
    public void verifySend() {
        HttpResponse<String> response = this.mailTrapClient.send(this.email);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void verifySendAsync() {

        List<Integer> result = new LinkedList<>();

        CompletableFuture<Void> futureResponse = this.mailTrapClient.sendAsync(this.email, HttpResponse::statusCode, result::add);

        futureResponse.join();

        Assert.assertEquals(result.getFirst(), 200);
    }

    @Test
    public void verifySendViaConstructor() {
        var attachment = Attachment.builder()
                .setContent("MjEzMjEz")
                .setType("text/plain")
                .setFilename("Filename")
                .build();

        var email = MailtrapEmail.builder()
                .setText("dsadsa")
                .setSubject("fdsfdsfds")
                .setHtml("<html></html>")
                .setFrom(User.builder().setName("Admin").setEmail("mailtrap@demomailtrap.com").build())
                .setTo(Collections.singletonList(User.builder().setName("Admin").setEmail("ivanpoppov88@gmail.com").build()))
                .setAttachment(Collections.singletonList(attachment))
                .build();

        HttpResponse<String> response = this.mailTrapClient.send(email);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void verifySendWithoutMandatoryField() {

        var email = MailtrapEmail.builder()
                .setText("dsadsa")
                .setSubject("fdsfdsfds")
                .setTo(Collections.singletonList(User.builder().setName("Oleg").setEmail("dsadsa@bk.ru").build()))
                .build();

        HttpResponse<String> response = this.mailTrapClient.send(email);
        Assert.assertEquals(response.body(), "{\"errors\":[\"'from' is required\"]}");
    }
}
