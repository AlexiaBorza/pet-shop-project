package main.tests;

import main.domain.Fish;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestAnimalsFish {

    @Test
    void testConstructorAndGetters() {
        Fish fish = new Fish(
            1, "Goldie", "Female", 12, 0.5, "Omnivore", 50.0, true, "Gold", "Goldfish"
        );

        assertEquals(1, fish.getId());
        assertEquals("Goldie", fish.getName());
        assertEquals("Female", fish.getGender());
        assertEquals(12, fish.getAge());
        assertEquals(0.5, fish.getWeight());
        assertEquals("Omnivore", fish.getDiet());
        assertEquals(50.0, fish.getPrice());
        assertTrue(fish.isFed());
        assertEquals("Gold", fish.getColor());
        assertEquals("Goldfish", fish.getFishBreed());
    }

    @Test
    void testSetters() {
        Fish fish = new Fish();
        fish.setId(2);
        fish.setName("Bubbles");
        fish.setGender("Male");
        fish.setAge(6);
        fish.setWeight(0.3);
        fish.setDiet("Carnivore");
        fish.setPrice(40.0);
        fish.setFed(false);
        fish.setColor("Blue");
        fish.setFishBreed("Betta");

        assertEquals(2, fish.getId());
        assertEquals("Bubbles", fish.getName());
        assertEquals("Male", fish.getGender());
        assertEquals(6, fish.getAge());
        assertEquals(0.3, fish.getWeight());
        assertEquals("Carnivore", fish.getDiet());
        assertEquals(40.0, fish.getPrice());
        assertFalse(fish.isFed());
        assertEquals("Blue", fish.getColor());
        assertEquals("Betta", fish.getFishBreed());
    }

    @Test
    void testToString() {
        Fish fish = new Fish(
            1, "Goldie", "Female", 12, 0.5, "Omnivore", 50.0, true, "Gold", "Goldfish"
        );

        String expected = "Animals { ID: 1, Name: Goldie, of type: , Gender: Female, Age: 12, Weights: 0.5kg, Diet is: Omnivore, Price: 50.0 RON, Was fed today: true }" +
                          ", Color: Gold, Fish Breed: Goldfish";
        assertEquals(expected, fish.toString());
    }
}
