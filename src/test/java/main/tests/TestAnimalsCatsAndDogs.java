package main.tests;

import main.domain.Canine_and_Feline;
import main.domain.Canine_and_Feline.Species;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestAnimalsCatsAndDogs {

    @Test
    void testConstructorAndGetters() {
        Canine_and_Feline animal = new Canine_and_Feline(
            1, "Buddy", "Male", 24, 30.0, "Omnivore", 500.0, true,
            "Brown", true, Species.DOG, "Golden Retriever"
        );

        assertEquals(1, animal.getId());
        assertEquals("Buddy", animal.getName());
        assertEquals("Male", animal.getGender());
        assertEquals(24, animal.getAge());
        assertEquals(30.0, animal.getWeight());
        assertEquals("Omnivore", animal.getDiet());
        assertEquals(500.0, animal.getPrice());
        assertTrue(animal.isFed());
        assertEquals("Brown", animal.getFurColor());
        assertTrue(animal.isTrained());
        assertEquals(Species.DOG, animal.getSp());
        assertEquals("Golden Retriever", animal.getBreed());
    }

    @Test
    void testSetters() {
        Canine_and_Feline animal = new Canine_and_Feline();
        animal.setId(2);
        animal.setName("Mittens");
        animal.setGender("Female");
        animal.setAge(36);
        animal.setWeight(10.0);
        animal.setDiet("Carnivore");
        animal.setPrice(300.0);
        animal.setFed(false);
        animal.setFurColor("Black");
        animal.setTrained(false);
        animal.setSp(Species.CAT);
        animal.setBreed("Persian");

        assertEquals(2, animal.getId());
        assertEquals("Mittens", animal.getName());
        assertEquals("Female", animal.getGender());
        assertEquals(36, animal.getAge());
        assertEquals(10.0, animal.getWeight());
        assertEquals("Carnivore", animal.getDiet());
        assertEquals(300.0, animal.getPrice());
        assertFalse(animal.isFed());
        assertEquals("Black", animal.getFurColor());
        assertFalse(animal.isTrained());
        assertEquals(Species.CAT, animal.getSp());
        assertEquals("Persian", animal.getBreed());
    }

    @Test
    void testToString() {
        Canine_and_Feline animal = new Canine_and_Feline(
            1, "Buddy", "Male", 24, 30.0, "Omnivore", 500.0, true,
            "Brown", true, Species.DOG, "Golden Retriever"
        );

        String expected = "Animals { ID: 1, Name: Buddy, of type: , Gender: Male, Age: 24, Weights: 30.0kg, Diet is: Omnivore, Price: 500.0 RON, Was fed today: true }" +
                          ", Species: DOG, Fur Color: Brown, Breed: Golden Retriever, Is Trained: true";
        assertEquals(expected, animal.toString());
    }
}
