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
import java.util.ArrayList;

public class MainServer {
	protected static int numUsers;
	private static JFrame frame;
	private JTextField textField;
	private ArrayList usernames=new ArrayList();
	private boolean validUsername;
	private boolean validChatname;

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
		initialize();
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
<<<<<<< HEAD

		txtPleaseEnterA = new JTextField();
		txtPleaseEnterA.setText("Please enter a Username");
		txtPleaseEnterA.setBounds(10, 530, 326, 20);
		frame.getContentPane().add(txtPleaseEnterA);
		txtPleaseEnterA.setColumns(10);

		JButton btnCreateChatroom = new JButton("Create Chatroom");
=======
		
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
>>>>>>> 1745f75b153f21cb8abb453bd510c5a31bf20c0b
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
				Client newRoom=new Client();
				newRoom.ClientId=textField.getText();
				newRoom.showClient();
				hideServer();
<<<<<<< HEAD





=======
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
				
				
					
				
>>>>>>> 1745f75b153f21cb8abb453bd510c5a31bf20c0b
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
<<<<<<< HEAD

=======
					
				
>>>>>>> 1745f75b153f21cb8abb453bd510c5a31bf20c0b
			}});
		textField.setBounds(496, 59, 187, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
<<<<<<< HEAD

=======
		
		
		
		
		
<<<<<<< HEAD
		
		
=======
>>>>>>> 1745f75b153f21cb8abb453bd510c5a31bf20c0b
>>>>>>> c606ccf10859532f87ce8a6b3c66b199260cd215
	}
	public void hideServer(){
		this.frame.setVisible(false);
	}
	public static void showServer(){
		frame.setVisible(true);
	}
	
}
