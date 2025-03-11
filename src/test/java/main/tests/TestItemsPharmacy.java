package main.tests;
import main.domain.Pharmacy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItemsPharmacy {

    @Test
    void testPharmacyConstructor() {
        Pharmacy pharmacy = new Pharmacy(1, "Pain Relief", "VetMed", "Dog", 15.99, 100, "2025-05-15", true, "Pain");

        assertEquals(1, pharmacy.getId());
        assertEquals("Pain Relief", pharmacy.getName());
        assertEquals("VetMed", pharmacy.getBrand());
        assertEquals("Dog", pharmacy.forAnimal());
        assertEquals(15.99, pharmacy.getPrice());
        assertEquals(100, pharmacy.getStock());
        assertEquals("2025-05-15", pharmacy.getExpirationDate());
        assertTrue(pharmacy.isPrescriptionRequired());
        assertEquals("Pain", pharmacy.getIssueResolved());
    }

    @Test
    void testPharmacyToString() {
        Pharmacy pharmacy = new Pharmacy(2, "Antibiotic", "MediPets", "Cat", 12.49, 50, "2024-12-01", true, "Infection");

        String expectedString = "Item Details:\n" +
                "ID: 2\n" +
                "Name: Antibiotic\n" +
                "Brand: MediPets\n" +
                "Animal Destined To: Cat\n" +
                "Price: $12.49\n" +
                "Stock Quantity: 50\n" +
                "Expiration Date: 2024-12-01\n" +
                "Prescription Required: true\n" +
                "Recommended to combat: Infection";

        assertEquals(expectedString, pharmacy.toString());
    }

    @Test
    void testPharmacyExpirationDate() {
        Pharmacy pharmacy = new Pharmacy(3, "Flea Treatment", "PawCare", "Rabbit", 9.99, 30, "2026-03-10", false, "Fleas");

        assertEquals("2026-03-10", pharmacy.getExpirationDate());
    }

    @Test
    void testPharmacyPrescriptionRequired() {
        Pharmacy pharmacy1 = new Pharmacy(4, "Antifungal", "VetCure", "Dog", 18.99, 20, "2025-10-20", true, "Fungal Infection");
        Pharmacy pharmacy2 = new Pharmacy(5, "Vitamin Supplement", "HealthyPets", "Cat", 8.49, 100, "2026-05-30", false, "General Health");

        assertTrue(pharmacy1.isPrescriptionRequired());
        assertFalse(pharmacy2.isPrescriptionRequired());
    }

    @Test
    void testPharmacyIssueResolved() {
        Pharmacy pharmacy = new Pharmacy(6, "Worm Medicine", "PetCare", "Rabbit", 14.99, 10, "2025-08-15", true, "Parasites");

        assertEquals("Parasites", pharmacy.getIssueResolved());
    }
}

