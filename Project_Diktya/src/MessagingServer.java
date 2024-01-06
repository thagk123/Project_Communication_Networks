import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessagingServer{
    public static void main(String args[]) {

                try {
                    RemoteServer_Client stub = new RemoteServer_Client();
                    Registry rmiRegistry = LocateRegistry.createRegistry(5000);
                    rmiRegistry.rebind("Server_Client", stub);
                    System.out.println("java server 5000");
                    System.out.println("Server started");
                } catch (Exception e) {
                    System.out.println(e);
                }
        }
    }