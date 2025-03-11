package main.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.domain.Clients;
import main.repository.Repo_Clients;

public class Clients_ui extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Clients clientRepo;
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Clients_ui frame = new Clients_ui(); // Uses the no-argument constructor
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Clients_ui() {
        this(new Repo_Clients()); 
    }

    // Main constructor
    public Clients_ui(Repo_Clients clientRepo) {
        this.clientRepo = clientRepo;
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\\\Users\\\\ALEXIA\\\\Downloads\\\\5042264.png"));
        setTitle("Clients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 641, 735);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

    
    

        tableModel = new DefaultTableModel(new String[]{"ID", "Email", "Purchases"}, 0);
        table = new JTable(tableModel);
        loadClients(); 

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdd = new JButton("Add Client");
        JButton btnEdit = new JButton("Edit Client");
        JButton btnDelete = new JButton("Delete Client");
        JButton btnBack = new JButton("Back");

        btnAdd.addActionListener(e -> addClient());
        btnEdit.addActionListener(e -> editClient());
        btnDelete.addActionListener(e -> deleteClient());
        btnBack.addActionListener(e -> {
            
            MainMenu menuFrame = new MainMenu(); 
            menuFrame.setVisible(true);
            dispose(); 
        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadClients() {
        tableModel.setRowCount(0); // Clear any existing rows
        for (Clients client : clientRepo.getAllClients()) {
            tableModel.addRow(new Object[]{
                client.getId(),
                client.getEmail(),
                String.join(", ", client.getPurchases())  // Join the list of purchases into a comma-separated string
            });
        }
    }

    private void addClient() {
        String email = JOptionPane.showInputDialog("Enter client email:");

        int newId = 0;  

        if (email != null && !email.isEmpty()) {
            newId = clientRepo.getAllClients().size() + 1;
        }

        ArrayList<String> purchases = new ArrayList<>();
        String purchase;
        while (true) {
            purchase = JOptionPane.showInputDialog("Enter purchase (or leave empty to finish):");
            if (purchase == null || purchase.isEmpty()) {
                break; 
            }
            purchases.add(purchase); 
        }

        Clients newClient = new Clients(newId, email != null && !email.isEmpty() ? email : null, purchases);

        clientRepo.addClient(newClient);
        loadClients(); 
    }


    private void editClient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int clientId = (int) tableModel.getValueAt(selectedRow, 0);
            Clients client = clientRepo.getClientById(clientId);

            String newEmail = JOptionPane.showInputDialog("Edit client email:", client.getEmail());
            if (newEmail != null && !newEmail.isEmpty()) {
                client.setEmail(newEmail); 

                clientRepo.updateClient(client.getId(), client);
                loadClients(); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a client to edit.");
        }
    }

    private void deleteClient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int clientId = (int) tableModel.getValueAt(selectedRow, 0);
            boolean deleted = clientRepo.deleteClient(clientId);
            if (deleted) {
                loadClients(); 
            } else {
                JOptionPane.showMessageDialog(this, "Client not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a client to delete.");
        }
    }
}
