package main.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import main.domain.*;
import main.repository.Repo_Items;

public class Products extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Items itemRepo;
    private JTextField searchField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Products frame = new Products();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Products() {
        this(new Repo_Items());
    }

    public Products(Repo_Items itemRepo) {
        this.itemRepo = itemRepo;

        setTitle("Inventory");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 1200, 800);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        tableModel = new DefaultTableModel(new String[]{
            "ID", "Name", "Brand", "For Animal", "Price", "Stock",
            "Material", "Is Interactive", "Size",
            "Expiration Date", "Issue", "Prescription Required",
            "Color", "Diet", "Food Expiration Date"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchField = new JTextField(15);
        JButton btnSearch = new JButton("Search by ID");
        JButton btnFilterByType = new JButton("Filter by Type");
        JButton btnFilterByAnimal = new JButton("Filter by Animal");
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(btnSearch);
        topPanel.add(btnFilterByType);
        topPanel.add(btnFilterByAnimal);
        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAdd = new JButton("Add Item");
        JButton btnEdit = new JButton("Edit Item");
        JButton btnDelete = new JButton("Delete Item");
        JButton btnBack = new JButton("Back");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addItem());
        btnEdit.addActionListener(e -> editItem());
        btnDelete.addActionListener(e -> deleteItem());
        btnSearch.addActionListener(e -> searchById());
        btnFilterByType.addActionListener(e -> filterByType());
        btnFilterByAnimal.addActionListener(e -> filterByAnimal());
        btnBack.addActionListener(e -> {
            MainMenu menuFrame = new MainMenu();
            menuFrame.setVisible(true);
            dispose();
        });

        loadItems();
    }

    private void loadItems() {
        tableModel.setRowCount(0);
        for (Items item : itemRepo.readAllItems()) {
            if (item instanceof Toys) {
                Toys toy = (Toys) item;
                tableModel.addRow(new Object[]{
                    toy.getId(), toy.getName(), toy.getBrand(), toy.forAnimal(), toy.getPrice(), toy.getStock(),
                    toy.getMaterial(), toy.isInteractive(), toy.getSize(),
                    null, null, null,
                    null, null, null
                });
            } else if (item instanceof Pharmacy) {
                Pharmacy pharmacy = (Pharmacy) item;
                tableModel.addRow(new Object[]{
                    pharmacy.getId(), pharmacy.getName(), pharmacy.getBrand(), pharmacy.forAnimal(), pharmacy.getPrice(), pharmacy.getStock(),
                    null, null, null,
                    pharmacy.getExpirationDate(), pharmacy.getId(), pharmacy.isPrescriptionRequired(),
                    null, null, null
                });
            } else if (item instanceof Accessories) {
                Accessories accessory = (Accessories) item;
                tableModel.addRow(new Object[]{
                    accessory.getId(), accessory.getName(), accessory.getBrand(), accessory.forAnimal(), accessory.getPrice(), accessory.getStock(),
                    null, null, null,
                    null, null, null,
                    accessory.getColor(), null, null
                });
            } else if (item instanceof Food) {
                Food food = (Food) item;
                tableModel.addRow(new Object[]{
                    food.getId(), food.getName(), food.getBrand(), food.forAnimal(), food.getPrice(), food.getStock(),
                    null, null, null,
                    null, null, null,
                    null, food.getDiet(), food.getExpiration_date()
                });
            }
        }
    }

    private void addItem() {
        String[] options = {"Toys", "Pharmacy", "Accessories", "Food"};
        String category = (String) JOptionPane.showInputDialog(
            this, "Select item category:", "Add Item", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (category == null) return;

        String name = JOptionPane.showInputDialog("Enter item name:");
        String brand = JOptionPane.showInputDialog("Enter item brand:");
        String forAnimal = JOptionPane.showInputDialog("Enter for animal (e.g., Dog, Cat):");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter stock quantity:"));
        int newId = itemRepo.readAllItems().size() + 1;
        Items newItem = null;

        switch (category) {
            case "Toys":
                String material = JOptionPane.showInputDialog("Enter material:");
                boolean isInteractive = JOptionPane.showConfirmDialog(this, "Is interactive?") == JOptionPane.YES_OPTION;
                double size = Double.parseDouble(JOptionPane.showInputDialog("Enter size:"));
                newItem = new Toys(newId, name, brand, forAnimal, price, stock, material, isInteractive, size);
                break;
            case "Pharmacy":
                String expirationDate = JOptionPane.showInputDialog("Enter expiration date:");
                String issue = JOptionPane.showInputDialog("Enter issue:");
                boolean prescriptionRequired = JOptionPane.showConfirmDialog(this, "Prescription required?") == JOptionPane.YES_OPTION;
                newItem = new Pharmacy(newId, name, brand, forAnimal, price, stock, expirationDate, prescriptionRequired, issue);
                break;
            case "Accessories":
                String color = JOptionPane.showInputDialog("Enter color:");
                String type = JOptionPane.showInputDialog("Enter type:");
                double accSize = Double.parseDouble(JOptionPane.showInputDialog("Enter size:"));
                newItem = new Accessories(newId, name, brand, forAnimal, price, stock, color, type, accSize);
                break;
            case "Food":
                String diet = JOptionPane.showInputDialog("Enter diet:");
                String foodExpiration = JOptionPane.showInputDialog("Enter expiration date:");
                newItem = new Food(newId, name, brand, forAnimal, price, stock, diet, foodExpiration, null);
                break;
        }

        if (newItem != null) {
            itemRepo.createItem(newItem);
            loadItems();
        }
    }

    private void editItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int itemId = (int) tableModel.getValueAt(selectedRow, 0);
            Items item = itemRepo.readItemById(itemId);

            String newName = JOptionPane.showInputDialog("Edit item name:", item.getName());
            String newBrand = JOptionPane.showInputDialog("Edit item brand:", item.getBrand());
            String newForAnimal = JOptionPane.showInputDialog("Edit for animal:", item.forAnimal());
            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Edit price:", item.getPrice()));
            int newStock = Integer.parseInt(JOptionPane.showInputDialog("Edit stock:", item.getStock()));

            if (item instanceof Toys) {
                Toys toy = (Toys) item;
                String newMaterial = JOptionPane.showInputDialog("Edit material:", toy.getMaterial());
                boolean newInteractive = JOptionPane.showConfirmDialog(this, "Is interactive?") == JOptionPane.YES_OPTION;
                double newSize = Double.parseDouble(JOptionPane.showInputDialog("Edit size:", toy.getSize()));
                toy.setMaterial(newMaterial);
                toy.setInteractive(newInteractive);
                toy.setSize(newSize);
            } else if (item instanceof Pharmacy) {
                Pharmacy pharmacy = (Pharmacy) item;
                String newExpirationDate = JOptionPane.showInputDialog("Edit expiration date:", pharmacy.getExpirationDate());
                String newIssue = JOptionPane.showInputDialog("Edit issue:", pharmacy.getIssueResolved());
                boolean newPrescriptionRequired = JOptionPane.showConfirmDialog(this, "Prescription required?") == JOptionPane.YES_OPTION;
                pharmacy.setExpirationDate(newExpirationDate);
                pharmacy.setIssueResolved(newIssue);
                pharmacy.setPrescriptionRequired(newPrescriptionRequired);
            } else if (item instanceof Accessories) {
                Accessories accessory = (Accessories) item;
                String newColor = JOptionPane.showInputDialog("Edit color:", accessory.getColor());
                String newType = JOptionPane.showInputDialog("Edit type:", accessory.getType());
                double newSize = Double.parseDouble(JOptionPane.showInputDialog("Edit size:", accessory.getSize()));
                accessory.setColor(newColor);
                accessory.setType(newType);
                accessory.setSize(newSize);
            } else if (item instanceof Food) {
                Food food = (Food) item;
                String newDiet = JOptionPane.showInputDialog("Edit diet:", food.getDiet());
                String newFoodExpiration = JOptionPane.showInputDialog("Edit expiration date:", food.getExpiration_date());
                food.setDiet(newDiet);
                food.setExpiration_date(newFoodExpiration);
            }

            item.setName(newName);
            item.setBrand(newBrand);
            item.setForAnimal(newForAnimal);
            item.setPrice(newPrice);
            item.setStockQuantity(newStock);
            itemRepo.updateItem(item.getId(), item);
            loadItems();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to edit.");
        }
    }

    private void deleteItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int itemId = (int) tableModel.getValueAt(selectedRow, 0);
            itemRepo.deleteItem(itemId);
            loadItems();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.");
        }
    }

    private void searchById() {
        try {
            int id = Integer.parseInt(searchField.getText().trim());
            Items item = itemRepo.readItemById(id);
            if (item != null) {
                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{
                    item.getId(), item.getName(), item.getBrand(), item.forAnimal(), item.getPrice(), item.getStock(),
                    item instanceof Toys ? ((Toys) item).getMaterial() : null,
                    item instanceof Toys ? ((Toys) item).isInteractive() : null,
                    item instanceof Toys ? ((Toys) item).getSize() : null,
                    item instanceof Pharmacy ? ((Pharmacy) item).getExpirationDate() : null,
                    item instanceof Pharmacy ? ((Pharmacy) item).getIssueResolved() : null,
                    item instanceof Pharmacy ? ((Pharmacy) item).isPrescriptionRequired() : null,
                    item instanceof Accessories ? ((Accessories) item).getColor() : null,
                    item instanceof Accessories ? ((Accessories) item).getType() : null,
                    item instanceof Food ? ((Food) item).getDiet() : null,
                    item instanceof Food ? ((Food) item).getExpiration_date() : null
                });
            } else {
                JOptionPane.showMessageDialog(this, "No item found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a numeric value.");
        }
    }

    private void filterByType() {
        String[] types = {"Toys", "Pharmacy", "Accessories", "Food"};
        String selectedType = (String) JOptionPane.showInputDialog(
            this, "Select item type:", "Filter by Type", JOptionPane.PLAIN_MESSAGE, null, types, types[0]);

        if (selectedType != null) {
            tableModel.setRowCount(0);
            for (Items item : itemRepo.readAllItems()) {
                if (item.getClass().getSimpleName().equals(selectedType)) {
                    tableModel.addRow(new Object[]{
                        item.getId(), item.getName(), item.getBrand(), item.forAnimal(), item.getPrice(), item.getStock(),
                        item instanceof Toys ? ((Toys) item).getMaterial() : null,
                        item instanceof Toys ? ((Toys) item).isInteractive() : null,
                        item instanceof Toys ? ((Toys) item).getSize() : null,
                        item instanceof Pharmacy ? ((Pharmacy) item).getExpirationDate() : null,
                        item instanceof Pharmacy ? ((Pharmacy) item).getIssueResolved() : null,
                        item instanceof Pharmacy ? ((Pharmacy) item).isPrescriptionRequired() : null,
                        item instanceof Accessories ? ((Accessories) item).getColor() : null,
                        item instanceof Accessories ? ((Accessories) item).getType() : null,
                        item instanceof Food ? ((Food) item).getDiet() : null,
                        item instanceof Food ? ((Food) item).getExpiration_date() : null
                    });
                }
            }
        }
    }

    private void filterByAnimal() {
        String forAnimal = JOptionPane.showInputDialog("Enter the animal type to filter by (e.g., Dog, Cat):");
        if (forAnimal != null && !forAnimal.isEmpty()) {
            tableModel.setRowCount(0);
            for (Items item : itemRepo.readAllItems()) {
                if (item.forAnimal().equalsIgnoreCase(forAnimal)) {
                    tableModel.addRow(new Object[]{
                        item.getId(), item.getName(), item.getBrand(), item.forAnimal(), item.getPrice(), item.getStock(),
                        item instanceof Toys ? ((Toys) item).getMaterial() : null,
                        item instanceof Toys ? ((Toys) item).isInteractive() : null,
                        item instanceof Toys ? ((Toys) item).getSize() : null,
                        item instanceof Pharmacy ? ((Pharmacy) item).getExpirationDate() : null,
                        item instanceof Pharmacy ? ((Pharmacy) item).getIssueResolved() : null,
                        item instanceof Pharmacy ? ((Pharmacy) item).isPrescriptionRequired() : null,
                        item instanceof Accessories ? ((Accessories) item).getColor() : null,
                        item instanceof Accessories ? ((Accessories) item).getType() : null,
                        item instanceof Food ? ((Food) item).getDiet() : null,
                        item instanceof Food ? ((Food) item).getExpiration_date() : null
                    });
                }
            }
        }
    }
}