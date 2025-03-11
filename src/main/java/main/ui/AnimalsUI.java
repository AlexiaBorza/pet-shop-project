package main.ui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalsUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimalsUI frame = new AnimalsUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AnimalsUI() {
		setTitle("Pets");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 735);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(183, 211, 217));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(39, 601, 129, 27);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        MainMenu menuFrame = new MainMenu();
		        menuFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JLabel lblNewLabel = new JLabel("Select Category");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 20));
		lblNewLabel.setBounds(241, 73, 159, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Cats & Dogs");
		btnNewButton_1.setFont(new Font("Serif", Font.BOLD, 15));
		btnNewButton_1.setBounds(52, 257, 129, 27);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        PetCatsAndDogs catdogFrame = new PetCatsAndDogs();
		        catdogFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JButton btnNewButton_1_1 = new JButton("Birds");
		btnNewButton_1_1.setFont(new Font("Serif", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(239, 257, 129, 27);
		contentPane.add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Pet_Birds birdFrame = new Pet_Birds();
		        birdFrame.setVisible(true);
		        dispose();
		    }
		});
		
		
		JButton btnNewButton_1_2 = new JButton("Rodents");
		btnNewButton_1_2.setFont(new Font("Serif", Font.BOLD, 15));
		btnNewButton_1_2.setBounds(420, 257, 129, 27);
		contentPane.add(btnNewButton_1_2);
		btnNewButton_1_2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Pet_Rodents rodentFrame = new Pet_Rodents();
		        rodentFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JButton btnNewButton_1_3 = new JButton("Fish");
		btnNewButton_1_3.setFont(new Font("Serif", Font.BOLD, 15));
		btnNewButton_1_3.setBounds(134, 437, 129, 27);
		contentPane.add(btnNewButton_1_3);
		btnNewButton_1_3.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Pet_Fish fishFrame = new Pet_Fish();
		        fishFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JButton btnNewButton_1_4 = new JButton("Reptiles");
		btnNewButton_1_4.setFont(new Font("Serif", Font.BOLD, 15));
		btnNewButton_1_4.setBounds(343, 437, 129, 27);
		contentPane.add(btnNewButton_1_4);
		btnNewButton_1_4.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Pet_Reptiles reptileFrame = new Pet_Reptiles();
		        reptileFrame.setVisible(true);
		        dispose();
		    }
		});
		
		JLabel lblNewLabel_2 = new JLabel("");
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\CatDog.jpg");
        Image image = originalIcon.getImage();
        Image resizedImage = image.getScaledInstance(120, 110, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        lblNewLabel_2.setIcon(resizedIcon);
        lblNewLabel_2.setBounds(52, 152, 120, 102); 
        getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        ImageIcon originalIcon1 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\parrot.jpg");
        Image image1 = originalIcon1.getImage();
        Image resizedImage1 = image1.getScaledInstance(120, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
        lblNewLabel_3.setIcon(resizedIcon1);
        lblNewLabel_3.setBounds(241, 152, 120, 102); 
        getContentPane().add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("");
        ImageIcon originalIcon2 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\bunny.jpg");
        Image image2 = originalIcon2.getImage();
        Image resizedImage2 = image2.getScaledInstance(120, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        lblNewLabel_4.setIcon(resizedIcon2);
        lblNewLabel_4.setBounds(420, 154, 120, 100);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("");
        ImageIcon originalIcon3 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\fish2.jpg");
        Image image3 = originalIcon3.getImage();
        Image resizedImage3 = image3.getScaledInstance(120, 110, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        lblNewLabel_5.setIcon(resizedIcon3);
        lblNewLabel_5.setBounds(134, 333, 120, 100);
        contentPane.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("");
        ImageIcon originalIcon4 = new ImageIcon("C:\\Users\\ALEXIA\\OneDrive\\Imagini\\project\\agama.jpg");
        Image image4 = originalIcon4.getImage();
        Image resizedImage4 = image4.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
        lblNewLabel_6.setIcon(resizedIcon4);
        lblNewLabel_6.setBounds(343, 333, 120, 100);
        contentPane.add(lblNewLabel_6);
        
        setVisible(true);
	}

}
