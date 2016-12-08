
//this will be for the main server that creates chatrooms and prompts for screenname

//this comment is to test if comitt worked correctly

//another test comment

import java.awt.EventQueue;
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

public class MainServer extends JFrame {
	protected static int numUsers;
	private static JFrame frame;
	private JTextField txtPleaseEnterA;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		super("Main Hub");
		pack();
		initialize();
		Login screen= new Login();
		hideServer();
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
		Chatrooms.setEditable(false);
		Chatrooms.setBounds(10, 30, 326, 467);
		frame.getContentPane().add(Chatrooms);
		
		
		
		
		
		JButton btnCreateChatroom = new JButton("Create Chatroom");
		btnCreateChatroom.setEnabled(false);
		
		
		btnCreateChatroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatList.addItem(textField.getText());
				
				Chatrooms.append(textField.getText()+": Users "+ numUsers + "\n");
				textField.setText("");
				btnCreateChatroom.setEnabled(false);
				Client newRoom=new Client();
				newRoom.ClientId=textField.getText();
				newRoom.showClient();
				hideServer();
				
				
				
					
				
			}
		});
		btnCreateChatroom.setBounds(349, 36, 137, 67);
		frame.getContentPane().add(btnCreateChatroom);
		
		JTextArea txtrToCreateA = new JTextArea();
		txtrToCreateA.setEditable(false);
		txtrToCreateA.setText("To create a new chatroom, please enter a chatroom name in\r\nthe above text box and then click the button\r\n\r\nTo join a pre-existing chatroom, please select one from\r\nthe drop down menu ");
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
	public void hideServer(){
		this.frame.setVisible(false);
	}
	public static void showServer(){
		frame.setVisible(true);
	}
}

