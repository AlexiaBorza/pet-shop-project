package main.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;
import main.domain.Animals;
import main.domain.Birds;
import main.repository.Repo_Animals;

public class Pet_Birds extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Animals animalRepo;
    private JTextField searchField;
    private JComboBox<Birds.BirdType> birdTypeComboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Repo_Animals animalRepo = new Repo_Animals();
                Pet_Birds frame = new Pet_Birds(animalRepo);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public Pet_Birds() {
        this(new Repo_Animals()); // Default constructor
    }

    public Pet_Birds(Repo_Animals animalRepo) {
        this.animalRepo = animalRepo;

        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
        setTitle("Birds");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 641, 735);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));  // Ensure BorderLayout is used
        setContentPane(contentPane);

        // Updated table columns
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Name", "Feather Color", "Talks?", "Bird Type", "Age", "Price", "Gender", "Fed"},
            0
        );
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true); // Ensures that the table fills the available height

        // Initialize birdTypeComboBox before calling loadBirdsFromDB
        birdTypeComboBox = new JComboBox<>(Birds.BirdType.values());
        birdTypeComboBox.addActionListener(e -> loadBirdsFromDB()); // Reload table when combo box changes

        loadBirdsFromDB(); // Load data from the database after initializing combo box

        // Resize columns (you may adjust column widths for better visibility)
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);
        table.getColumnModel().getColumn(8).setPreferredWidth(50);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);  // Add the JScrollPane that wraps the table

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdd = new JButton("Add Bird");
        JButton btnEdit = new JButton("Edit Bird");
        JButton btnDelete = new JButton("Delete Bird");
        JButton btnBack = new JButton("Back");
        JButton btnFilter = new JButton("Filter");
        JButton btnSort = new JButton("Sort");


        btnAdd.addActionListener(e -> addBird());
        btnEdit.addActionListener(e -> editBird());
        btnDelete.addActionListener(e -> deleteBird());
        btnFilter.addActionListener(e -> applyFilter());
        btnSort.addActionListener(e -> applySort());
        btnBack.addActionListener(e -> {
            AnimalsUI animalFrame = new AnimalsUI();
            animalFrame.setVisible(true);
            dispose();
        });
        

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);
        buttonPanel.add(btnFilter); 
        buttonPanel.add(btnSort); 

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }


    private void applyFilter() {
        Birds.BirdType selectedBirdType = (Birds.BirdType) JOptionPane.showInputDialog(
                this,
                "Select Bird Type for filtering",
                "Bird Type Filter",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Birds.BirdType.values(),
                Birds.BirdType.PARROT 
        );

        if (selectedBirdType != null) {
            tableModel.setRowCount(0);
            for (Animals animal : animalRepo.getAllAnimals("birds")) {
                if (animal instanceof Birds) {
                    Birds bird = (Birds) animal;
                    if (bird.getBirdType() == selectedBirdType) {
                        tableModel.addRow(new Object[]{
                            bird.getId(),
                            bird.getName(),
                            bird.getFeatherColor(),
                            bird.getTalks() ? "Yes" : "No",
                            bird.getBirdType().toString(),
                            bird.getAge(),
                            bird.getPrice(),
                            bird.getGender(),
                            bird.isFed() ? "Yes" : "No"
                        });
                    }
                }
            }
        }
    }

    private void applySort() {

        String[] options = {"Sort by Age", "Sort by Price"};
        int choice = JOptionPane.showOptionDialog(this,
                "Select sorting criterion",
                "Sort Birds",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            // Sort by Age
            tableModel.setRowCount(0);
            List<Birds> sortedByAge = animalRepo.getAllAnimals("birds").stream()
                    .filter(animal -> animal instanceof Birds)
                    .map(animal -> (Birds) animal)
                    .sorted(Comparator.comparingInt(Birds::getAge))
                    .collect(Collectors.toList());
            for (Birds bird : sortedByAge) {
                tableModel.addRow(new Object[]{
                    bird.getId(),
                    bird.getName(),
                    bird.getFeatherColor(),
                    bird.getTalks() ? "Yes" : "No",
                    bird.getBirdType().toString(),
                    bird.getAge(),
                    bird.getPrice(),
                    bird.getGender(),
                    bird.isFed() ? "Yes" : "No"
                });
            }
        } else if (choice == 1) {
 
            tableModel.setRowCount(0); 
            List<Birds> sortedByPrice = animalRepo.getAllAnimals("birds").stream()
                    .filter(animal -> animal instanceof Birds)
                    .map(animal -> (Birds) animal)
                    .sorted(Comparator.comparingDouble(Birds::getPrice))
                    .collect(Collectors.toList());
            for (Birds bird : sortedByPrice) {
                tableModel.addRow(new Object[]{
                    bird.getId(),
                    bird.getName(),
                    bird.getFeatherColor(),
                    bird.getTalks() ? "Yes" : "No",
                    bird.getBirdType().toString(),
                    bird.getAge(),
                    bird.getPrice(),
                    bird.getGender(),
                    bird.isFed() ? "Yes" : "No"
                });
            }
        }
    }


    private void loadBirdsFromDB() {
        tableModel.setRowCount(0); 

        Birds.BirdType selectedBirdType = (Birds.BirdType) birdTypeComboBox.getSelectedItem();
        boolean filterByTalks = true; 
        for (Animals animal : animalRepo.getAllAnimals("birds")) { 
            if (animal instanceof Birds) {
                Birds bird = (Birds) animal;

                if (bird.getTalks() == filterByTalks && 
                    (selectedBirdType == null || bird.getBirdType() == selectedBirdType)) {
                    tableModel.addRow(new Object[]{
                        bird.getId(),
                        bird.getName(),
                        bird.getFeatherColor(),
                        bird.getTalks() ? "Yes" : "No",
                        bird.getBirdType().toString(),
                        bird.getAge(),
                        bird.getPrice(),
                        bird.getGender(),
                        bird.isFed() ? "Yes" : "No"
                    });
                }
            }
        }
    }


    private void addBird() {
        try {
            String name = JOptionPane.showInputDialog("Enter bird name:");
            String featherColor = JOptionPane.showInputDialog("Enter feather color:");
            String talksInput = JOptionPane.showInputDialog("Does the bird talk? (yes/no):");
            String ageInput = JOptionPane.showInputDialog("Enter bird age:");
            String priceInput = JOptionPane.showInputDialog("Enter bird price:");
            String genderInput = JOptionPane.showInputDialog("Enter bird gender (Male/Female):");
            String fedInput = JOptionPane.showInputDialog("Is the bird fed? (yes/no):");
            String weightInput = JOptionPane.showInputDialog("Enter bird weight (greater than 0):");

            if (name != null && featherColor != null && talksInput != null && ageInput != null && priceInput != null && genderInput != null && fedInput != null && weightInput != null) {
                boolean talks = talksInput.equalsIgnoreCase("yes");

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
                
                double weight = Double.parseDouble(weightInput);
                if (weight <= 0) {
                    throw new IllegalArgumentException("Weight must be greater than 0.");
                }

                boolean fed = fedInput.equalsIgnoreCase("yes");

                Birds.BirdType birdType = (Birds.BirdType) JOptionPane.showInputDialog(
                        this,
                        "Select Bird Type",
                        "Bird Type",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Birds.BirdType.values(),
                        Birds.BirdType.PARROT
                );

                if (birdType != null) {
                    Birds newBird = new Birds(0, name, genderInput, age, weight, "Unknown", price, fed, featherColor, talks, birdType);
                    animalRepo.create(newBird, "birds");
                    loadBirdsFromDB();
                }
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding bird: " + e.getMessage());
        }
    }


    private void editBird() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            try {

                int birdId = (int) tableModel.getValueAt(selectedRow, 0);
                Birds bird = (Birds) animalRepo.readById(birdId, "birds");

                String newName = JOptionPane.showInputDialog("Edit bird name:", bird.getName());
                String newFeatherColor = JOptionPane.showInputDialog("Edit feather color:", bird.getFeatherColor());
                String talksInput = JOptionPane.showInputDialog("Does the bird talk? (yes/no):", bird.getTalks() ? "yes" : "no");
                String ageInput = JOptionPane.showInputDialog("Edit bird age:", bird.getAge());
                String priceInput = JOptionPane.showInputDialog("Edit bird price:", bird.getPrice());
                String genderInput = JOptionPane.showInputDialog("Edit bird gender (Male/Female):", bird.getGender());
                String fedInput = JOptionPane.showInputDialog("Is the bird fed? (yes/no):", bird.isFed() ? "yes" : "no");
                String weightInput = JOptionPane.showInputDialog("Edit bird weight (greater than 0):", bird.getWeight());

                if (newName != null && newFeatherColor != null && talksInput != null && ageInput != null && priceInput != null && genderInput != null && fedInput != null && weightInput != null) {
                    boolean talks = talksInput.equalsIgnoreCase("yes");

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

                    double weight = Double.parseDouble(weightInput);
                    if (weight <= 0) {
                        throw new IllegalArgumentException("Weight must be greater than 0.");
                    }

                    boolean fed = fedInput.equalsIgnoreCase("yes");

                    Birds.BirdType selectedType = (Birds.BirdType) JOptionPane.showInputDialog(
                            this,
                            "Select Bird Type",
                            "Bird Type",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            Birds.BirdType.values(),
                            bird.getBirdType()
                    );

                    if (selectedType != null) {
                        bird.setName(newName);
                        bird.setFeatherColor(newFeatherColor);
                        bird.setTalks(talks);
                        bird.setAge(age);
                        bird.setPrice(price);
                        bird.setGender(genderInput);
                        bird.setFed(fed);
                        bird.setWeight(weight);
                        bird.setBirdType(selectedType);

                        animalRepo.updateAnimal(birdId, bird, "birds");
                        loadBirdsFromDB(); 
                    }
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error editing bird: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a bird to edit.");
        }
    }

    private void deleteBird() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            try {
                int birdId = (int) tableModel.getValueAt(selectedRow, 0);
                animalRepo.delete(birdId, "birds");
                loadBirdsFromDB(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error deleting bird: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a bird to delete.");
        }
    }
}
