package main.tests;

import main.domain.Items;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItems {

    private Items item;

    @BeforeEach
    void setUp() {
        // Initializing a basic item (e.g., food or medicine)
        item = new Items(1, "Dog Food", "BrandA", "Dog", 25.0, 100);
    }

    @Test
    void testItemsConstructorAndGetters() {
        assertEquals(1, item.getId());
        assertEquals("Dog Food", item.getName());
        assertEquals("BrandA", item.getBrand());
        assertEquals("Dog", item.forAnimal());
        assertEquals(25.0, item.getPrice());
        assertEquals(100, item.getStock());
    }

    @Test
    void testItemsSetters() {
        item.setName("Cat Food");
        item.setBrand("BrandC");
        item.setForAnimal("Cat");
        item.setPrice(30.0);
        item.setStockQuantity(150);

        assertEquals("Cat Food", item.getName());
        assertEquals("BrandC", item.getBrand());
        assertEquals("Cat", item.forAnimal());
        assertEquals(30.0, item.getPrice());
        assertEquals(150, item.getStock());
    }

    @Test
    void testItemsPurchaseDetails() {
        String expectedDetails = "Dog Food (Brand: BrandA, Price: $25.0)";
        assertEquals(expectedDetails, item.getPurchaseDetails());
    }

    @Test
    void testItemsToString() {
        String expectedString = "Item Details:\n" +
                "ID: 1\n" +
                "Name: Dog Food\n" +
                "Brand: BrandA\n" +
                "Animal Destined To: Dog\n" +
                "Price: $25.0\n" +
                "Stock Quantity: 100";
        assertEquals(expectedString, item.toString());
    }
}

