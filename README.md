# File-Sharing-Using-Java-Sockets
Client creates and sends a file to the Server, Server updates the file and sends it back to the Client , using Java Socket Programming

## Commands to run the Application

Open two terminals side by side, one inside the **Client** folder containing the **client.java** file and the other terminal inside the **Server** folder containing  the **server.java** file.

### First run the Server
```
javac server.java
```

```
java server
```

### Run the Client
```
javac client.java
```

```
java client
```

## WORKFLOW

-	Server has started and is waiting for the client to connect.
-	Client Connects to the server.
-	Server is waiting for the client to send a CSV file
-	Client creates a CSV file named data.csv by entering the headers and the data
-	Client reads the data.csv and stores the data into a byte array[].
-	Client sends the byte array to the server and waits for the server’s response.
-	Server receives the byte array containing the data , sent by the client.
-	Server creates its own copy of data.csv and writes the data from the byte array to the data.csv.
-	Server reads its copy of data.csv line by line and writes each line into a new CSV file named temp.csv , after updating the salary.
-	Now the server has its own copy of data.csv and the updated salary file named temp.csv
-	Server deletes its copy of data.csv and renames the “temp.csv” to “data.csv”.
-	Server sends the updated copy of “data.csv” back to client.
-	Client receives the updated data in the form of a byte array.
-	Client creates a CSV file named updatedData.csv and writes the data from the received byte array into the updatedData.csv.
-	Now the client has the “data.csv” and the “updatedData.csv”.
-	Client deletes the old “data.csv” and renames the “updatedData.csv” to “data.csv”.
-	Now client has the file “data.csv” which contains the data of employees with salaries increased by 10%.

## Preview
<img width="1299" alt="Screenshot 2021-08-10 at 1 40 41 PM" src="https://user-images.githubusercontent.com/65719940/128832025-505083fe-bec0-4c79-bf2c-77b473658093.png">

