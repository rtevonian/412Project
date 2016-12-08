import java.awt.EventQueue;
import javax.swing.JApplet;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.net.InetAddress;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainServer extends JFrame implements Runnable{
	protected static int numUsers;
	private static JFrame frame;
	private JTextField textField;
	private ArrayList usernames=new ArrayList();
	private ArrayList clients=new ArrayList();
	private boolean validUsername;
	private boolean validChatname;
	static int index;
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private Thread outputThread;
	private ServerSocket server;
	private ExecutorService runServer;
	

	/**
	 * Launch the application.
	 */
	public static void main (String[] args) throws IOException{
		
	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainServer window = new MainServer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainServer() {
		initialize();
		runServer=Executors.newFixedThreadPool(15);
		try{
		server=new ServerSocket(12345,15);
		}
		catch(IOException e){
		e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox chatList = new JComboBox();
		chatList.setBounds(10, 5, 864, 20);
		frame.getContentPane().add(chatList);
		
		JTextArea Chatrooms = new JTextArea();
		Chatrooms.setBounds(10, 30, 326, 467);
		frame.getContentPane().add(Chatrooms);
		
		JButton btnEnterChat = new JButton("Enter Chatroom");
		btnEnterChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chatroom=(String) chatList.getSelectedItem();
				JOptionPane.showMessageDialog(null, chatroom);
			}
		});
		btnEnterChat.setBounds(346, 36, 140, 59);
		frame.getContentPane().add(btnEnterChat);
		btnEnterChat.setEnabled(false);
		
		JButton btnCreateChatroom = new JButton("Create Username");
		btnCreateChatroom.setEnabled(false);
		
		
		btnCreateChatroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validUsername){
				String chatname=textField.getText();
					if(chatname==""){
					JOptionPane.showMessageDialog(null, "chat name can not be blank");
					}
					else{
				chatList.addItem(chatname);
					if(chatList.getItemCount()>0)
						btnEnterChat.setEnabled(true);
				Chatrooms.append(textField.getText()+": Users "+ numUsers + "\n");
				textField.setText("");
				btnCreateChatroom.setEnabled(false);
				//Server newRoom=new Server();
				//newRoom.start();
				Client newClient= new Client();
				newClient.ClientId=(String)usernames.get(index);
				newClient.showClient();
				JOptionPane.showMessageDialog(null,newClient.ClientId);
				hideServer();
					}
				}
				else{
					String possUsername=textField.getText();
					if(usernames.contains(possUsername))
						JOptionPane.showMessageDialog(null,"Username already exists please use another");
					else{
						usernames.add(possUsername);
						JOptionPane.showMessageDialog(null,"Username Registered");
						validUsername=true;
						btnCreateChatroom.setEnabled(false);
						textField.setText("");
						btnCreateChatroom.setText("Create Chatroom");
					}
				}
				
				
					
				
			}
		});
		btnCreateChatroom.setBounds(693, 36, 137, 59);
		frame.getContentPane().add(btnCreateChatroom);
		
		JTextArea txtrToCreateA = new JTextArea();
		txtrToCreateA.setText("To create a username, enter a name in the above text box and click the button\r\n\r\nTo create a new chatroom, register a username first enter a chatroom name in\r\nthe above text box and then click the button\r\n\r\nTo join a pre-existing chatroom, please select one from\r\nthe drop down menu after regestering a username, then press the enter chatroom button");
		txtrToCreateA.setBounds(346, 106, 460, 244);
		frame.getContentPane().add(txtrToCreateA);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(textField.getText().isEmpty()==false)
					btnCreateChatroom.setEnabled(true);
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(textField.getText().isEmpty()==false)
					btnCreateChatroom.setEnabled(true);
					
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(textField.getText().isEmpty()==false)
					btnCreateChatroom.setEnabled(true);
					
				
			}});
		textField.setBounds(496, 59, 187, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		
		
		
		
	}
	public void start(){
		try{
		connection=new Socket(InetAddress.getByName("127.0.0.1"),6000);
		input=new DataInputStream(connection.getInputStream());
		output=new DataOutputStream(connection.getOutputStream());
		}
		catch(IOException e){
		e.printStackTrace();
		}
		outputThread=new Thread(this);
		outputThread.start();
	}
	public void hideServer(){
		this.frame.setVisible(false);
	}
	public static void showServer(){
		frame.setVisible(true);
	}
	public void execute(){
	while(true)
		users.set(index,new Client(server.accept(), index));
		index++;
	}
	public void run(){
		
	}
	
}
