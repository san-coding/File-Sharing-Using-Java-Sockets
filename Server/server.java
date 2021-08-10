import java.io.*;
import java.net.*;
import java.util.Scanner;

class server {
  public static void main(String[] args) {
    try {

      ServerSocket ss = new ServerSocket(3000);
      System.out.println();
      System.out.println("Waiting for connection... ");

      Socket s = ss.accept();

      System.out.println("Connection established with client");

      System.out.println("Waiting for client to send the file...");

      DataInputStream din = new DataInputStream(s.getInputStream());
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());

      ///  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      byte b[] = new byte[20002];

      din.read(b, 0, b.length);
      System.out.println("File received from the client");

      FileOutputStream fr = new FileOutputStream("data.csv");

      fr.write(b, 0, b.length);

      fr.close();
      System.out.println();
      System.out.println("File received from the client contains the following data : \n");

      // Creating the Updated Data file

      File newFile = new File("temp.csv");
      String name = "", department = "", salary = "";
      try {
        Scanner x = new Scanner(new File("data.csv"));
        x.useDelimiter("[,\n]");

        while (x.hasNext()) {

          name = x.next();
          department = x.next();
          salary = x.next();
          System.out.println(name + "," + department + "," + salary);

          try {

            int updatedSalary = Integer.parseInt(salary);
            updatedSalary = (int)(updatedSalary + (int)(updatedSalary * 0.1));
            String textToAppend = name + "," + department + "," + String.valueOf(updatedSalary) + "\n";
            BufferedWriter writer = new BufferedWriter(
              new FileWriter("temp.csv", true));

            writer.write(textToAppend);
            writer.close();
          } catch (Exception e) {
            String textToAppend = name + "," + department + "," + salary + "\n";
            BufferedWriter writer = new BufferedWriter(
              new FileWriter("temp.csv", true));

            writer.write(textToAppend);
            writer.close();
          }

        }

        x.close();

      } catch (Exception e) {
        System.out.println();
        System.out.println("File has been updated : Salary increased by 10%");

      }

      System.out.println("Sending updated file back to client...");

      // Sending updated file back to client
      FileInputStream fstr = new FileInputStream("temp.csv");
      byte sendToClient[] = new byte[20002];
      fstr.read(sendToClient, 0, sendToClient.length);
      dout.write(sendToClient, 0, sendToClient.length);
      dout.flush();
      fstr.close();

      System.out.println("Updated file sent back to client.\n");

      // Deleting old file and renaming temp file

      File file = new File("temp.csv");
      File file2 = new File("data.csv");
      boolean delete = file2.delete();
      boolean success = file.renameTo(file2);

      s.close();
      ss.close();

    } catch (Exception e) {
      System.out.println(e);
      System.out.println("Lost Connection");
    }
  }

}