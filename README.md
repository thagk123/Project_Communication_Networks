Για το συγκεκριμένο project χρησιμοποίησα την τεχνολογία RMI. Στον φάκελο src υπάρχουν:
Το interface Server_ClientInt το οποίο χρησιμοποιείται για να μπορούν να επικοινωνούν μεταξύ τους ο client με τον
server στο οποίο βρίσκονται οι μέθοδοι που χρησιμοποιούνται στην υλοποίηση της σύνδεσης και των request των client.
Επίσης, στον φάκελο src υπάρχουν και οι παρακάτω κλάσεις:
RemoteServer_Client: Η υλοποίηση του interface στον Server. Σε αυτήν υλοποιούνται όλες οι κλάσεις του Sever_ClientInt.
MessagingServer: Σε αυτή την κλάση δημιουργείται ο server.
MessagingClient: Σε αυτή την κλάση ο client ξεκινάει την επικοινωνία του με τον server και ζητάει ενα request απο αυτόν.
Έπειτα αναλόγως με το τι έχει πληκτρολογήσει ο client γίνονται κάποιοι έλεγχοι και εμφανίζεται μια απάντηση στον Client.
Account και Message: Οι συγκεκριμένες κλάσεις βοηθούν στην καλύτερη λειτουργία του project και στη διαχείριση των 
μηνυμάτων και των λογαριασμών του συστήματος.
Οι λειτουργίες που υλοποιούνται στο interface είναι οι εξής:
CreateAccount: Ο client μπορεί να δημιουργήσει τον λογαριασμό του λαμβάνοντας ενα token που χρησιμοποιεί στα επόμενα 
requests του.
ShowAccounts: Ο client μπορεί να δεί τα ονόματα των λογαριασμών που υπάρχουν στο σύστημα.
SendMessage: Ο client στέλνει ενα μήνυμα σε κάποιον άλλον client.(Δεν μπορεί να στείλει μήνυμα στον εαυτό το!)
ShowInbox: Ο client μπορεί να δει τα μηνύματα του.
ReadMessage: Ο client μπορεί να διαβάσει ενα συγκεκριμένο μήνυμα απο αυτά που έχει λάβει.
DeleteMessage: O client μπορεί να διαγράψει ενα συγκεκριμένο μήνυμα απο αυτά που έχει λάβει.
Γενικότερα η ιδέα του project είναι ότι δημιουργείται ένα σύστημα όπου πολλοί και διαφορετικοί clients με τη βοήθεια
και την επικοινωνία ενός server μπορούν να δημιουργούν λογαριασμούς και να επικοινωνούν μεταξύ τους (οι Clients)
στέλνοντας, διαβάζοντας και διαγράφοντας μηνύματα. Ακόμα μπορούν να δούν και ποίοι λογαριασμοί υπάρχουν στο σύστημα.
Τα .jar αρχεία λειτουργούν κανονικά.

For this project, I used RMI technology. In the src folder, the following components are included:
Server_ClientInt Interface: This interface facilitates communication between the client and the server. It contains the methods used for implementing the connection and handling the client requests.
Additionally, the src folder contains the following classes:
RemoteServer_Client: This is the implementation of the Server_ClientInt interface on the server side. All the methods defined in Server_ClientInt are implemented here.
MessagingServer: This class is responsible for creating the server.
MessagingClient: In this class, the client initiates communication with the server and submits a request. Depending on the input provided by the client, certain checks are performed, and a corresponding response is displayed to the client.
Account and Message: These classes support the project's functionality by managing system accounts and messages more effectively.
The operations implemented in the Server_ClientInt interface include:
CreateAccount: The client can create an account and receive a token, which is used for subsequent requests.
ShowAccounts: The client can view the account names present in the system.
SendMessage: The client can send a message to another client (sending messages to oneself is not allowed!).
ShowInbox: The client can view their received messages.
ReadMessage: The client can read a specific message from their inbox.
DeleteMessage: The client can delete a specific message from their inbox.
General Idea of the Project
The project creates a system where multiple clients can interact with the help of a server. Clients can create accounts and communicate with each other by sending, reading, and deleting messages. They can also view the list of accounts in the system.
The .jar files function correctly.
