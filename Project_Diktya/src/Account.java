package com.example;

import java.util.List;
import java.util.ArrayList;

public class Account implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    
    private final String username;
    private final List<Message> messageBox;

    public Account(String username, int authToken) {
        this.username=username;
        this.messageBox= new ArrayList<>();
    }

    public void addMessage(Message mess){
        messageBox.add(mess);
    }

    public void deleteMessage(Message mess){
        messageBox.remove(mess);
    }

    public String getUsername() {return username;}

    public List<Message> getMessageBox() {return messageBox;}
}
