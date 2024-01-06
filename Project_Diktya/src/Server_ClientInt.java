import java.rmi.*;
import java.util.ArrayList;
import java.util.HashMap;


public interface Server_ClientInt extends Remote {

      public HashMap<Integer,Account> accs = new HashMap<>();

      public HashMap<Integer,Account> getAccs() throws RemoteException;

      public int CreateAccount(String username) throws RemoteException;

      public String SendMessage(String message, String username, int authToken,Account account) throws RemoteException;

      public ArrayList<Message> ShowInbox(int authToken) throws RemoteException;

      public String ReadMessage(int authToken, int message_id) throws RemoteException;

      public String  DeleteMessage(int authToken, int message_id) throws RemoteException;
}