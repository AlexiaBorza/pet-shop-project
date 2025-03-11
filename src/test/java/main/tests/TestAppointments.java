package main.tests;

import main.domain.Appointments;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAppointments {

    @Test
    void testConstructorAndGetters() {
        Appointments appointment = new Appointments("Vet", "John Doe", "2025-01-18", "10:00 AM");

        assertEquals("Vet", appointment.getAppointmentType());
        assertEquals("John Doe", appointment.getClient());
        assertEquals("2025-01-18", appointment.getDate());
        assertEquals("10:00 AM", appointment.getTime());
    }

    @Test
    void testSetters() {
        Appointments appointment = new Appointments("Grooming", "Jane Smith", "2025-01-20", "11:30 AM");

        appointment.setAppointmentType("Vet");
        appointment.setClient("Emily White");
        appointment.setDate("2025-01-22");
        appointment.setTime("2:00 PM");

        assertEquals("Vet", appointment.getAppointmentType());
        assertEquals("Emily White", appointment.getClient());
        assertEquals("2025-01-22", appointment.getDate());
        assertEquals("2:00 PM", appointment.getTime());
    }

    @Test
    void testToString() {
        Appointments appointment = new Appointments("Vet", "John Doe", "2025-01-18", "10:00 AM");

        String expected = " Type: 'Vet' appointment forJohn Doe, Date: '2025-01-18', Time: '10:00 AM'";
        assertEquals(expected, appointment.toString());
    }

    @Test
    void testAppointmentsList() {
        Appointments appointment1 = new Appointments("Vet", "John Doe", "2025-01-18", "10:00 AM");
        Appointments appointment2 = new Appointments("Grooming", "Jane Smith", "2025-01-20", "11:30 AM");

        Appointments appointmentsObj = new Appointments();
        appointmentsObj.getAppointments().add(appointment1);
        appointmentsObj.getAppointments().add(appointment2);

        assertEquals(2, appointmentsObj.getAppointments().size());
        assertEquals(appointment1, appointmentsObj.getAppointments().get(0));
        assertEquals(appointment2, appointmentsObj.getAppointments().get(1));
    }
}

