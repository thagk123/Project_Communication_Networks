package com.messaging.server;

import java.rmi.*;
import java.util.List;
import java.util.Map;


public interface Server_ClientInt extends Remote {

      public Map<Integer,Account> getAccs() throws RemoteException;

      public int createAccount(String username) throws RemoteException;

      public String sendMessage(String message, String username, int authToken,Account account) throws RemoteException;

      public List<Message> showInbox(int authToken) throws RemoteException;

      public String readMessage(int authToken, int messageId) throws RemoteException;

      public String deleteMessage(int authToken, int messageId) throws RemoteException;
}
