package main.ui;

import main.domain.Enclosure;
import main.repository.Repo_Enclosure;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Enclosure_ui extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Enclosure enclosureRepo;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Enclosure_ui frame = new Enclosure_ui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Enclosure_ui() {
        enclosureRepo = new Repo_Enclosure();
        setTitle("Enclosures Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tableModel = new DefaultTableModel(new String[]{"ID", "Type", "Is Clean", "Is Full", "Capacity", "Temperature"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 100, 820, 400);
        contentPane.add(scrollPane);

        loadEnclosures();

        // Filter components (as before)
        JLabel lblFilter = new JLabel("Filter by Type:");
        lblFilter.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblFilter.setBounds(30, 30, 100, 30);
        contentPane.add(lblFilter);

        JComboBox<String> cbFilterType = new JComboBox<>(new String[]{"All", "Tank", "Cage", "Terrarium"});
        cbFilterType.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cbFilterType.setBounds(140, 30, 150, 30);
        contentPane.add(cbFilterType);

        cbFilterType.addActionListener(e -> filterByType((String) cbFilterType.getSelectedItem()));

        // Search components (as before)
        JLabel lblSearch = new JLabel("Search by ID:");
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSearch.setBounds(350, 30, 100, 30);
        contentPane.add(lblSearch);

        JTextField txtSearch = new JTextField();
        txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtSearch.setBounds(460, 30, 150, 30);
        contentPane.add(txtSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSearch.setBounds(620, 30, 100, 30);
        contentPane.add(btnSearch);

        btnSearch.addActionListener(e -> searchById(txtSearch.getText()));

        JButton btnFilterNotClean = new JButton("Show Not Clean");
        btnFilterNotClean.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnFilterNotClean.setBounds(30, 520, 150, 30);
        contentPane.add(btnFilterNotClean);
        btnFilterNotClean.addActionListener(e -> filterByNotClean());

        startResetCleanTimer();

        // Edit button with ActionListener added
        JButton btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnEdit.setBounds(30, 560, 100, 30);
        contentPane.add(btnEdit);

        btnEdit.addActionListener(e -> editSelectedEnclosure());  // Add ActionListener to the Edit button

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnBack.setBounds(720, 520, 100, 30);
        contentPane.add(btnBack);
        btnBack.addActionListener(e -> {
            MainMenu menuFrame = new MainMenu();
            menuFrame.setVisible(true);
            dispose();
        });
    }

    private void loadEnclosures() {
        tableModel.setRowCount(0); 
        for (Enclosure e : enclosureRepo.readAllEnclosures()) {
            tableModel.addRow(new Object[]{
                    e.getId(),
                    e.getType(),
                    e.isClean(),
                    e.isFull(),
                    e.getCapacity(),
                    e.getTemperature()
            });
        }
    }

    private void editSelectedEnclosure() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Enclosure enclosure = enclosureRepo.readEnclosureById(id);
            if (enclosure != null) {
                openEditDialog(enclosure);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an enclosure to edit.");
        }
    }

    private void openEditDialog(Enclosure enclosure) {
        JDialog editDialog = new JDialog(this, "Edit Enclosure", true);
        editDialog.setSize(400, 300);
        editDialog.setLayout(new GridLayout(6, 2));

        // ComboBox for type selection
        JComboBox<String> cbType = new JComboBox<>(new String[]{"Tank", "Cage", "Terrarium"});
        cbType.setSelectedItem(enclosure.getType().toString());  // Set the selected type from the enclosure
        JTextField txtCapacity = new JTextField(String.valueOf(enclosure.getCapacity()));
        JTextField txtTemperature = new JTextField(String.valueOf(enclosure.getTemperature()));
        JCheckBox chkClean = new JCheckBox("Clean", enclosure.isClean());
        JCheckBox chkFull = new JCheckBox("Full", enclosure.isFull());

        editDialog.add(new JLabel("Type:"));
        editDialog.add(cbType);
        editDialog.add(new JLabel("Capacity:"));
        editDialog.add(txtCapacity);
        editDialog.add(new JLabel("Temperature:"));
        editDialog.add(txtTemperature);
        editDialog.add(chkClean);
        editDialog.add(new JLabel("Is Full:"));
        editDialog.add(chkFull);

        JButton btnSave = new JButton("Save Changes");
        btnSave.addActionListener(e -> {
            String type = (String) cbType.getSelectedItem();
            int capacity = Integer.parseInt(txtCapacity.getText());
            double temperature = Double.parseDouble(txtTemperature.getText());
            boolean isClean = chkClean.isSelected();
            boolean isFull = chkFull.isSelected();

            // Update enclosure with new data
            enclosure.setType(Enclosure.EnclosureType.valueOf(type.toUpperCase()));
            enclosure.setCapacity(capacity);
            enclosure.setTemperature(temperature);
            enclosure.setClean(isClean);
            enclosure.setFull(isFull);

            enclosureRepo.saveToFile();  // Save the changes
            loadEnclosures();  // Reload the table with updated data

            editDialog.dispose();  // Close the edit dialog
        });

        editDialog.add(btnSave);

        editDialog.setVisible(true);
    }

    // Filter methods (as before)
    private void filterByType(String type) {
        tableModel.setRowCount(0); 
        List<Enclosure> filteredEnclosures = enclosureRepo.readAllEnclosures().stream()
                .filter(e -> "All".equals(type) || e.getType().toString().equalsIgnoreCase(type))
                .collect(Collectors.toList());

        for (Enclosure e : filteredEnclosures) {
            tableModel.addRow(new Object[]{
                    e.getId(),
                    e.getType(),
                    e.isClean(),
                    e.isFull(),
                    e.getCapacity(),
                    e.getTemperature()
            });
        }
    }

    private void filterByNotClean() {
        tableModel.setRowCount(0);
        List<Enclosure> notCleanEnclosures = enclosureRepo.readAllEnclosures().stream()
                .filter(e -> !e.isClean())
                .collect(Collectors.toList());

        for (Enclosure e : notCleanEnclosures) {
            tableModel.addRow(new Object[]{
                    e.getId(),
                    e.getType(),
                    e.isClean(),
                    e.isFull(),
                    e.getCapacity(),
                    e.getTemperature()
            });
        }
    }

    private void searchById(String idText) {
        try {
            int id = Integer.parseInt(idText);
            tableModel.setRowCount(0); 
            Enclosure e = enclosureRepo.readEnclosureById(id);
            if (e != null) {
                tableModel.addRow(new Object[]{
                        e.getId(),
                        e.getType(),
                        e.isClean(),
                        e.isFull(),
                        e.getCapacity(),
                        e.getTemperature()
                });
            } else {
                JOptionPane.showMessageDialog(this, "No enclosure found with ID: " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.");
        }
    }

    private void startResetCleanTimer() {
        Timer timer = new Timer(24 * 60 * 60 * 1000, e -> {
            List<Enclosure> enclosures = enclosureRepo.readAllEnclosures();
            for (Enclosure enclosure : enclosures) {
                enclosure.setClean(false);
            }
            enclosureRepo.saveToFile(); 
            loadEnclosures(); 
            JOptionPane.showMessageDialog(this, "All enclosures have been reset to not clean.");
        });
        timer.start();
    }
}
