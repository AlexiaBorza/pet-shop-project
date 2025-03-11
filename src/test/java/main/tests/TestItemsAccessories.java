package main.tests;

import main.domain.Accessories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItemsAccessories {

    private Accessories accessory;

    @BeforeEach
    void setUp() {
        accessory = new Accessories(2, "Leash", "BrandB", "Dog", 15.0, 50, "Red", "Leash", 1.5);
    }

    @Test
    void testAccessoriesConstructorAndGetters() {
        assertEquals(2, accessory.getId());
        assertEquals("Leash", accessory.getName());
        assertEquals("BrandB", accessory.getBrand());
        assertEquals("Dog", accessory.forAnimal());
        assertEquals(15.0, accessory.getPrice());
        assertEquals(50, accessory.getStock());
        assertEquals("Red", accessory.getColor());
        assertEquals("Leash", accessory.getType());
        assertEquals(1.5, accessory.getSize());
    }

    @Test
    void testAccessoriesSetters() {
        accessory.setColor("Blue");
        accessory.setType("Collar");
        accessory.setSize(1.0);
        accessory.setName("Cat Collar");

        assertEquals("Blue", accessory.getColor());
        assertEquals("Collar", accessory.getType());
        assertEquals(1.0, accessory.getSize());
        assertEquals("Cat Collar", accessory.getName());
    }

    @Test
    void testAccessoriesToString() {
        String expectedString = "Item Details:\n" +
                "ID: 2\n" +
                "Name: Leash\n" +
                "Brand: BrandB\n" +
                "Animal Destined To: Dog\n" +
                "Price: $15.0\n" +
                "Stock Quantity: 50\n" +
                "Color: Red\n" +
                "Type: Leash\n" +
                "Size1.5";
        assertEquals(expectedString, accessory.toString());
    }
}

