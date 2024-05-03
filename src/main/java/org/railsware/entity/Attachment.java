package org.railsware.entity;

import com.google.gson.annotations.SerializedName;

public class Attachment {

    @SerializedName("content")
    private final String content;
    @SerializedName("type")
    private final String type;
    @SerializedName("filename")
    private final String filename;
    @SerializedName("disposition")
    private final String disposition;
    @SerializedName("content_id")
    private final String contentId;

    Attachment(String content, String type, String filename, String disposition, String contentId) {
        this.content = content;
        this.type = type;
        this.filename = filename;
        this.disposition = disposition;
        this.contentId = contentId;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String content;

        private String type;

        private String filename;

        private String disposition;

        private String contentId;


        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setFilename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder setDisposition(String disposition) {
            this.disposition = disposition;
            return this;
        }

        public Builder setContentId(String contentId) {
            this.contentId = contentId;
            return this;
        }

        public Attachment build() {
            return new Attachment(this.content, this.type, this.filename, this.disposition, this.contentId);
        }
    }
}
