# 💬 Messaging System with RMI

📚 University project for the course **Communication Networks**.  

This project implements a **messaging system** using **Java RMI (Remote Method Invocation)**, enabling multiple clients to communicate with each other through a central server.  
Clients can create accounts, send, read, and delete messages, as well as view all existing accounts in the system.

---

## 📂 Project Structure (src folder)

### 📌 Interface
- **`Server_ClientInt`**  
  Facilitates communication between the client and the server.  
  Defines the methods for implementing connections and handling client requests.  

### 📌 Classes
- **`RemoteServer_Client`**: Server-side implementation of `Server_ClientInt`. Implements all interface methods.  
- **`MessagingServer`**: Responsible for creating and running the server.  
- **`MessagingClient`**: Client-side class where communication with the server begins.  
  - The client sends requests to the server.  
  - Based on the input, checks are performed, and responses are returned.  
- **`Account` & `Message`**: Utility classes for managing system accounts and messages.  

---

## ⚙️ Operations Implemented in `Server_ClientInt`
- **`CreateAccount`**:  
  Client creates an account and receives a token for subsequent requests.  

- **`ShowAccounts`**:  
  Client can view all existing account names in the system.  

- **`SendMessage`**:  
  Client sends a message to another client (cannot send messages to themselves).  

- **`ShowInbox`**:  
  Client views all received messages.  

- **`ReadMessage`**:  
  Client reads a specific message from their inbox.  

- **`DeleteMessage`**:  
  Client deletes a specific message from their inbox.  

---

## 🌐 General Idea
The project simulates a **multi-client messaging system** where:  
- Multiple clients interact via a server.  
- Clients can create accounts, communicate through messages, and manage their inbox.  
- The system also provides visibility of all accounts.  

---

## 📦 Deployment
- The provided **`.jar` files** run correctly and enable the full functionality of the system.  

---
