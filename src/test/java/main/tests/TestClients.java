package main.tests;
import main.domain.Clients;
import main.domain.Items;
import main.services.ItemsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TestClients {

    private Clients client;
    private ItemsServices itemsServices;
    private Items item;

    @BeforeEach
    void setUp() {
        itemsServices = new ItemsServices();
        client = new Clients(1, "john.doe@example.com", new ArrayList<>());
        item = new Items("Toy", 10, 5.99); // Example item with name, quantity, and price
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1, client.getId());
        assertEquals("john.doe@example.com", client.getEmail());
        assertTrue(client.getPurchases().isEmpty());
    }

    @Test
    void testSetters() {
        client.setId(2);
        client.setEmail("jane.doe@example.com");

        assertEquals(2, client.getId());
        assertEquals("jane.doe@example.com", client.getEmail());
    }

    @Test
    void testAddPurchase() {
        client.addPurchase(item, itemsServices);

        assertEquals(1, client.getPurchases().size());
        assertEquals("Toy", client.getPurchases().get(0)); 
    }

    @Test
    void testToString() {
        String expectedString = "Client Details:\nID: 1\nEmail: john.doe@example.com\nPurchase History:\nNo purchases made yet.\n";
        assertEquals(expectedString, client.toString());

        client.addPurchase(item, itemsServices);
        expectedString = "Client Details:\nID: 1\nEmail: john.doe@example.com\nPurchase History:\n- Toy\n";
        assertEquals(expectedString, client.toString());
    }
}

