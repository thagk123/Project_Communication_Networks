import java.lang.reflect.Array;
import java.util.ArrayList;

public class Account implements java.io.Serializable{
    private String username;
    private int authToken;
    private ArrayList<Message> messageBox;

    public Account(String username, int authToken) {
        this.username=username;
        this.authToken=authToken;
        this.messageBox= new ArrayList<>();
    }

    public void addMessage(Message mess){
        messageBox.add(mess);
    }

    public void deleteMessage(Message mess){
        messageBox.remove(mess);
    }

    public String getUsername() {return username;}

    public ArrayList<Message> getMessageBox() {return messageBox;}
}
