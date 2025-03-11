package main.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import main.domain.Animals;
import main.domain.Reptiles;
import main.repository.Repo_Animals;

public class Pet_Reptiles extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Animals animalRepo;
    private JTextField searchField;
    private JComboBox<Reptiles.ReptileType> filterComboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Repo_Animals animalRepo = new Repo_Animals();
                Pet_Reptiles frame = new Pet_Reptiles(animalRepo);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public Pet_Reptiles() {
        this(new Repo_Animals()); 
    }

    public Pet_Reptiles(Repo_Animals animalRepo) {
        this.animalRepo = animalRepo;
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
        setTitle("Reptiles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        setContentPane(contentPane);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Shell Size", "Tail Length", "Reptile Type"}, 0);
        table = new JTable(tableModel);
        loadReptiles(); 

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search by ID");
        searchButton.addActionListener(e -> searchReptileById());

        filterComboBox = new JComboBox<>(Reptiles.ReptileType.values());
        JButton filterButton = new JButton("Filter by Type");
        filterButton.addActionListener(e -> filterByType());

        JButton sortButton = new JButton("Sort by Size");
        sortButton.addActionListener(e -> sortBySize());

        topPanel.add(new JLabel("Search by ID:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(new JLabel("Filter by Type:"));
        topPanel.add(filterComboBox);
        topPanel.add(filterButton);
        topPanel.add(sortButton);

        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdd = new JButton("Add Reptile");
        JButton btnEdit = new JButton("Edit Reptile");
        JButton btnDelete = new JButton("Delete Reptile");
        JButton btnBack = new JButton("Back");

        btnAdd.addActionListener(e -> addReptile());
        btnEdit.addActionListener(e -> editReptile());
        btnDelete.addActionListener(e -> deleteReptile());
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

    private void loadReptiles() {
        tableModel.setRowCount(0); 
        for (Animals animal : animalRepo.getAllAnimals("reptiles")) { // Fetch reptiles from the "reptiles" table
            if (animal instanceof Reptiles) {
                Reptiles reptile = (Reptiles) animal;
                tableModel.addRow(new Object[]{
                        reptile.getId(),
                        reptile.getName(),
                        reptile.getShellSize(),
                        reptile.getTailLength(),
                        reptile.getReptileType().toString()
                });
            }
        }
    }

    private void searchReptileById() {
        String searchId = searchField.getText();
        if (!searchId.isEmpty()) {
            try {
                int id = Integer.parseInt(searchId);
                tableModel.setRowCount(0);
                Animals animal = animalRepo.readById(id, "reptiles");
                if (animal instanceof Reptiles) {
                    Reptiles reptile = (Reptiles) animal;
                    tableModel.addRow(new Object[]{
                            reptile.getId(),
                            reptile.getName(),
                            reptile.getShellSize(),
                            reptile.getTailLength(),
                            reptile.getReptileType().toString()
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "No reptile found with the given ID.");
                    loadReptiles();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
            }
        }
    }

    private void filterByType() {
        Reptiles.ReptileType selectedType = (Reptiles.ReptileType) filterComboBox.getSelectedItem();
        tableModel.setRowCount(0);
        for (Animals animal : animalRepo.getAllAnimals("reptiles")) {
            if (animal instanceof Reptiles) {
                Reptiles reptile = (Reptiles) animal;
                if (reptile.getReptileType() == selectedType) {
                    tableModel.addRow(new Object[]{
                            reptile.getId(),
                            reptile.getName(),
                            reptile.getShellSize(),
                            reptile.getTailLength(),
                            reptile.getReptileType().toString()
                    });
                }
            }
        }
    }

    private void sortBySize() {
        animalRepo.getAllAnimals("reptiles").stream()
                .filter(animal -> animal instanceof Reptiles)
                .map(animal -> (Reptiles) animal)
                .sorted((r1, r2) -> Double.compare(r1.getShellSize(), r2.getShellSize()))
                .forEachOrdered(reptile -> tableModel.addRow(new Object[]{
                        reptile.getId(),
                        reptile.getName(),
                        reptile.getShellSize(),
                        reptile.getTailLength(),
                        reptile.getReptileType().toString()
                }));
    }

    private void addReptile() {
        try {
            String name = JOptionPane.showInputDialog("Enter reptile name:");
            String shellSizeInput = JOptionPane.showInputDialog("Enter shell size:");
            String tailLengthInput = JOptionPane.showInputDialog("Enter tail length:");

            if (name != null && shellSizeInput != null && tailLengthInput != null) {
                double shellSize = Double.parseDouble(shellSizeInput);
                int tailLength = Integer.parseInt(tailLengthInput);
                Reptiles.ReptileType reptileType = (Reptiles.ReptileType) JOptionPane.showInputDialog(
                        this,
                        "Select Reptile Type",
                        "Reptile Type",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Reptiles.ReptileType.values(),
                        Reptiles.ReptileType.TURTLE
                );

                if (reptileType != null) {
                    int newId = animalRepo.getAllAnimals("reptiles").size() + 1;
                    Reptiles newReptile = new Reptiles(newId, name, "Female", 10, 3.5, "Omnivore", 50.0, true, shellSize, tailLength, reptileType);
                    animalRepo.create(newReptile, "reptiles");
                    loadReptiles();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for shell size or tail length.");
        }
    }

    private void editReptile() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int reptileId = (int) tableModel.getValueAt(selectedRow, 0);
            Reptiles reptile = (Reptiles) animalRepo.readById(reptileId, "reptiles");

            String newName = JOptionPane.showInputDialog("Edit reptile name:", reptile.getName());
            String shellSizeInput = JOptionPane.showInputDialog("Edit shell size:", reptile.getShellSize());
            String tailLengthInput = JOptionPane.showInputDialog("Edit tail length:", reptile.getTailLength());

            Reptiles.ReptileType selectedType = (Reptiles.ReptileType) JOptionPane.showInputDialog(
                    this,
                    "Select Reptile Type",
                    "Reptile Type",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Reptiles.ReptileType.values(),
                    reptile.getReptileType()
            );

            if (newName != null && shellSizeInput != null && tailLengthInput != null && selectedType != null) {
                try {
                    double shellSize = Double.parseDouble(shellSizeInput);
                    int tailLength = Integer.parseInt(tailLengthInput);

                    reptile.setName(newName);
                    reptile.setShellSize(shellSize);
                    reptile.setTailLength(tailLength);
                    reptile.setReptileType(selectedType);

                    animalRepo.updateAnimal(reptile.getId(), reptile, "reptiles");
                    loadReptiles();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input for shell size or tail length.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a reptile to edit.");
        }
    }

    private void deleteReptile() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int reptileId = (int) tableModel.getValueAt(selectedRow, 0);
            animalRepo.delete(reptileId, "reptiles");
            loadReptiles();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a reptile to delete.");
        }
    }
}

