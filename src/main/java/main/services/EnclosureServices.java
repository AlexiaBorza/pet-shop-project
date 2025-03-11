package main.services;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import main.domain.Animals;
import main.domain.Enclosure;

public class EnclosureServices {
    
    /**
     * Adds an animal to the enclosure and automatically updates the "isFull" status.
     */
	public void addAnimal(Enclosure enclosure, Animals animal) {
        // Add the animal to the enclosure's list of animals
        enclosure.getAnimals().add(animal);

        // Update the animals_list column (format: {animal1, animal2, ...})
        //updateAnimalsList(enclosure);

        // Check if the enclosure has reached its capacity
        if (enclosure.getAnimals().size() == enclosure.getCapacity()) {
            enclosure.setFull(true);
        }

        System.out.println("Animal " + animal.getName() + " added to the enclosure.");
    }

    /**
     * Removes an animal from the enclosure and updates the "animals_list" column in the database.
     */
    public void removeAnimal(Enclosure enclosure, Animals animal) {
        // Remove the animal from the enclosure's list of animals
        if (enclosure.getAnimals().remove(animal)) {
            // Update the animals_list column (format: {animal1, animal2, ...})
            //updateAnimalsList(enclosure);

            // Check if the enclosure is no longer full
            if (enclosure.getAnimals().size() < enclosure.getCapacity()) {
                enclosure.setFull(false);
            }

            System.out.println("Animal " + animal.getName() + " removed from the enclosure.");
        } else {
            System.out.println("Animal not found in the enclosure.");
        }
    }
    
    /**
     * Updates the "animals_list" column for a given enclosure in the database.
     * @param enclosureId The ID of the enclosure.
     * @param animalsList The formatted string of animal names (e.g., {Buddy, Max}).
     
    private void updateAnimalsListInDatabase(int enclosureId, String animalsList) {
        String sql = "UPDATE Enclosures SET animals_list = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set the parameters for the query
            stmt.setString(1, animalsList);  // animals_list value
            stmt.setInt(2, enclosureId);     // enclosureId value

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("Animals list updated successfully.");
            } else {
                System.out.println("No enclosure found with the given ID.");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            System.out.println("Error updating animals list.");
        }
    }

    /**
     * Updates the "animals_list" column for the given enclosure.
     * This is called after adding or removing an animal.
    
    private void updateAnimalsList(Enclosure enclosure) {
        List<String> animalNames = enclosure.getAnimals().stream()
            .map(Animals::getName)  // Get the name of each animal
            .collect(Collectors.toList());  // Collect the names into a list

        // Format the list of animal names into the string format {animal1, animal2, ...}
        String animalsString = "{" + String.join(", ", animalNames) + "}";

        // Assuming you have a method to update the database, you'll need to use
        // SQL to update the "animals_list" column in the database.
        updateAnimalsListInDatabase(enclosure.getId(), animalsString);
    }
    */
}
