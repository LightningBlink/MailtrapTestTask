package org.railsware.service;

import org.railsware.entity.MailtrapEmail;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public interface MailTrapClient {

    HttpResponse<String> send(MailtrapEmail email);

    <U> CompletableFuture<Void> sendAsync(MailtrapEmail email, Function<? super HttpResponse<String>, ? extends U> fn,
                                          Consumer<? super U> action);

    static MailTrapClient create(String token) {
        return new MailTrapClientImpl(token);
    }
}
