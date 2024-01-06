public class Message implements java.io.Serializable {
    private boolean isRead;
    private String sender;
    private String receiver;
    private String body;
    private int id;

    public Message(String sender, String receiver, String body, Account acc) {
        this.isRead=false;
        this.sender=sender;
        this.receiver=receiver;
        this.body=body;
        int min = 0;
        int max = 100;
        this.id = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (!acc.getMessageBox().isEmpty()) {
            for (int i = 0; i<acc.getMessageBox().size() ;i++) {
                Message mess=acc.getMessageBox().get(i);
                if (mess.getId() == this.id) {
                    this.id = (int) Math.floor(Math.random() * (max - min + 1) + min);
                    i = -1;
                }
            }
        }
    }

    public void setRead(boolean isRead) {this.isRead=isRead;};
    public boolean getIsRead() {return isRead;}

    public String getSender() {return sender;}

    public String getBody() {return body;}

    public int getId() {return id;}
}