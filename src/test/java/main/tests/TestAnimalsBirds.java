package main.tests;
import main.domain.Animals;
import main.domain.Birds;
import main.domain.Birds.BirdType;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestAnimalsBirds {

	Birds bird = new Birds(3, "Parrot", "Male", 12, 1.5, "Omnivore", 500, true, "Green", true, BirdType.PARROT);

    assertEquals(3, bird.getId());
    assertEquals("Parrot", bird.getName());
    assertEquals("Male", bird.getGender());
    assertEquals(12, bird.getAge());
    assertEquals(1.5, bird.getWeight(), 0.01);
    assertEquals("Omnivore", bird.getDiet());
    assertEquals(500, bird.getPrice(), 0.01);
    assertTrue(bird.isFed());
    assertEquals("Green", bird.getFeatherColor());
    assertTrue(bird.getTalks());
    assertEquals(BirdType.PARROT, bird.getBirdType());
}

@Test
void testBirdSetters() {
    Birds bird = new Birds(4, "Canary", "Female", 10, 0.2, "Omnivore", 200, false, "Yellow", false, BirdType.CANARY);

    bird.setFeatherColor("Red");
    bird.setTalks(true);
    bird.setBirdType(BirdType.COCKATOO);

    assertEquals("Red", bird.getFeatherColor());
    assertTrue(bird.getTalks());
    assertEquals(BirdType.COCKATOO, bird.getBirdType());
}


@Test
void testBirdToString() {
    Birds bird = new Birds(5, "Cockatoo", "Female", 24, 2.0, "Omnivore", 800, true, "White", true, BirdType.COCKATOO);
    String expected = "Animals { ID: 5, Name: Cockatoo, of type: , Gender: Female, Age: 24, Weights: 2.0kg, Diet is: Omnivore, Price: 800.0 RON, Was fed today: true }, Feather Color: White, Talks: true, Bird Type: COCKATOO";
    assertEquals(expected, bird.toString());
}
}