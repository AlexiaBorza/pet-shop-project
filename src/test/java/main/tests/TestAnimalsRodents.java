package main.tests;

import main.domain.Rodents;
import main.domain.Rodents.RodentType;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestAnimalsRodents {

    @Test
    void testConstructorAndGetters() {
        Rodents rodent = new Rodents(
            1, "Bunny", "Female", 12, 2.5, "Herbivore", 100.0, true, "White", 30.0, RodentType.RABBIT
        );

        assertEquals(1, rodent.getId());
        assertEquals("Bunny", rodent.getName());
        assertEquals("Female", rodent.getGender());
        assertEquals(12, rodent.getAge());
        assertEquals(2.5, rodent.getWeight());
        assertEquals("Herbivore", rodent.getDiet());
        assertEquals(100.0, rodent.getPrice());
        assertTrue(rodent.isFed());
        assertEquals("White", rodent.getFurColor());
        assertEquals(30.0, rodent.getSize());
        assertEquals(RodentType.RABBIT, rodent.getRodentType());
    }

    @Test
    void testSetters() {
        Rodents rodent = new Rodents(
            2, "Hammy", "Male", 8, 0.8, "Omnivore", 80.0, false, "Brown", 20.0, RodentType.HAMSTER
        );

        rodent.setFurColor("Grey");
        rodent.setSize(22.0);
        rodent.setRodentType(RodentType.RABBIT);

        assertEquals("Grey", rodent.getFurColor());
        assertEquals(22.0, rodent.getSize());
        assertEquals(RodentType.RABBIT, rodent.getRodentType());
    }

    @Test
    void testToString() {
        Rodents rodent = new Rodents(
            1, "Bunny", "Female", 12, 2.5, "Herbivore", 100.0, true, "White", 30.0, RodentType.RABBIT
        );

        String expected = "Animals { ID: 1, Name: Bunny, of type: , Gender: Female, Age: 12, Weights: 2.5kg, Diet is: Herbivore, Price: 100.0 RON, Was fed today: true }" +
                          ", Fur Color: White, Size: 30.0, Rodent Type: RABBIT";
        assertEquals(expected, rodent.toString());
    }
}
