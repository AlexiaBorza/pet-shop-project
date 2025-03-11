package main.services;
import java.util.List;

import main.domain.Appointments;
import main.repository.Repo_Animals;
import main.repository.Repo_Appointments;
import java.util.ArrayList;
import java.util.List;


public class AppointmentsServices {
	private Repo_Appointments repo_app;

public class Repo_Appointments {
    private List<Appointments> appointmentList;

    
    public Repo_Appointments() {
        this.appointmentList = new ArrayList<>();
    }


    public void addAppointment(Appointments appointment) {
        appointmentList.add(appointment);
    }

    public Appointments getAppointmentByClient(String client) {
        return appointmentList.stream()
                .filter(app -> app.getClient().equalsIgnoreCase(client))
                .findFirst()
                .orElse(null);
    }

    public List<Appointments> getAllAppointments() {
        return new ArrayList<>(appointmentList);
    }

    public boolean updateAppointment(String client, Appointments updatedAppointment) {
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getClient().equalsIgnoreCase(client)) {
                appointmentList.set(i, updatedAppointment);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAppointment(String client) {
        return appointmentList.removeIf(app -> app.getClient().equalsIgnoreCase(client));
    }
}
}
