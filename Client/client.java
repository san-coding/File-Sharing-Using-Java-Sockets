import java.io.*;
import java.net.*;
import java.nio.file.Files;

class client {

  public static void main(String[] args) {
    try {
      Socket s = new Socket("localhost", 3000); // server IP , port
      System.out.println();
      System.out.println("Connection established with server");

      DataInputStream din = new DataInputStream(s.getInputStream());
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      File file = new File("data.csv");
      FileWriter fileWritter;

      String line = "";

      // keep reading until "Over" is input
      boolean headerPresent = false;
      DataInputStream input = new DataInputStream(System.in);

      // Creating and inputting data into csv
      System.out.println();
      System.out.println("Create the CSV file : ");

      while (!line.equals("Over")) {
        try {
          fileWritter = new FileWriter(file.getName(), true);

          BufferedWriter bw = new BufferedWriter(fileWritter);

          if (headerPresent == false) {
            System.out.print("Enter the headers : ");
            headerPresent = true;
          } else System.out.print("Enter the next row of data : ");
          line = input.readLine();

          if (!line.equals("Over")) bw.write(line + "\n");
          bw.close();

        } catch (IOException i) {
          System.out.println(i);
        }
      }
      System.out.println();
      System.out.println("Sending the file to the server to update the salary by 10%");

      // Reading data from the file created above
      // storing it into byte array and sending the byte array to server

      FileInputStream fr = new FileInputStream("data.csv");
      byte b[] = new byte[20002];
      fr.read(b, 0, b.length);
      dout.write(b, 0, b.length); // writing to server
      dout.flush();
      fr.close();

      System.out.println("Waiting for server's response...");

      // Receiving the updated file from server and storing it into byte array
      // storing the data from the byte array into a new csv

      byte fromServer[] = new byte[20002];
      din.read(fromServer, 0, fromServer.length); // Reading from server

      System.out.println("Updated file received from the server");

      FileOutputStream fstr = new FileOutputStream("updatedData.csv");
      fstr.write(fromServer, 0, fromServer.length);
      fstr.close();

      // Renaming updated file and deleting old file

      File f1 = new File("updatedData.csv");
      File f2 = new File("data.csv");
      boolean delete = f2.delete();
      boolean success = f1.renameTo(f2);

      String str = new String(fromServer);
      System.out.println();
      System.out.println("Updated file received from the server contains the following data : \n");

      System.out.println(str);

      s.close();

    } catch (Exception e) {
      System.out.println("Lost Connection");
    }
  }
}