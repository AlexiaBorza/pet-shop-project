package main.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 735);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(183, 211, 217));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Back!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(34, 41, 177, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select desired category:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(178, 110, 254, 25);
		contentPane.add(lblNewLabel_1);

		
		JButton btnNewButton = new JButton("Products");
		btnNewButton.setIcon(null);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(69, 305, 114, 25);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Products prodFrame = new Products();
		        prodFrame.setVisible(true);       
		    }
		});
		
		JButton btnNewButton_4 = new JButton("Log Out");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setBounds(476, 611, 141, 25);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        LogIn loginFrame = new LogIn();
		        loginFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JButton btnAnimals = new JButton("Animals");
		btnAnimals.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAnimals.setBounds(253, 305, 114, 25);
		contentPane.add(btnAnimals);
		btnAnimals.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        AnimalsUI animalsFrame = new AnimalsUI();
		        animalsFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JButton btnNewButton_5_1 = new JButton("Appointments");
		btnNewButton_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_1.setBounds(439, 306, 114, 25);
		contentPane.add(btnNewButton_5_1);
		btnNewButton_5_1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Appointments_ui appFrame = new Appointments_ui();
		        appFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JButton btnNewButton_5_2 = new JButton("Tasks");
		btnNewButton_5_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5_2.setBounds(349, 485, 114, 25);
		contentPane.add(btnNewButton_5_2);
		btnNewButton_5_2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Tasks taskFrame = new Tasks();
		        taskFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JButton btnNewButton_5_2_2 = new JButton("Enclosures");
        btnNewButton_5_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButton_5_2_2.setBounds(143, 485, 114, 25);
        contentPane.add(btnNewButton_5_2_2);
        btnNewButton_5_2_2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Enclosure_ui enclosureFrame = new Enclosure_ui();
		        enclosureFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JLabel lblNewLabel_2 = new JLabel("");
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\icontoys.jpg");
        Image image = originalIcon.getImage();
        Image resizedImage = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        lblNewLabel_2.setIcon(resizedIcon);
        lblNewLabel_2.setBounds(69, 186, 99, 102); 
        getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        ImageIcon originalIcon1 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\iconanimale.jpg");
        Image image1 = originalIcon1.getImage();
        Image resizedImage1 = image1.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
        lblNewLabel_3.setIcon(resizedIcon1);
        lblNewLabel_3.setBounds(253, 186, 99, 102); 
        getContentPane().add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("");
        ImageIcon originalIcon2 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\iconclient.jpg");
        Image image2 = originalIcon2.getImage();
        Image resizedImage2 = image2.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        lblNewLabel_4.setIcon(resizedIcon2);
        lblNewLabel_4.setBounds(453, 186, 100, 100);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("");
        ImageIcon originalIcon3 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\iconprodus.jpg");
        Image image3 = originalIcon3.getImage();
        Image resizedImage3 = image3.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        lblNewLabel_5.setIcon(resizedIcon3);
        lblNewLabel_5.setBounds(157, 375, 100, 100);
        contentPane.add(lblNewLabel_5);
        ImageIcon originalIcon4 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\icontasks.jpg");
        Image image4 = originalIcon4.getImage();
        Image resizedImage4 = image4.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
        
        JLabel lblNewLabel_6_1 = new JLabel("");
        ImageIcon originalIcon6 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\icontasks.jpg");
        Image image6 = originalIcon6.getImage();
        Image resizedImage6 = image6.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);
        lblNewLabel_6_1.setIcon(resizedIcon6);
        lblNewLabel_6_1.setBounds(363, 378, 100, 100);
        contentPane.add(lblNewLabel_6_1);
        setVisible(true);
        ImageIcon originalIcon5 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\icontasks.jpg");
        Image image5 = originalIcon5.getImage();
        Image resizedImage5 = image5.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(resizedImage4);
        setVisible(true);
	}
}
