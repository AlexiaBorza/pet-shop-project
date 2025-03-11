package main.tests;

import main.domain.Enclosure;
import main.domain.Canine_and_Feline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEnclosures {

    private Enclosure enclosure;
    private Canine_and_Feline dog;

    @BeforeEach
    void setUp() {
        // Creating an Enclosure of type CAGE with id 1, capacity 5, and temperature 22.0°C
        enclosure = new Enclosure(1, Enclosure.EnclosureType.CAGE, 5, 22.0);

        // Creating a dog object to add to the enclosure
        dog = new Canine_and_Feline(1, "Buddy", "Male", 3, 10.5, "Dog Food", 100.0, true, "Brown", true, Canine_and_Feline.Species.DOG, "Golden Retriever");
    }

    @Test
    void testConstructorAndGetters() {
        // Testing the constructor and getters
        assertEquals(1, enclosure.getId());  // Test for the id
        assertEquals(Enclosure.EnclosureType.CAGE, enclosure.getType());
        assertTrue(enclosure.isClean());
        assertFalse(enclosure.isFull());
        assertEquals(5, enclosure.getCapacity());
        assertEquals(22.0, enclosure.getTemperature());
    }

    @Test
    void testSetters() {
        // Testing the setters
        enclosure.setClean(false);
        enclosure.setFull(true);
        enclosure.setCapacity(10);
        enclosure.setTemperature(25.0);

        assertFalse(enclosure.isClean());
        assertTrue(enclosure.isFull());
        assertEquals(10, enclosure.getCapacity());
        assertEquals(25.0, enclosure.getTemperature());
    }

    @Test
    void testAddAnimalToEnclosure() {
        // Adding a dog to the enclosure
        enclosure.getAnimals().add(dog);

        assertEquals(1, enclosure.getAnimals().size());
        assertEquals("Buddy", enclosure.getAnimals().get(0).getName());
    }

    @Test
    void testToString() {
        // Checking the string representation of an empty enclosure
        String expectedString = "Enclosure Details:\n" +
                                "ID: 1\n" +
                                "Type: CAGE\n" +
                                "Is Clean: true\n" +
                                "Is Full: false\n" +
                                "Capacity: 5\n" +
                                "Temperature: 22.0°C\n" +
                                "Animals:\n" +
                                "No animals in the enclosure.\n";
        assertEquals(expectedString, enclosure.toString());

        // Adding the dog to the enclosure and checking the updated string representation
        enclosure.getAnimals().add(dog);
        expectedString = "Enclosure Details:\n" +
                         "ID: 1\n" +
                         "Type: CAGE\n" +
                         "Is Clean: true\n" +
                         "Is Full: false\n" +
                         "Capacity: 5\n" +
                         "Temperature: 22.0°C\n" +
                         "Animals:\n" +
                         "- Buddy ()\n";  // Assuming the `Canine_and_Feline` class has a `getName()` method correctly implemented
        assertEquals(expectedString, enclosure.toString());
    }
}
