package com.b1707b.cours.resto_app.reclerview;

public class Email {
    private String nameSender;
    private String dateSend;
    private String object;
    private String messageContent;
    private Boolean isRead=false;
    public Email(String nameSender, String dateSend, String object, String messageContent, boolean isRead) {
        this.nameSender = nameSender;
        this.dateSend = dateSend;
        this.object = object;
        this.messageContent = messageContent;
        this.isRead = isRead;
    }

    public boolean getRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Email(String nameSender, String dateSend, String object, String messageContent) {
        this.nameSender = nameSender;
        this.dateSend = dateSend;
        this.object = object;
        this.messageContent = messageContent;
    }
}
