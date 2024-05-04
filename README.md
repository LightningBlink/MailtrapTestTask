Example of usage such client

        var attachment = Attachment.builder()
                .setContent("MjEzMjEz")
                .setType("text/plain")
                .setFilename("Filename")
                .build();

        var email = MailtrapEmail.builder()
                .setText("text")
                .setSubject("subject")
                .setHtml("<html></html>")
                .setFrom(User.builder().setName("Admin").setEmail("mailtrap@demomailtrap.com").build())
                .setTo(Collections.singletonList(User.builder().setName("Admin").setEmail("ivanpoppov88@gmail.com").build()))
                .setAttachment(Collections.singletonList(attachment))
                .build();

        MailTrapClient.create("{your_tokep_api}").send(email);

Units tested added.
