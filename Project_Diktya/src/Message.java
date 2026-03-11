package com.example.messaging;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private boolean readStatus;
    private final String sender;
    private final String body;
    private final int id;

    public Message(String sender, String receiver, String body, Account acc) {
        this.readStatus = false;
        this.sender = sender;
        this.body = body;
        int min = 0;
        int max = 100;
        this.id = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (!acc.getMessageBox().isEmpty()) {
            for (int i = 0; i < acc.getMessageBox().size(); i++) {
                Message mess = acc.getMessageBox().get(i);
                if (mess.getId() == this.id) {
                    this.id = (int) Math.floor(Math.random() * (max - min + 1) + min);
                    i = -1;
                }
            }
        }
    }

    public void setRead(boolean readStatus) {
        this.readStatus = readStatus;
    }
    
    public boolean isRead() {
        return readStatus;
    }

    public String getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }

    public int getId() {
        return id;
    }
}
