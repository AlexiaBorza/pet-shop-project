package main.tests;

import main.domain.Toys;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItemsToys {

    @Test
    void testToysConstructor() {
        Toys toy = new Toys(1, "Ball", "PetPlay", "Dog", 5.99, 100, "Rubber", true, 10.0);

        assertEquals(1, toy.getId());
        assertEquals("Ball", toy.getName());
        assertEquals("PetPlay", toy.getBrand());
        assertEquals("Dog", toy.forAnimal());
        assertEquals(5.99, toy.getPrice());
        assertEquals(100, toy.getStock());
        assertEquals("Rubber", toy.getMaterial());
        assertTrue(toy.isInteractive());
        assertEquals(10.0, toy.getSize());
    }

    @Test
    void testToysToString() {
        Toys toy = new Toys(2, "Chew Bone", "BarkToys", "Dog", 3.49, 50, "Plastic", false, 8.5);

        String expectedString = "Item Details:\n" +
                "ID: 2\n" +
                "Name: Chew Bone\n" +
                "Brand: BarkToys\n" +
                "Animal Destined To: Dog\n" +
                "Price: $3.49\n" +
                "Stock Quantity: 50\n" +
                "Material: Plastic\n" +
                "Interactive: false\n" +
                "Size8.5";

        assertEquals(expectedString, toy.toString());
    }

    @Test
    void testToysMaterial() {
        Toys toy = new Toys(3, "Frisbee", "FetchToys", "Dog", 7.99, 60, "Plastic", true, 12.0);

        assertEquals("Plastic", toy.getMaterial());
    }

    @Test
    void testToysIsInteractive() {
        Toys toy1 = new Toys(4, "Cat Laser", "PetSmart", "Cat", 9.99, 30, "Metal", true, 5.0);
        Toys toy2 = new Toys(5, "Mouse Plush", "SoftToys", "Cat", 4.99, 50, "Fabric", false, 7.0);

        assertTrue(toy1.isInteractive());
        assertFalse(toy2.isInteractive());
    }

    @Test
    void testToysSize() {
        Toys toy = new Toys(6, "Squeaky Toy", "PlayPaws", "Dog", 6.49, 25, "Rubber", true, 6.0);

        assertEquals(6.0, toy.getSize());
    }
}
