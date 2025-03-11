package main.domain;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Appointments reprezintă un set de programari care includ informații despre tipul de programare pentru animăluțe (vet sau grooming), cât și data și ora programării.
 * Aceasta oferă metode pentru accesarea și modificarea informațiilor despre programări.
 */

public class Appointments {
    private int id;  // Unique ID for each appointment
    private String appType;  // Type of appointment (e.g., Vet Check-up, Grooming, etc.)
    private String client;   // Client's name
    private String date;     // Appointment date (e.g., "2025-01-20")
    private String time;     // Appointment time (e.g., "09:30")

    // Default constructor
    public Appointments() {
    }

    // Constructor with parameters, including the ID
    public Appointments(int id, String appType, String client, String date, String time) {
        this.id = id;
        this.appType = appType;
        this.client = client;
        this.date = date;
        this.time = time;
    }

    // Getter and setter methods for all properties
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointmentType() {
        return appType;
    }

    public void setAppointmentType(String appType) {
        this.appType = appType;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Return a description of the appointment as a string
    @Override
    public String toString() {
        return "ID: " + id + ", Type: '" + appType + "' appointment for " + client +
                ", Date: '" + date + "', Time: '" + time + "'";
    }
}
