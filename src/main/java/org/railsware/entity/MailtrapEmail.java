package org.railsware.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MailtrapEmail {

    @SerializedName("from")
    private final User from;
    @SerializedName("to")
    private final List<User> to;
    @SerializedName("subject")
    private final String subject;
    @SerializedName("text")
    private final String text;
    @SerializedName("html")
    private final String html;
    @SerializedName("attachments")
    private final List<Attachment> attachments;

    public MailtrapEmail(final User from,
                         final List<User> to,
                         final String subject,
                         final String text,
                         final String html,
                         final List<Attachment> attachments) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.html = html;
        this.attachments = attachments;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private User from;

        private List<User> to;

        private String subject;

        private String text;

        private String html;

        private List<Attachment> attachments;


        public static Builder builder(){
            return new Builder();
        }

        public Builder setFrom(final User from) {
            this.from = from;
            return this;
        }

        public Builder setTo(final List<User> to) {
            this.to = to;
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setHtml(String html) {
            this.html = html;
            return this;
        }

        public Builder setAttachment(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        public MailtrapEmail build() {
            return new MailtrapEmail(this.from,
                    this.to,
                    this.subject,
                    this.text,
                    this.html,
                    this.attachments);
        }
    }
}
