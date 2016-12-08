import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

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
					Login window = new Login();
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
	public Login() {
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
		textField.setBounds(49, 73, 125, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(184, 76, 100, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(184, 109, 100, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(49, 106, 125, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogOn = new JButton("Log on");
		btnLogOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogOn.setBounds(49, 137, 89, 23);
		frame.getContentPane().add(btnLogOn);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration form=new Registration();
				form.setVisible(true);
				setVisible(false);
			}
		});
		btnRegister.setBounds(148, 137, 125, 23);
		frame.getContentPane().add(btnRegister);
	}
}
