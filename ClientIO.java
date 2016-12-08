import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


// Simple Thread that will listen for messages sent to the client
// and display them
public class ClientIO extends Thread{

  private ObjectInputStream input;
  private ObjectOutputStream output;

  public ClientIO(ObjectInputStream input, ObjectOutputStream output){
    this.input = input;
    this.output = output;
  }

  // Runs until EXIT message is received
  // Client types EXIT, server echos back
  public void run(){
    String message="";
    try{
      do{
        try{
          // Get the message
          message = (String)input.readObject();

          // Display the message
          System.out.println(message);
        }
        catch(ClassNotFoundException e){
          System.out.println("Unknown object received");
        }
      }while(!message.equals("EXIT"));
      input.close();
      output.close();
    }
      catch(IOException io){
        io.printStackTrace();
    }
  }
}
