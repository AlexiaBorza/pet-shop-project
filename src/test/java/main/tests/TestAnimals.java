package main.tests;
import main.domain.Animals;
import main.repository.Repo_Animals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAnimals {

	private Repo_Animals repo;

    @BeforeEach
    void setUp() {
        repo = new Repo_Animals();
    }
    @Test
    void testAnimalConstructorAndGetters() {
        Animals animal = new Animals(1, "Lion", "Male", 36, 200.5, "Carnivore", 10000, true);

        assertEquals(1, animal.getId());
        assertEquals("Lion", animal.getName());
        assertEquals("Male", animal.getGender());
        assertEquals(36, animal.getAge());
        assertEquals(200.5, animal.getWeight(), 0.01);
        assertEquals("Carnivore", animal.getDiet());
        assertEquals(10000, animal.getPrice(), 0.01);
        assertTrue(animal.isFed());
    }

    @Test
    void testAnimalSetters() {
        Animals animal = new Animals();
        animal.setId(2);
        animal.setName("Elephant");
        animal.setGender("Female");
        animal.setAge(60);
        animal.setWeight(5000.0);
        animal.setDiet("Herbivore");
        animal.setPrice(20000);
        animal.setFed(false);

        assertEquals(2, animal.getId());
        assertEquals("Elephant", animal.getName());
        assertEquals("Female", animal.getGender());
        assertEquals(60, animal.getAge());
        assertEquals(5000.0, animal.getWeight(), 0.01);
        assertEquals("Herbivore", animal.getDiet());
        assertEquals(20000, animal.getPrice(), 0.01);
        assertFalse(animal.isFed());
    }
    
    @Test
    void testAnimalToString() {
        Animals animal = new Animals(1, "Tiger", "Male", 48, 250.3, "Carnivore", 15000, true);
        String expected = "Animals { ID: 1, Name: Tiger, of type: , Gender: Male, Age: 48, Weights: 250.3kg, Diet is: Carnivore, Price: 15000.0 RON, Was fed today: true }";
        assertEquals(expected, animal.toString());
    }
        
        
        @Test
        void testCreateAnimal() {
            Animals animal = new Animals(1, "Lion", "Male", 36, 200.5, "Carnivore", 10000, true);
            repo.create(animal);

            List<Animals> animalsList = repo.getAllAnimals();
            assertEquals(1, animalsList.size());
            assertEquals(animal, animalsList.get(0));
        }

        @Test
        void testReadAllAnimals() {
            Animals animal1 = new Animals(1, "Tiger", "Male", 48, 250.0, "Carnivore", 15000, true);
            Animals animal2 = new Animals(2, "Elephant", "Female", 60, 5000.0, "Herbivore", 20000, false);

            repo.create(animal1);
            repo.create(animal2);

            List<Animals> animalsList = repo.getAllAnimals();
            assertEquals(2, animalsList.size());
            assertTrue(animalsList.contains(animal1));
            assertTrue(animalsList.contains(animal2));
        }

        @Test
        void testReadAnimalById() {
            Animals animal = new Animals(1, "Lion", "Male", 36, 200.5, "Carnivore", 10000, true);
            repo.create(animal);

            Animals foundAnimal = repo.readById(1);
            assertNotNull(foundAnimal);
            assertEquals(animal, foundAnimal);

            Animals notFoundAnimal = repo.readById(2);
            assertNull(notFoundAnimal);
        }

        @Test
        void testUpdateAnimal() {
            Animals animal = new Animals(1, "Lion", "Male", 36, 200.5, "Carnivore", 10000, true);
            repo.create(animal);

            Animals updatedAnimal = new Animals(1, "Updated Lion", "Male", 40, 210.0, "Carnivore", 11000, false);
            boolean updateResult = repo.updateAnimal(1, updatedAnimal);

            assertTrue(updateResult);
            Animals foundAnimal = repo.readById(1);
            assertNotNull(foundAnimal);
            assertEquals("Updated Lion", foundAnimal.getName());
            assertEquals(40, foundAnimal.getAge());
            assertFalse(foundAnimal.isFed());
        }

        @Test
        void testUpdateNonExistentAnimal() {
            Animals updatedAnimal = new Animals(1, "Updated Lion", "Male", 40, 210.0, "Carnivore", 11000, false);
            boolean updateResult = repo.updateAnimal(1, updatedAnimal);

            assertFalse(updateResult);
        }

        @Test
        void testDeleteAnimal() {
            Animals animal = new Animals(1, "Lion", "Male", 36, 200.5, "Carnivore", 10000, true);
            repo.create(animal);

            boolean deleteResult = repo.delete(1);
            assertTrue(deleteResult);

            List<Animals> animalsList = repo.getAllAnimals();
            assertTrue(animalsList.isEmpty());
        }

        @Test
        void testDeleteNonExistentAnimal() {
            boolean deleteResult = repo.delete(1);
            assertFalse(deleteResult);
        }
   
    }
