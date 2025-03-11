package main.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.domain.Animals;
import main.domain.Rodents;
import main.repository.Repo_Animals;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pet_Rodents extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Animals animalRepo;
    private JComboBox<Rodents.RodentType> rodentTypeComboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Repo_Animals animalRepo = new Repo_Animals();
                Pet_Rodents frame = new Pet_Rodents(animalRepo);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor
    public Pet_Rodents(Repo_Animals animalRepo) {
        this.animalRepo = animalRepo;
        initialize();
    }

    public Pet_Rodents() {
        this(new Repo_Animals()); 
    }

    private void initialize() {
        setTitle("Rodents");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 639, 735);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout());
        setContentPane(contentPane);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Fur Color", "Size", "Rodent Type"}, 0);
        table = new JTable(tableModel);
        loadRodents();

        setTableColumnWidths();

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane);

        JPanel buttonPanel = createButtonPanel();
        contentPane.add(buttonPanel);
    }

    private void setTableColumnWidths() {
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdd = new JButton("Add Rodent");
        btnAdd.addActionListener(e -> addRodent());

        JButton btnEdit = new JButton("Edit Rodent");
        btnEdit.addActionListener(e -> editRodent());

        JButton btnDelete = new JButton("Delete Rodent");
        btnDelete.addActionListener(e -> deleteRodent());

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            AnimalsUI animalFrame = new AnimalsUI();
            animalFrame.setVisible(true);
            dispose();
        });

        rodentTypeComboBox = new JComboBox<>(Rodents.RodentType.values());

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);

        return buttonPanel;
    }

    private void loadRodents() {
        tableModel.setRowCount(0);
        for (Animals animal : animalRepo.getAllAnimals("rodents")) {
            if (animal instanceof Rodents) {
                Rodents rodent = (Rodents) animal;
                tableModel.addRow(new Object[]{
                        rodent.getId(),
                        rodent.getName(),
                        rodent.getFurColor(),
                        rodent.getSize(),
                        rodent.getRodentType().toString()
                });
            }
        }
    }

    // Add a new rodent
    private void addRodent() {
        String name = JOptionPane.showInputDialog("Enter rodent name:");
        String furColor = JOptionPane.showInputDialog("Enter fur color:");
        String sizeInput = JOptionPane.showInputDialog("Enter rodent size:");

        if (name != null && furColor != null && sizeInput != null) {
            try {
                double size = Double.parseDouble(sizeInput);
                int newId = animalRepo.getAllAnimals("").size() + 1;
                Rodents.RodentType rodentType = (Rodents.RodentType) JOptionPane.showInputDialog(
                        this,
                        "Select Rodent Type",
                        "Rodent Type",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Rodents.RodentType.values(),
                        Rodents.RodentType.RABBIT
                );

                if (rodentType != null) {
                    Rodents newRodent = new Rodents(newId, name, "Female", 3, 1.5, "Omnivore", 30.0, true, furColor, size, rodentType);
                    animalRepo.create(newRodent, "rodents");
                    loadRodents();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input for rodent size.");
            }
        }
    }

    private void editRodent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int rodentId = (int) tableModel.getValueAt(selectedRow, 0);
            Rodents rodent = (Rodents) animalRepo.readById(rodentId, "rodents");

            String newName = JOptionPane.showInputDialog("Edit rodent name:", rodent.getName());
            String newFurColor = JOptionPane.showInputDialog("Edit fur color:", rodent.getFurColor());
            String sizeInput = JOptionPane.showInputDialog("Edit rodent size:", rodent.getSize());

            Rodents.RodentType selectedType = (Rodents.RodentType) JOptionPane.showInputDialog(
                    this,
                    "Select Rodent Type",
                    "Rodent Type",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Rodents.RodentType.values(),
                    rodent.getRodentType()
            );

            if (newName != null && newFurColor != null && sizeInput != null && selectedType != null) {
                try {
                    double size = Double.parseDouble(sizeInput);

                    rodent.setName(newName);
                    rodent.setFurColor(newFurColor);
                    rodent.setSize(size);
                    rodent.setRodentType(selectedType);

                    animalRepo.updateAnimal(rodent.getId(), rodent, sizeInput);
                    loadRodents();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input for rodent size.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a rodent to edit.");
        }
    }

    // Delete an existing rodent
    private void deleteRodent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int rodentId = (int) tableModel.getValueAt(selectedRow, 0);
            animalRepo.delete(rodentId, "rodents");
            loadRodents();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a rodent to delete.");
        }
    }
}
