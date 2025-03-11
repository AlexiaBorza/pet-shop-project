package main.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import main.domain.Appointments;
import main.repository.Repo_Appointments;

public class Appointments_ui extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private Repo_Appointments appointmentRepo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Appointments_ui frame = new Appointments_ui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Appointments_ui() {
        appointmentRepo = new Repo_Appointments(); 
        setTitle("Appointments");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ALEXIA\\Downloads\\5042264.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 800);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(183, 211, 217));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tableModel = new DefaultTableModel(new String[]{"ID", "Appointment Type", "Client", "Date", "Time"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(43, 40, 600, 556);
        contentPane.add(scrollPane);

        loadAppointments();

        JButton btnAddAppointment = new JButton("Add Appointment");
        btnAddAppointment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAddAppointment.setBounds(206, 640, 133, 30);
        contentPane.add(btnAddAppointment);
        btnAddAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppointment();
            }
        });

        JButton btnEditAppointment = new JButton("Edit Appointment");
        btnEditAppointment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnEditAppointment.setBounds(349, 640, 142, 30);
        contentPane.add(btnEditAppointment);
        btnEditAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAppointment();
            }
        });

        JButton btnDeleteAppointment = new JButton("Delete Appointment");
        btnDeleteAppointment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnDeleteAppointment.setBounds(501, 640, 142, 30);
        contentPane.add(btnDeleteAppointment);
        btnDeleteAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAppointment();
            }
        });
        
        

        JButton btnFilter = new JButton("Filter");
        btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnFilter.setBounds(43, 0, 100, 30);
        contentPane.add(btnFilter);
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterAppointments();
            }
        });

        JButton btnSort = new JButton("Sort by Date");
        btnSort.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSort.setBounds(513, 0, 130, 30);
        contentPane.add(btnSort);
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortAppointments();
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnBack.setBounds(64, 638, 122, 30);
        contentPane.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu menuFrame = new MainMenu();
                menuFrame.setVisible(true);
                dispose(); 
            }
        });
    }

    private void loadAppointments() {
        tableModel.setRowCount(0); // Clear the table
        for (Appointments appointment : appointmentRepo.getAllAppointments()) {
            tableModel.addRow(new Object[]{
                appointment.getId(),
                appointment.getAppointmentType(),
                appointment.getClient(),
                appointment.getDate(),
                appointment.getTime()
            });
        }
    }

    private void addAppointment() {
        String appType = JOptionPane.showInputDialog("Enter appointment type:");
        String client = JOptionPane.showInputDialog("Enter client name:");
        String date = JOptionPane.showInputDialog("Enter appointment date (e.g., 12.02.2025):");
        String time = JOptionPane.showInputDialog("Enter appointment time (e.g., 14:00):");
        if (appType != null && client != null && date != null && time != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date appointmentDate = dateFormat.parse(date);
                Date currentDate = new Date();
                if (appointmentDate.before(currentDate)) {
                    JOptionPane.showMessageDialog(this, "The appointment date must be in the future.");
                    return;  
                }
                int id = 0;
                Appointments newAppointment = new Appointments(id, appType, client, date, time);
                appointmentRepo.addAppointment(newAppointment);
                loadAppointments();
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use dd.MM.yyyy.");
            }
        }
    }

    private void editAppointment() {
        int selectedRow = table.getSelectedRow(); 
        if (selectedRow != -1) {
            int appointmentId = (int) tableModel.getValueAt(selectedRow, 0); 
            String currentAppType = (String) tableModel.getValueAt(selectedRow, 1);
            String currentClient = (String) tableModel.getValueAt(selectedRow, 2);
            String currentDate = (String) tableModel.getValueAt(selectedRow, 3);
            String currentTime = (String) tableModel.getValueAt(selectedRow, 4);

            String newAppType = JOptionPane.showInputDialog("Edit appointment type:", currentAppType);
            String newClient = JOptionPane.showInputDialog("Edit client name:", currentClient);
            String newDate = JOptionPane.showInputDialog("Edit appointment date:", currentDate);
            String newTime = JOptionPane.showInputDialog("Edit appointment time:", currentTime);

            if (newAppType != null && newClient != null && newDate != null && newTime != null) {
                Appointments updatedAppointment = new Appointments(appointmentId, newAppType, newClient, newDate, newTime);
                boolean isUpdated = appointmentRepo.updateAppointmentById(appointmentId, updatedAppointment);
                if (isUpdated) {
                    loadAppointments(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update the appointment. Please try again.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an appointment to edit.");
        }
    }

    private void deleteAppointment() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int appointmentId = (int) tableModel.getValueAt(selectedRow, 0); 
            boolean isDeleted = appointmentRepo.deleteAppointmentById(appointmentId);

            if (isDeleted) {
                loadAppointments(); 
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete the appointment.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an appointment to delete.");
        }
    }

    private void filterAppointments() {
        String filterType = JOptionPane.showInputDialog("Enter appointment type to filter (e.g., Vet, Grooming):");
        if (filterType != null && !filterType.isEmpty()) {
            List<Appointments> filteredAppointments = appointmentRepo.getAllAppointments().stream()
                .filter(a -> a.getAppointmentType().equalsIgnoreCase(filterType))
                .collect(Collectors.toList());

            tableModel.setRowCount(0); // Clear the table
            for (Appointments appointment : filteredAppointments) {
                tableModel.addRow(new Object[]{
                    appointment.getId(),
                    appointment.getAppointmentType(),
                    appointment.getClient(),
                    appointment.getDate(),
                    appointment.getTime()
                });
            }
        }
    }

    private void sortAppointments() {
        List<Appointments> sortedAppointments = appointmentRepo.getAllAppointments().stream()
            .sorted((a1, a2) -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    Date date1 = dateFormat.parse(a1.getDate());
                    Date date2 = dateFormat.parse(a2.getDate());
                    return date1.compareTo(date2); // Ascending order
                } catch (ParseException e) {
                    e.printStackTrace(); // Log parsing errors for debugging
                    return 0; // Keep original order in case of error
                }
            })
            .collect(Collectors.toList());

        // Update the table with sorted appointments
        tableModel.setRowCount(0); // Clear the table
        for (Appointments appointment : sortedAppointments) {
            tableModel.addRow(new Object[]{
                appointment.getId(),
                appointment.getAppointmentType(),
                appointment.getClient(),
                appointment.getDate(),
                appointment.getTime()
            });
        }
    }
}
