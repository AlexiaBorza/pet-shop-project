package main.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;
import main.domain.Animals;
import main.domain.Fish;
import main.repository.Repo_Animals;

public class Pet_Fish extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Animals animalRepo;
    private JTextField searchField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Repo_Animals animalRepo = new Repo_Animals();
                Pet_Fish frame = new Pet_Fish(animalRepo);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public Pet_Fish() {
        this(new Repo_Animals()); 
    }

    public Pet_Fish(Repo_Animals animalRepo) {
        this.animalRepo = animalRepo;

        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
        setTitle("Fish");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 641, 735);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        tableModel = new DefaultTableModel(
            new String[]{"ID", "Name", "Color", "Fish Type", "Age", "Price", "Gender", "Fed"},
            0
        );
        table = new JTable(tableModel);
        loadFishFromDB(); 

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);  
        table.getColumnModel().getColumn(6).setPreferredWidth(80);  
        table.getColumnModel().getColumn(7).setPreferredWidth(80);  

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search by ID");
        searchButton.addActionListener(e -> searchById());

        JButton sortButton = new JButton("Sort by Price");
        sortButton.addActionListener(e -> sortByPrice());
        
        

        topPanel.add(new JLabel("Search by ID:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(sortButton);

        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdd = new JButton("Add Fish");
        JButton btnEdit = new JButton("Edit Fish");
        JButton btnDelete = new JButton("Delete Fish");
        JButton btnBack = new JButton("Back");

        btnAdd.addActionListener(e -> addFish());
        btnEdit.addActionListener(e -> editFish());
        btnDelete.addActionListener(e -> deleteFish());
        btnBack.addActionListener(e -> {
            AnimalsUI animalFrame = new AnimalsUI();
            animalFrame.setVisible(true);
            dispose();
        });


        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadFishFromDB() {
        tableModel.setRowCount(0); 
        for (Animals animal : animalRepo.getAllAnimals("fish")) {
            if (animal instanceof Fish) {
                Fish fish = (Fish) animal;
                tableModel.addRow(new Object[]{
                    fish.getId(),
                    fish.getName(),
                    fish.getColor(),
                    fish.getFishBreed(),
                    fish.getAge(),
                    fish.getPrice(),
                    fish.getGender(),
                    fish.isFed() ? "Yes" : "No"
                });
            }
        }
    }

    private void searchById() {
        String searchId = searchField.getText();
        if (!searchId.isEmpty()) {
            try {
                int id = Integer.parseInt(searchId);
                tableModel.setRowCount(0); 
                Animals animal = animalRepo.readById(id, "fish");
                if (animal instanceof Fish) {
                    Fish fish = (Fish) animal;
                    tableModel.addRow(new Object[]{
                            fish.getId(),
                            fish.getName(),
                            fish.getColor(),
                            fish.getFishBreed(),
                            fish.getAge(),
                            fish.getPrice(),
                            fish.getGender(),
                            fish.isFed() ? "Yes" : "No"
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "No fish found with the given ID.");
                    loadFishFromDB(); 
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
            }
        }
    }

    private void sortByPrice() {
        tableModel.setRowCount(0); 
        animalRepo.getAllAnimals("fish").stream()
                .filter(animal -> animal instanceof Fish)
                .map(animal -> (Fish) animal)
                .sorted((f1, f2) -> Double.compare(f1.getPrice(), f2.getPrice())) // Sorting by price
                .forEachOrdered(fish -> tableModel.addRow(new Object[]{
                        fish.getId(),
                        fish.getName(),
                        fish.getColor(),
                        fish.getFishBreed(),
                        fish.getAge(),
                        fish.getPrice(),
                        fish.getGender(),
                        fish.isFed() ? "Yes" : "No"
                }));
    }

    private void addFish() {
        try {
            String name = JOptionPane.showInputDialog("Enter Fish name:");
            String color = JOptionPane.showInputDialog("Enter color:");
            String breedInput = JOptionPane.showInputDialog("Does breed:");
            String ageInput = JOptionPane.showInputDialog("Enter age:");
            String priceInput = JOptionPane.showInputDialog("Enter price:");
            String genderInput = JOptionPane.showInputDialog("Enter gender (Male/Female):");
            String fedInput = JOptionPane.showInputDialog("Is the fish fed? (yes/no):");

            if (name != null && color != null && breedInput != null && ageInput != null && priceInput != null && genderInput != null && fedInput != null) {

                int age = Integer.parseInt(ageInput);
                if (age < 0) {
                    throw new IllegalArgumentException("Age must be a positive number.");
                }
                
                double price = Double.parseDouble(priceInput);
                if (price < 0) {
                    throw new IllegalArgumentException("Price must be a non-negative number.");
                }

                if (!genderInput.equalsIgnoreCase("Male") && !genderInput.equalsIgnoreCase("Female")) {
                    throw new IllegalArgumentException("Gender must be 'Male' or 'Female'.");
                }

                boolean fed = fedInput.equalsIgnoreCase("yes");

                if (breedInput != null) {
                    String diet = "Herbivore";
					double weight = 0;
					Fish newFish = new Fish(0, name, genderInput, age, weight, diet,  price, fed, color, breedInput);
                    animalRepo.create(newFish, "fish");
                    loadFishFromDB(); 
                }
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding bird: " + e.getMessage());
        }
    }

        private void editFish() {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    int fishId = (int) tableModel.getValueAt(selectedRow, 0);
                    Fish fish = (Fish) animalRepo.readById(fishId, "fish");

                    String newName = JOptionPane.showInputDialog("Edit fish name:", fish.getName());
                    String newColor = JOptionPane.showInputDialog("Edit fish color:", fish.getColor());
                    String newFishBreed = JOptionPane.showInputDialog("Edit fish breed:", fish.getFishBreed());
                    String ageInput = JOptionPane.showInputDialog("Edit fish age:", fish.getAge());
                    String priceInput = JOptionPane.showInputDialog("Edit fish price:", fish.getPrice());
                    String genderInput = JOptionPane.showInputDialog("Edit fish gender (Male/Female):", fish.getGender());
                    String fedInput = JOptionPane.showInputDialog("Is the fish fed? (yes/no):", fish.isFed() ? "yes" : "no");

                    if (newName != null && newColor != null && newFishBreed != null && ageInput != null && priceInput != null && genderInput != null && fedInput != null) {
                        int age = Integer.parseInt(ageInput);
                        if (age < 0) {
                            throw new IllegalArgumentException("Age must be a positive number.");
                        }

                        double price = Double.parseDouble(priceInput);
                        if (price < 0) {
                            throw new IllegalArgumentException("Price must be a non-negative number.");
                        }

                        if (!genderInput.equalsIgnoreCase("Male") && !genderInput.equalsIgnoreCase("Female")) {
                            throw new IllegalArgumentException("Gender must be 'Male' or 'Female'.");
                        }

                        boolean fed = fedInput.equalsIgnoreCase("yes");

                        fish.setName(newName);
                        fish.setColor(newColor);
                        fish.setFishBreed(newFishBreed);
                        fish.setAge(age);
                        fish.setPrice(price);
                        fish.setGender(genderInput);
                        fish.setFed(fed);

                        animalRepo.updateAnimal(fishId, fish, "fish");
                        loadFishFromDB();
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error editing fish: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a fish to edit.");
            }
        }

        private void deleteFish() {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    int fishId = (int) tableModel.getValueAt(selectedRow, 0);
                    animalRepo.delete(fishId, "fish");
                    loadFishFromDB(); 
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error deleting fish: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a fish to delete.");
            }
        }
}
