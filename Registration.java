import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Registration extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
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
	public Registration() {
		initialize();
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
		textField.setBounds(49, 55, 116, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(49, 108, 116, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(175, 58, 100, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(175, 111, 100, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblPasswordMustBe = new JLabel("Password must be at\r\nleast 6 characters");
		lblPasswordMustBe.setBounds(49, 139, 300, 20);
		frame.getContentPane().add(lblPasswordMustBe);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				private String username = textField.getText();
				private String password = passwordField.getText();
			}
		});
		btnRegister.setBounds(49, 170, 100, 23);
		frame.getContentPane().add(btnRegister);
	}

}
