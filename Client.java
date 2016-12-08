
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client {
	private int NumUsers;
	protected String ClientId=null;
	private JFrame frame;
	private JTextField textField;

	// Networking Variables
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket clientSocket;
	private String message;
	private String addr = null; // Allow IP address?
	private Scanner scan;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
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
	public Client() {
		initialize();
		this.frame.setVisible(false);
		// Start Networking Connection
		try{
			startClient();
		}
		catch(IOException io){
			io.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 230, 259, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 0, 414, 217);
		frame.getContentPane().add(textArea);

		JButton btnNewButton = new JButton("Leave");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnNewButton.setBounds(289, 229, 114, 23);
		frame.getContentPane().add(btnNewButton);
	}
	// Connect to the Server
	private void startClient() throws IOException{
		try{
			print("Connecting to the server...\n");
			if(addr == null){
				clientSocket = new Socket(InetAddress.getByName("127.0.0.1"), 4120);
			}else{
				clientSocket = new Socket(InetAddress.getByName(addr), 4120);
			}
			print("Connected to Server!\n");

			output = new ObjectOutputStream(clientSocket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(clientSocket.getInputStream());



			// Server sends it's salutation. Read the message and display it.
			try{
				String message = (String) input.readObject();
				print(message);
			}
			catch(ClassNotFoundException e){
				print("Object unidentified\n");
			}
			ClientIO thread = new ClientIO(input, output);
			thread.start();
			scan = new Scanner(System.in);
			do{
				// Get String from standard in
				message = scan.nextLine();
				//message+="\n";
				output.writeObject(message);
				output.flush();
			}while(!message.equals("EXIT"));
			output.close();
			input.close();
			clientSocket.close();
		}
		catch(EOFException eof){
			print("Client terminated unexpectedly\n");
		}
	}

	public void showClient(){
		this.frame.setVisible(true);
	}
	public void close(){
		this.frame.dispose();
		MainServer.showServer();
	}
	private void print(String s){
		System.out.printf(s);
	}
}
