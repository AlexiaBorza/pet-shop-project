package main.repository;

import main.domain.Appointments;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing appointments.
 */
public class Repo_Appointments {
    private List<Appointments> appointmentList;
    private static final String FILE_NAME = "C:\\Users\\ALEXIA\\git\\p3-proiect-gr2-AndreeaBorza\\src\\main\\java\\main\\domain\\data\\Appointments.txt";
    private int nextId;

    /**
     * Default constructor that initializes the appointment list and loads the appointments from the file.
     */
    public Repo_Appointments() {
        this.appointmentList = new ArrayList<>();
        loadAppointmentsFromFile1(); // Load appointments when the Repo_Appointments is created
    }

    /**
     * Loads appointments from the file and updates the nextId based on the highest ID found.
     */
    public void loadAppointmentsFromFile1() {
        // Define the file to read from
        File file = new File(FILE_NAME);

        // Check if the file exists
        if (!file.exists()) {
            System.err.println("File not found: " + FILE_NAME);
            return;
        }

        int maxId = 0;  // Track the maximum ID found in the file

        // Read the file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();  // Skip the header line if needed
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                try {
                    // Process each line
                    int id = Integer.parseInt(fields[0].trim());  // Parse the ID
                    maxId = Math.max(maxId, id);  // Keep track of the highest ID
                    String appType = fields[1].trim();
                    String client = fields[2].trim();
                    String date = fields[3].trim();
                    String time = fields[4].trim();

                    // Add to the appointment list
                    Appointments appointment = new Appointments(id, appType, client, date, time);
                    appointmentList.add(appointment);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid appointment ID format, skipping row: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle file read errors
        }

        // Set the nextId to be one greater than the maximum ID found
        this.nextId = maxId + 1;
    }

    // Create
    /**
     * Adds a new appointment to the list and saves it to the file.
     *
     * @param appointment The Appointment object to be added.
     */
    public void addAppointment(Appointments appointment) {
        appointment.setId(nextId);  // Set the appointment's ID before adding it
        appointmentList.add(appointment);
        nextId++;  // Increment the ID for the next appointment
        saveAppointmentsToFile();  // Save to the file whenever a new appointment is added
    }

    // Read
    /**
     * Gets the first appointment found by client name.
     *
     * @param client The client name to search for.
     * @return The Appointment object if found, null otherwise.
     */
    public Appointments getAppointmentByClient(String client) {
        return appointmentList.stream()
                .filter(app -> app.getClient().equalsIgnoreCase(client))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets all appointments in the list.
     *
     * @return A list of all appointments.
     */
    public List<Appointments> getAllAppointments() {
        return new ArrayList<>(appointmentList);
    }

    // Update
    /**
     * Updates an appointment by client name.
     *
     * @param client The client name to search for.
     * @param updatedAppointment The updated appointment data.
     * @return true if the update is successful, false otherwise.
     */
    public boolean updateAppointment(String client, Appointments updatedAppointment) {
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getClient().equalsIgnoreCase(client)) {
                appointmentList.set(i, updatedAppointment);
                saveAppointmentsToFile(); // Save to the file after updating
                return true;
            }
        }
        return false;
    }

    /**
     * Updates an appointment by ID.
     *
     * @param id The ID of the appointment to update.
     * @param updatedAppointment The updated appointment data.
     * @return true if the update is successful, false otherwise.
     */
    public boolean updateAppointmentById(int id, Appointments updatedAppointment) {
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getId() == id) {
                appointmentList.set(i, updatedAppointment);  // Update the appointment
                saveAppointmentsToFile();  // Save changes to the file
                return true;
            }
        }
        return false;  // If the appointment with the given id is not found
    }

    // Delete
    /**
     * Deletes an appointment based on its type, client, date, and time.
     *
     * @param appointmentType The appointment type to match.
     * @param clientName The client name to match.
     * @param date The date of the appointment.
     * @param time The time of the appointment.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteAppointment(String appointmentType, String clientName, String date, String time) {
        boolean isDeleted = appointmentList.removeIf(app ->
                app.getAppointmentType().equalsIgnoreCase(appointmentType) &&
                        app.getClient().equalsIgnoreCase(clientName) &&
                        app.getDate().equalsIgnoreCase(date) &&
                        app.getTime().equalsIgnoreCase(time)
        );

        if (isDeleted) {
            saveAppointmentsToFile(); // Save to the file after deletion
        }
        return isDeleted;
    }

    /**
     * Deletes an appointment based on its ID.
     *
     * @param id The ID of the appointment to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteAppointmentById(int id) {
        boolean isDeleted = appointmentList.removeIf(app -> app.getId() == id);  // Match based on ID
        if (isDeleted) {
            saveAppointmentsToFile();  // Save changes to the file
        }
        return isDeleted;
    }

    /**
     * Saves the appointments list back to the file.
     */
    private void saveAppointmentsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Appointments appointment : appointmentList) {
                bw.write(appointment.getId() + "," +
                        appointment.getAppointmentType() + "," +
                        appointment.getClient() + "," +
                        appointment.getDate() + "," +
                        appointment.getTime());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
