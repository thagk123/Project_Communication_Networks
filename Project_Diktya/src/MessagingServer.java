package com.messaging.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.server.ExportException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MessagingServer {
    private static final Logger LOGGER = Logger.getLogger(MessagingServer.class.getName());
    
    public static void main(String[] args) {
        try {
            RemoteServer_Client stub = new RemoteServer_Client();
            Registry rmiRegistry = LocateRegistry.createRegistry(5000);
            rmiRegistry.rebind("Server_Client", stub);
            LOGGER.info("java server 5000");
            LOGGER.info("Server started");
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "RMI error occurred while starting server", e);
        } catch (ExportException e) {
            LOGGER.log(Level.SEVERE, "Export error occurred while starting server", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.SEVERE, "Server startup was interrupted", e);
        }
    }
}
