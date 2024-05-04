package org.railsware.service;

import com.google.gson.Gson;

import org.railsware.constraint.MailTrapConstraints;
import org.railsware.entity.MailtrapEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class MailTrapClientImpl implements MailTrapClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(MailTrapClientImpl.class);
    private final Gson gson;
    private final String token;
    private final HttpClient client;

    public MailTrapClientImpl(final String token) {
        this.token = token;
        this.gson = new Gson();
        this.client = HttpClient.newHttpClient();
    }

    @Override
    public HttpResponse<String> send(MailtrapEmail email) {
        try {
            return client.send(buildRequest(email), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Can't send request due to system problem!", e);
        }

        return null;
    }

    @Override
    public <U> CompletableFuture<Void> sendAsync(MailtrapEmail email, Function<? super HttpResponse<String>, ? extends U> fn,
                                                 Consumer<? super U> action) {
        return client.sendAsync(buildRequest(email), HttpResponse.BodyHandlers.ofString())
                .thenApply(fn)
                .thenAccept(action);

    }


    private HttpRequest buildRequest(MailtrapEmail email) {
        return HttpRequest.newBuilder()
                .uri(URI.create(MailTrapConstraints.MAILTRAP_HOST + MailTrapConstraints.SEND_EMAIL_PATH))
                .timeout(Duration.ofMinutes(2))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(email)))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Api-Token", this.token)
                .build();
    }
}
