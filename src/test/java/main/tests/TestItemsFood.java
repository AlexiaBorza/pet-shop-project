package main.tests;

import main.domain.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestItemsFood {

    private Food food;

    @BeforeEach
    void setUp() {
        // Initializing a food item (e.g., pet food)
        food = new Food(3, "Dog Food", "BrandX", "Dog", 20.0, 50, "High Protein", "2026-12-31", 
                        Arrays.asList("Chicken", "Rice", "Carrot"));
    }

    @Test
    void testFoodConstructorAndGetters() {
        assertEquals(3, food.getId());
        assertEquals("Dog Food", food.getName());
        assertEquals("BrandX", food.getBrand());
        assertEquals("Dog", food.forAnimal());
        assertEquals(20.0, food.getPrice());
        assertEquals(50, food.getStock());
        assertEquals("High Protein", food.getDiet());
        assertEquals("2026-12-31", food.getExpiration_date());
        assertEquals(Arrays.asList("Chicken", "Rice", "Carrot"), food.getIngredients());
    }

    @Test
    void testFoodSetters() {
        food.setDiet("Grain-Free");
        food.setExpiration_date("2027-01-01");
        food.setIngredients(Arrays.asList("Beef", "Potatoes"));

        assertEquals("Grain-Free", food.getDiet());
        assertEquals("2027-01-01", food.getExpiration_date());
        assertEquals(Arrays.asList("Beef", "Potatoes"), food.getIngredients());
    }

    @Test
    void testFoodToString() {
        String expectedString = "ID: 3\n" +
                "Name: Dog Food\n" +
                "Brand: BrandX\n" +
                "Animal Destined To: Dog\n" +
                "Price: $20.0\n" +
                "Stock Quantity: 50\n" +
                "Diet Type: High Protein\n" +
                "Expires at: 2026-12-31\n" +
                "Ingredients: Chicken, Rice, Carrot";
        assertEquals(expectedString, food.toString());
    }
}
