package main.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import main.domain.Animals;
import main.domain.Canine_and_Feline;
import main.repository.Repo_Animals;

public class PetCatsAndDogs extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Animals animalRepo;
    private JTextField searchField;
    private JComboBox<Canine_and_Feline.Species> filterComboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Repo_Animals animalRepo = new Repo_Animals();
                PetCatsAndDogs frame = new PetCatsAndDogs(animalRepo);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PetCatsAndDogs() {
        this(new Repo_Animals());
    }

    public PetCatsAndDogs(Repo_Animals animalRepo) {
        this.animalRepo = animalRepo;

        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
        setTitle("Cats & Dogs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 800); 

        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Breed", "Age", "Fur Color", "Species", "Price"}, 0); // Added Price column
        table = new JTable(tableModel);
        loadCatsAndDogs(); 

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100); // Price column width

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search by ID");
        searchButton.addActionListener(e -> searchById());

        filterComboBox = new JComboBox<>(Canine_and_Feline.Species.values());
        JButton filterButton = new JButton("Filter by Species");
        filterButton.addActionListener(e -> filterBySpecies());

        JButton sortButton = new JButton("Sort by Price");
        sortButton.addActionListener(e -> sortByPrice()); // Changed to sort by price

        topPanel.add(new JLabel("Search by ID:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(new JLabel("Filter by Species:"));
        topPanel.add(filterComboBox);
        topPanel.add(filterButton);
        topPanel.add(sortButton);

        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Buttons for adding, editing, deleting, and navigating back
        JButton btnAdd = new JButton("Add Cat/Dog");
        JButton btnEdit = new JButton("Edit Cat/Dog");
        JButton btnDelete = new JButton("Delete Cat/Dog");
        JButton btnBack = new JButton("Back");

        btnAdd.addActionListener(e -> addCatOrDog());
        btnEdit.addActionListener(e -> editCatOrDog());
        btnDelete.addActionListener(e -> deleteCatOrDog());
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

    private void loadCatsAndDogs() {
        tableModel.setRowCount(0); 
        for (Animals animal : animalRepo.getAllAnimals("canineandfeline")) {
            if (animal instanceof Canine_and_Feline) {
                Canine_and_Feline pet = (Canine_and_Feline) animal;
                tableModel.addRow(new Object[]{
                        pet.getId(),
                        pet.getName(),
                        pet.getBreed(),
                        pet.getAge(),
                        pet.getFurColor(),
                        pet.getSp(),
                        pet.getPrice() 
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
                Animals animal = animalRepo.readById(id, "canineandfeline");
                if (animal instanceof Canine_and_Feline) {
                    Canine_and_Feline pet = (Canine_and_Feline) animal;
                    tableModel.addRow(new Object[]{
                            pet.getId(),
                            pet.getName(),
                            pet.getBreed(),
                            pet.getAge(),
                            pet.getFurColor(),
                            pet.getSp(),
                            pet.getPrice() // Added price to search result
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "No cat or dog found with the given ID.");
                    loadCatsAndDogs();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
            }
        }
    }

    private void filterBySpecies() {
        Canine_and_Feline.Species selectedSpecies = (Canine_and_Feline.Species) filterComboBox.getSelectedItem();
        tableModel.setRowCount(0);
        for (Animals animal : animalRepo.getAllAnimals("canineandfeline")) {
            if (animal instanceof Canine_and_Feline) {
                Canine_and_Feline pet = (Canine_and_Feline) animal;
                if (pet.getSp() == selectedSpecies) {
                    tableModel.addRow(new Object[]{
                            pet.getId(),
                            pet.getName(),
                            pet.getBreed(),
                            pet.getAge(),
                            pet.getFurColor(),
                            pet.getSp(),
                            pet.getPrice() // Added price
                    });
                }
            }
        }
    }

    private void sortByPrice() {
        tableModel.setRowCount(0);

        animalRepo.getAllAnimals("canineandfeline").stream()
                .filter(animal -> animal instanceof Canine_and_Feline)
                .map(animal -> (Canine_and_Feline) animal)
                .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                .forEachOrdered(pet -> tableModel.addRow(new Object[]{
                        pet.getId(),
                        pet.getName(),
                        pet.getBreed(),
                        pet.getAge(),
                        pet.getFurColor(),
                        pet.getSp(),
                        pet.getPrice() 
                }));
    }

    private void addCatOrDog() {
        try {
            String name = JOptionPane.showInputDialog("Enter name:");
            String breed = JOptionPane.showInputDialog("Enter breed:");
            String ageInput = JOptionPane.showInputDialog("Enter age:");
            String furColor = JOptionPane.showInputDialog("Enter fur color:");
            String priceInput = JOptionPane.showInputDialog("Enter price:");

            if (name != null && breed != null && ageInput != null && furColor != null && priceInput != null) {
                int age = Integer.parseInt(ageInput);
                double price = Double.parseDouble(priceInput); // Added price parsing
                Canine_and_Feline.Species species = (Canine_and_Feline.Species) JOptionPane.showInputDialog(
                        this,
                        "Select Species",
                        "Species",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Canine_and_Feline.Species.values(),
                        Canine_and_Feline.Species.CAT
                );

                if (species != null) {
                    int newId = animalRepo.getAllAnimals("canineandfeline").size() + 1;
                    Canine_and_Feline newPet = new Canine_and_Feline(newId, name, "Female", age, 0, "Omnivore", price, true, furColor, true, species, breed);
                    animalRepo.create(newPet, "canineandfeline");
                    loadCatsAndDogs();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for age or price.");
        }
    }

    private void editCatOrDog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int petId = (int) tableModel.getValueAt(selectedRow, 0);
            Canine_and_Feline pet = (Canine_and_Feline) animalRepo.readById(petId, "canineandfeline");

            String newName = JOptionPane.showInputDialog("Edit name:", pet.getName());
            String newBreed = JOptionPane.showInputDialog("Edit breed:", pet.getBreed());
            String newAgeInput = JOptionPane.showInputDialog("Edit age:", pet.getAge());
            String newFurColor = JOptionPane.showInputDialog("Edit fur color:", pet.getFurColor());
            String newPriceInput = JOptionPane.showInputDialog("Edit price:", pet.getPrice()); // Added price input

            Canine_and_Feline.Species newSpecies = (Canine_and_Feline.Species) JOptionPane.showInputDialog(
                    this,
                    "Select Species",
                    "Species",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Canine_and_Feline.Species.values(),
                    pet.getSp()
            );

            if (newName != null && newBreed != null && newAgeInput != null && newFurColor != null && newSpecies != null && newPriceInput != null) {
                try {
                    int newAge = Integer.parseInt(newAgeInput);
                    double newPrice = Double.parseDouble(newPriceInput); // Parsing the price

                    pet.setName(newName);
                    pet.setBreed(newBreed);
                    pet.setAge(newAge);
                    pet.setFurColor(newFurColor);
                    pet.setSp(newSpecies);
                    pet.setPrice(newPrice); // Set new price

                    animalRepo.updateAnimal(pet.getId(), pet, "canineandfeline");
                    loadCatsAndDogs();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input for age or price.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }

    private void deleteCatOrDog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int petId = (int) tableModel.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this pet?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                animalRepo.delete(petId, "canineandfeline");
                loadCatsAndDogs();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }
}
