import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HandleClient extends Thread
{
  private Socket connection;
  private ObjectOutputStream output;
  private ObjectInputStream input;
  private String username;
  //final private ArrayList<String> nameList;
  private int my_id;
  private ArrayList<ObjectOutputStream> group;
  final private ArrayList<HandleClient> otherClients;

  // Thread constructor
  public HandleClient(Socket theConnection, ArrayList<HandleClient> otherClients, int id, ObjectOutputStream output, ObjectInputStream input) throws IOException{
    connection = theConnection;
    this.output = output;
    this.input = input;
    this.otherClients = otherClients;
    //nameList = list;
    String msg="Enter a username: ";
    output.writeObject(msg);
    output.flush();
    try{
      username = (String) input.readObject();
    }
    catch(ClassNotFoundException e){
      print("Unknown object received\n");
    }
    my_id = id;
    print("Username from client: " + username+"\n");
    print(username+" has id number "+id +"\n");
    //nameList.add(username);
    print("Displaying Available usernames:\n");
    //printList();
    group = new ArrayList<ObjectOutputStream>();

  }

  // Server reads new messages from the client and will print them
  // on the server terminal .
  // Client remains connected until EXIT message is received.
  public void run(){
    try{
      String fromClient = "";
      do{
        try{
          fromClient = (String) input.readObject();
          String message = username+": "+fromClient;
          println(message);
          sendToGroup(message);
        }
        catch(ClassNotFoundException e){
          print("Unknown object received\n");
        }
        /*
        if(fromClient.equals("LIST")){
          printList();
          output.writeObject("List of Users:\n");
          output.flush();
          for(int i = 0; i<nameList.size(); i++){
            output.writeObject(nameList.get(i));
            output.flush();
          }
        }
        */

      }while(!fromClient.equals("EXIT"));

      // Server recieves EXIT, echo it back to Client
      output.writeObject("EXIT");
      group.clear();
      leaveGroup(output);
      output.flush();

      // Remove username from server's list of users connected
      //nameList.remove(username);

      // Client exited from connection to server. Close streams.
      otherClients.remove(this);
      input.close();
      output.close();
      connection.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }

  public void print(String s){
    System.out.printf(s);
  }

  public void println(String s){
    System.out.println(s);
  }
/*
  private void printList(){
    System.out.println(nameList);
  }
*/

  public void test(){
    System.out.println("The Thread object has been called to invoke test");
  }

  public ObjectOutputStream getOutputStream(){
    return this.output;
  }

  public String getUsername(){
    return username;
  }

  public void setUsername(String username){
    this.username = username;
  }

  public void addToGroup(ObjectOutputStream newMember){
    if(newMember != output && !group.contains(newMember)){
      group.add(newMember);
    }

  }

  public void removeFromGroup(ObjectOutputStream member){
    group.remove(member);
  }

  public void leaveGroup(ObjectOutputStream removeMe){
    for(int i = 0; i < otherClients.size(); i++){
      if(otherClients.get(i).getGroup().contains(removeMe)){
        otherClients.get(i).removeFromGroup(removeMe);
      }
    }
  }

  public ArrayList<ObjectOutputStream> getGroup(){
    return group;
  }

  public void sendToGroup(String message) throws IOException{
    for(int i = 0; i < group.size(); i++){
      if(group.get(i) != output){
        group.get(i).writeObject(message);
        group.get(i).flush();
      }
    }
  }

}
