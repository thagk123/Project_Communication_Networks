import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class MessagingClient{
    public static void main(String args[]) {
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry(5000);
            Server_ClientInt stub = (Server_ClientInt) rmiRegistry.lookup("Server_Client");
            System.out.println("Your request must start with 'java client localhost 5000'");
            Scanner sc = new Scanner(System.in);
            String line = null;
                System.out.println("Please write below your request");
                line=sc.nextLine();
                String[] words = line.split(" ");
                if (words[0].equals("java") && words[1].equals("client") && words[2].equals("localhost") && words[3].equals("5000") && words.length>=6 && Integer.parseInt(words[4])>=1 && Integer.parseInt(words[4])<=6) {
                    if (words[4].equals("1")) {
                        int g = 0;
                        for (Account account : stub.getAccs().values()) {
                            if (account.getUsername().equals(words[5])) {
                                System.out.println("Sorry, the user already exists");
                                g = 1;
                                break;
                            }
                        }
                        int k = 0;
                        if (g == 0) {
                            char[] chars = words[5].toCharArray();
                            for (char c : chars) {
                                if (!Character.isLetter(c)) {
                                    System.out.println("Invalid Username");
                                    k = 1;
                                    break;
                                }
                            }
                        }
                        if (k == 0 && g == 0) {
                            int authToken = stub.CreateAccount(words[5]);
                            System.out.println("Your authToken is:" + authToken);
                        }
                    } else if (words[4].equals("2")) {
                        int i = 1;
                        if (stub.getAccs().containsKey(Integer.parseInt(words[5]))) {
                            for (Account account : stub.getAccs().values()) {
                                System.out.println(i + ". " + account.getUsername());
                                i++;
                            }
                        } else {
                            System.out.println("authToken not found");
                        }
                    } else if (words[4].equals("3")) {
                        if (stub.getAccs().containsKey(Integer.parseInt(words[5])) && !stub.getAccs().get(Integer.parseInt(words[5])).getUsername().equals(words[6])) {
                            int i = 0;
                            for (Account account : stub.getAccs().values()) {
                                if (account.getUsername().equals(words[6])) {
                                    String s = words[7] + " ";
                                    for (int k = 8; k < words.length; k++) {
                                        s = s + words[k] + " ";
                                    }
                                    System.out.println(stub.SendMessage(s, words[6], Integer.parseInt(words[5]),account));
                                    i = 1;
                                    break;
                                }
                            }
                            if (i == 0) {
                                System.out.println("User does not exist");
                            }
                        } else {
                            if(stub.getAccs().containsKey(Integer.parseInt(words[5])) && stub.getAccs().get(Integer.parseInt(words[5])).getUsername().equals(words[6])){
                                System.out.println("You cannot send a message to yourself");
                            }
                            else {System.out.println("authToken not found");}
                        }
                    } else if (words[4].equals("4")) {
                        if (stub.getAccs().containsKey(Integer.parseInt(words[5]))) {
                            if (stub.ShowInbox(Integer.parseInt(words[5])).isEmpty()) {
                                System.out.println("The messagebox is empty");
                            } else {
                                ArrayList<Message> mess = stub.ShowInbox(Integer.parseInt(words[5]));
                                for (Message m : mess) {
                                    if (m.getIsRead()) {
                                        System.out.println(m.getId() + ". from:" + m.getSender());
                                    } else {
                                        System.out.println(m.getId() + ". from:" + m.getSender() + "*");
                                    }
                                }
                            }
                        } else {
                            System.out.println("authToken not found");
                        }
                    } else if (words[4].equals("5")) {
                        if (stub.getAccs().containsKey(Integer.parseInt(words[5]))) {
                            System.out.println(stub.ReadMessage(Integer.parseInt(words[5]), Integer.parseInt(words[6])));
                        } else {
                            System.out.println("authToken not found");
                        }
                    } else if (words[4].equals("6")) {
                        if (stub.getAccs().containsKey(Integer.parseInt(words[5]))) {
                            System.out.println(stub.DeleteMessage(Integer.parseInt(words[5]), Integer.parseInt(words[6])));
                        } else {
                            System.out.println("authToken not found");
                        }
                    }
                }
                else {System.out.println("Wrong request format");}
                } catch (Exception e){
                System.out.println(e);
        }
    }
}