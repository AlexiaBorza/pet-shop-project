package main.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JPasswordField passwordField;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 735);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 117, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JLabel lblNewLabel = new JLabel("New label");
		//lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\luna2.jpg")); 
		//lblNewLabel.setBounds(220, 21, 188, 141);
		//Image image = originalIcon.getImage(); 
        //Image resizedImage = image.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        //ImageIcon resizedIcon = new ImageIcon(resizedImage);
		//contentPane.add(lblNewLabel);
		
		//JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(500, 500);
        //frame.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("New label");

        // Load the original image
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\luna.jpg");

        // Resize the image to fit the label's bounds
        Image image = originalIcon.getImage();
        Image resizedImage = image.getScaledInstance(188, 141, Image.SCALE_SMOOTH); // Resize to fit within the label bounds
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Set the resized image as the label's icon
        lblNewLabel.setIcon(resizedIcon);

        // Set the label's position and size using setBounds
        lblNewLabel.setBounds(220, 21, 179, 146);

        // Optionally, adjust the label's text position if needed
        //lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        //lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);   

        // Add the label to the content pane
        getContentPane().add(lblNewLabel);
        setVisible(true);

		
		JLabel lblNewLabel_1 = new JLabel("Welcome, Admin");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(206, 177, 228, 26);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(306, 299, 197, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(138, 391, 143, 36);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Username:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(138, 290, 143, 36);
		contentPane.add(lblNewLabel_3);
		
		btnNewButton = new JButton("Log In");
		btnNewButton.setBackground(new Color(0, 128, 192));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(245, 522, 154, 36);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(306, 401, 197, 26);
		contentPane.add(passwordField);
		
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username.equals("Luna") && password.equals("LunaIsTheBest")) {
                    MainMenu mainFrame = new MainMenu();
                    mainFrame.setVisible(true);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		
	}
}
