package main.tests;

import main.domain.Reptiles;
import main.domain.Reptiles.ReptileType;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestAnimalsReptiles {

    @Test
    void testConstructorAndGetters() {
        Reptiles reptile = new Reptiles(
            1, "Leonardo", "Male", 24, 10.5, "Omnivore", 150.0, true, 30.0, 15, ReptileType.TURTLE
        );

        assertEquals(1, reptile.getId());
        assertEquals("Leonardo", reptile.getName());
        assertEquals("Male", reptile.getGender());
        assertEquals(24, reptile.getAge());
        assertEquals(10.5, reptile.getWeight());
        assertEquals("Omnivore", reptile.getDiet());
        assertEquals(150.0, reptile.getPrice());
        assertTrue(reptile.isFed());
        assertEquals(30.0, reptile.getShellSize());
        assertEquals(15, reptile.getTailLength());
        assertEquals(ReptileType.TURTLE, reptile.getReptileType());
    }

    @Test
    void testSetters() {
        Reptiles reptile = new Reptiles(
            2, "Smaug", "Female", 36, 8.0, "Carnivore", 200.0, false, 0.0, 20, ReptileType.BEARDED_DRAGON
        );

        reptile.setShellSize(35.0);
        reptile.setTailLength(25);
        reptile.setReptileType(ReptileType.TURTLE);

        assertEquals(35.0, reptile.getShellSize());
        assertEquals(25, reptile.getTailLength());
        assertEquals(ReptileType.TURTLE, reptile.getReptileType());
    }

    @Test
    void testToString() {
        Reptiles reptile = new Reptiles(
            1, "Leonardo", "Male", 24, 10.5, "Omnivore", 150.0, true, 30.0, 15, ReptileType.TURTLE
        );

        String expected = "Animals { ID: 1, Name: Leonardo, of type: , Gender: Male, Age: 24, Weights: 10.5kg, Diet is: Omnivore, Price: 150.0 RON, Was fed today: true }" +
                          ", Shell Size: 30.0 cm, Tail Length: 15 cm, Reptile Type: TURTLE";
        assertEquals(expected, reptile.toString());
    }
}
