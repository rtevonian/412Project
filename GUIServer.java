import java.net.ServerSocket;
import java.io.IOException;
import java.util.ArrayList;
public class GUIServer{
	int index=0;
	ArrayList users=new ArrayList();
	ServerSocket server;
	public GUIServer() throws IOException{
		try{
	server=new ServerSocket(5000);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public void execute() throws IOException{
		try{
			while(true){
			users.set(index,new MainServer(server.accept(),this,index));
			index++;
			
			}
		}
		catch(IOException e){
			
		}
		
		
	}
	
}

	

