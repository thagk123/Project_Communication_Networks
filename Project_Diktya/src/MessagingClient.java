package com.messaging.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MessagingClient {
    private static final Logger LOGGER = Logger.getLogger(MessagingClient.class.getName());
    private static final String AUTH_TOKEN_NOT_FOUND = "authToken not found";
    private static final String JAVA_COMMAND = "java";
    private static final String CLIENT_COMMAND = "client";
    private static final String LOCALHOST = "localhost";
    private static final String PORT = "5000";
    private static final String OPERATION_CREATE_ACCOUNT = "1";
    private static final String OPERATION_LIST_ACCOUNTS = "2";
    private static final String OPERATION_SEND_MESSAGE = "3";
    private static final String OPERATION_SHOW_INBOX = "4";
    private static final String OPERATION_READ_MESSAGE = "5";
    private static final String OPERATION_DELETE_MESSAGE = "6";
    
    public static void main(String args[]) {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry(5000);
            Server_ClientInt stub = (Server_ClientInt) rmiRegistry.lookup("Server_Client");
            LOGGER.info("Your request must start with 'java client localhost 5000'");
            Scanner sc = new Scanner(System.in);
            String line = null;
            LOGGER.info("Please write below your request");
            line = sc.nextLine();
            String[] words = line.split(" ");
            if (words[0].equals(JAVA_COMMAND) && words[1].equals(CLIENT_COMMAND) && words[2].equals(LOCALHOST)
                    && words[3].equals(PORT) && words.length >= 6 && Integer.parseInt(words[4]) >= 1
                    && Integer.parseInt(words[4]) <= 6) {
                if (words[4].equals(OPERATION_CREATE_ACCOUNT)) {
                    int g = 0;
                    for (Account account : stub.getAccs().values()) {
                        if (account.getUsername().equals(words[5])) {
                            LOGGER.info("Sorry, the user already exists");
                            g = 1;
                            break;
                        }
                    }
                    int k = 0;
                    if (g == 0) {
                        char[] chars = words[5].toCharArray();
                        for (char c : chars) {
                            if (!Character.isLetter(c)) {
                                LOGGER.info("Invalid Username");
                                k = 1;
                                break;
                            }
                        }
                    }
                    if (k == 0 && g == 0) {
                        int authToken = stub.CreateAccount(words[5]);
                        if (LOGGER.isLoggable(Level.INFO)) {
                            LOGGER.info("Your authToken is:" + authToken);
                        }
                    }
                } else if (words[4].equals(OPERATION_LIST_ACCOUNTS)) {
                    int i = 1;
                    if (stub.getAccs().containsKey(Integer.parseInt(words[5]))) {
                        for (Account account : stub.getAccs().values()) {
                            if (LOGGER.isLoggable(Level.INFO)) {
                                LOGGER.info(i + ". " + account.getUsername());
                            }
                            i++;
                        }
                    } else {
                        LOGGER.info(AUTH_TOKEN_NOT_FOUND);
                    }
                } else if (words[4].equals(OPERATION_SEND_MESSAGE)) {
                    if (stub.getAccs().containsKey(Integer.parseInt(words[5]))
                            && !stub.getAccs().get(Integer.parseInt(words[5])).getUsername().equals(words[6])) {
                        int i = 0;
                        for (Account account : stub.getAccs().values()) {
                            if (account.getUsername().equals(words[6])) {
                                StringBuilder sb = new StringBuilder(words[7]);
                                sb.append(" ");
                                for (int k = 8; k < words.length; k++) {
                                    sb.append(words[k]).append(" ");
                                }
                                String sendMessageResult = stub.SendMessage(sb.toString(), words[6], Integer.parseInt(words[5]), account);
                                LOGGER.info(sendMessageResult);
                                i = 1;
                                break;
                            }
                        }
                        if (i == 0) {
                            LOGGER.info("User does not exist");
                        }
                    } else {
                        if (stub.getAccs().containsKey(Integer.parseInt(words[5]))
                                && stub.getAccs().get(Integer.parseInt(words[5])).getUsername().equals(words[6])) {
                            LOGGER.info("You cannot send a message to yourself");
                        } else {
                            LOGGER.info(AUTH_TOKEN_NOT_FOUND);
                        }
                    }
                } else if (words[4].equals(OPERATION_SHOW_INBOX)) {
                    if (stub.getAccs().containsKey(Integer.parseInt(words[5]))) {
                        if (stub.ShowInbox(Integer.parseInt(words[5])).isEmpty()) {
                            LOGGER.info("The messagebox is empty");
                        } else {
                            ArrayList<Message> mess = stub.ShowInbox(Integer.parseInt(words[5]));
                            for (Message m : mess) {
                                if (m.getIsRead()) {
                                    if (LOGGER.isLoggable(Level.INFO)) {
                                        LOGGER.info(m.getId() + ". from:" + m.getSender());
                                    }
                                } else {
                                    if (LOGGER.isLoggable(Level.INFO)) {
                                        LOGGER.info(m.getId() + ". from:" + m.getSender() + "*");
                                    }
                                }
                            }
                        }
                    } else {
                        LOGGER.info(AUTH_TOKEN_NOT_FOUND);
                    }
                } else if (words[4].equals(OPERATION_READ_MESSAGE)) {
                    if (stub.getAccs().containsKey(Integer.parseInt(words[5]))) {
                        String readMessageResult = stub.ReadMessage(Integer.parseInt(words[5]), Integer.parseInt(words[6]));
                        LOGGER.info(readMessageResult);
                    } else {
                        LOGGER.info(AUTH_TOKEN_NOT_FOUND);
                    }
                } else if (words[4].equals(OPERATION_DELETE_MESSAGE)) {
                    if (stub.getAccs().containsKey(Integer.parseInt(words[5]))) {
                        String deleteMessageResult = stub.DeleteMessage(Integer.parseInt(words[5]), Integer.parseInt(words[6]));
                        LOGGER.info(deleteMessageResult);
                    } else {
                        LOGGER.info(AUTH_TOKEN_NOT_FOUND);
                    }
                }
            } else {
                LOGGER.info("Wrong request format");
            }
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "Remote exception occurred", e);
        } catch (NotBoundException e) {
            LOGGER.log(Level.SEVERE, "Service not bound in registry", e);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid number format", e);
        }
    }
}
