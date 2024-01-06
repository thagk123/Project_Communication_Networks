import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class RemoteServer_Client extends UnicastRemoteObject implements Server_ClientInt  {
    public RemoteServer_Client() throws RemoteException {
        super();
    }

    public HashMap<Integer,Account> getAccs() throws RemoteException {return accs;}

    public int CreateAccount(String username) throws RemoteException{
        int authToken;
        int i=0;
        int min = 0;
        int max = 100;
        authToken = (int)Math.floor(Math.random() * (max - min + 1) + min);
        if (!accs.isEmpty()){
            while (i==0){
                if (accs.containsKey(authToken)){
                    authToken = (int)Math.floor(Math.random() * (max - min + 1) + min);
                }
                else {i=1;}
            }
        }
        Account acc=new Account(username,authToken);
        accs.put(authToken,acc);
        return authToken;
    }

    public String SendMessage(String message, String username, int authToken,Account acc) throws RemoteException{
         Message mess=new Message(accs.get(authToken).getUsername(),username,message,acc);
        for (Account account : accs.values()) {
            if (account.getUsername().equals(username)){
                account.addMessage(mess);
                break;
            }
        }
         return "OK";
    }

    public ArrayList<Message> ShowInbox(int authToken) throws RemoteException{
        return accs.get(authToken).getMessageBox();
    }

    public String ReadMessage(int authToken, int message_id) throws RemoteException{
       for (int i=0;i<accs.get(authToken).getMessageBox().size();i++){
          if(accs.get(authToken).getMessageBox().get(i).getId()==message_id){
              String a="("+accs.get(authToken).getMessageBox().get(i).getSender()+")  "+accs.get(authToken).getMessageBox().get(i).getBody();
              accs.get(authToken).getMessageBox().get(i).setRead(true);
              return a;
          }
       }
       return "Message ID does not exist";
    }

    public String  DeleteMessage(int authToken, int message_id) throws RemoteException{
        for (int i=0;i<accs.get(authToken).getMessageBox().size();i++){
            if(accs.get(authToken).getMessageBox().get(i).getId()==message_id){
                accs.get(authToken).deleteMessage(accs.get(authToken).getMessageBox().get(i));
                return "OK";
            }
        }
        return "Message ID does not exist";
    }

}