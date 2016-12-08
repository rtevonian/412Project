import java.net.ServerSocket;
import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Thread;
import java.util.ArrayList;

public class Server{
  private ServerSocket server;
  private Socket connection;
  private ObjectOutputStream output;
  private ObjectInputStream input;
  final private ArrayList<String> nameList;
  private ArrayList<ObjectOutputStream> allClients;
  private int num_clients=0;

  public Server(){
    nameList = new ArrayList<String>();
    allClients = new ArrayList<ObjectOutputStream>();
    try{
      server = new ServerSocket(4120, 2048);
      while(true){
        print("Waiting for a connetion\n");

        connection = server.accept();
        print("Connection received!\n");
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        allClients.add(output);
        input = new ObjectInputStream(connection.getInputStream());
        HandleClient thread = new HandleClient(connection, nameList, num_clients, output, input);
        num_clients++;
        thread.start();
        output.writeObject("Test");
        System.out.printf("num_clients = %d\n", num_clients);
        if(num_clients > 1){
          String chat = "Users available to chat: ";
          for(int i = 0; i < allClients.size(); i++){
            allClients.get(i).writeObject(chat);
            allClients.get(i).flush();
          }
          for(int i = 0; i < allClients.size(); i++){
            for(int j = 0; j < nameList.size(); j++){
              allClients.get(i).writeObject(nameList.get(j));
              allClients.get(i).flush();
            }
          }

        }
      }
    }
    catch(IOException io){
      io.printStackTrace();
    }
  }

  void print(String s){
    System.out.print(s);
  }
}
