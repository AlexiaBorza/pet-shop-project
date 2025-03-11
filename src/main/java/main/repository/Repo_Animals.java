package main.repository;

import main.domain.Animals;
import main.domain.Birds;
import main.domain.Canine_and_Feline;
import main.domain.Fish;
import main.domain.Reptiles;
import main.domain.Rodents;
import main.domain.Canine_and_Feline.Species;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repo_Animals offers CRUD functionality for various animal types using database tables.
 */
public class Repo_Animals {

    private static final String DB_URL = "jdbc:mysql://192.168.56.1:3306/petshop";
    private static final String DB_USER = "monty";
    private static final String DB_PASSWORD = "some_pass";

    // Read: Get all animals of a specific type
    public List<Animals> getAllAnimals(String tableName) {
        List<Animals> animalsList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                switch (tableName.toLowerCase()) {
                    case "birds":
                        animalsList.add(new Birds(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getString("featherColor"),
                            rs.getBoolean("talks"),
                            Birds.BirdType.valueOf(rs.getString("birdType"))
                        ));
                        break;
                        
                    case "fish":
                        animalsList.add(new Fish(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getString("color"),
                            rs.getString("fish_breed")
                        ));
                        break;

                    case "reptiles":
                        animalsList.add(new Reptiles(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getDouble("shell_size"),
                            rs.getInt("tail_length"),
                            Reptiles.ReptileType.valueOf(rs.getString("reptile_type"))
                        ));
                        break;

                    case "rodents":
                        animalsList.add(new Rodents(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getString("fur_color"),
                            rs.getDouble("size"),
                            Rodents.RodentType.valueOf(rs.getString("rodent_type"))
                        ));
                        break;
                       
                        
                    case "canineandfeline":
                        animalsList.add(new Canine_and_Feline(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getString("fur_color"),
                            rs.getBoolean("is_trained"), 
                            Canine_and_Feline.Species.valueOf(rs.getString("species")), 
                            rs.getString("breed")
                        ));
                        break;

                       
                    default:
                        throw new IllegalArgumentException("Unknown table: " + tableName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animalsList;
    }

    
    public Animals readById(int id, String tableName) {
        String query = "";
        Animals animal = null;

        switch (tableName.toLowerCase()) {
            case "birds":
                query = "SELECT * FROM Birds WHERE id = ?";
                break;
            case "canineandfeline":
                query = "SELECT * FROM CanineAndFeline WHERE id = ?";
                break;
            case "fish":
                query = "SELECT * FROM Fish WHERE id = ?";
                break;
            case "reptiles":
                query = "SELECT * FROM Reptiles WHERE id = ?";
                break;
            case "rodents":
                query = "SELECT * FROM Rodents WHERE id = ?";
                break;
            default:
                return null;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    if (tableName.equalsIgnoreCase("birds")) {
                        animal = new Birds(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getString("featherColor"),
                            rs.getBoolean("talks"),
                            Birds.BirdType.valueOf(rs.getString("birdType"))
                        );
                    }

                    else if (tableName.equalsIgnoreCase("canineandfeline")) {
                        animal = new Canine_and_Feline(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getString("fur_color"),
                            rs.getBoolean("is_trained"),
                            Canine_and_Feline.Species.valueOf(rs.getString("species")),
                            rs.getString("breed")
                        );
                    }

                    else if (tableName.equalsIgnoreCase("fish")) {
                        animal = new Fish(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getString("color"),
                            rs.getString("fish_breed")
                        );
                    }

                    else if (tableName.equalsIgnoreCase("reptiles")) {
                        animal = new Reptiles(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getDouble("shell_size"),
                            rs.getInt("tail_length"),
                            Reptiles.ReptileType.valueOf(rs.getString("reptile_type"))
                        );
                    }

                    else if (tableName.equalsIgnoreCase("rodents")) {
                        animal = new Rodents(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getInt("age"),
                            rs.getDouble("weight"),
                            rs.getString("diet"),
                            rs.getDouble("price"),
                            rs.getBoolean("fed"),
                            rs.getString("fur_color"),
                            rs.getDouble("size"),
                            Rodents.RodentType.valueOf(rs.getString("RodentType"))
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return animal; 
    }


    public void create(Animals animal, String tableName) {
        String query;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            switch (tableName) {
                case "birds":
                    query = "INSERT INTO birds (name, gender, age, weight, diet, price, fed, featherColor, talks, birdType) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Birds bird = (Birds) animal;
                        pstmt.setString(1, bird.getName());
                        pstmt.setString(2, bird.getGender());
                        pstmt.setInt(3, bird.getAge());
                        pstmt.setDouble(4, bird.getWeight());
                        pstmt.setString(5, bird.getDiet());
                        pstmt.setDouble(6, bird.getPrice());
                        pstmt.setBoolean(7, bird.isFed());
                        pstmt.setString(8, bird.getFeatherColor());
                        pstmt.setBoolean(9, bird.getTalks());
                        pstmt.setString(10, bird.getBirdType().toString());
                        pstmt.executeUpdate();
                    }
                    break;
                    
                case "canineandfeline":
                    query = "INSERT INTO CanineAndFeline (name, gender, age, weight, diet, price, fed, fur_color, is_trained, species, breed) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Canine_and_Feline animalCF = (Canine_and_Feline) animal;
                        pstmt.setString(1, animalCF.getName());
                        pstmt.setString(2, animalCF.getGender());
                        pstmt.setInt(3, animalCF.getAge());
                        pstmt.setDouble(4, animalCF.getWeight());
                        pstmt.setString(5, animalCF.getDiet());
                        pstmt.setDouble(6, animalCF.getPrice());
                        pstmt.setBoolean(7, animalCF.isFed());
                        pstmt.setString(8, animalCF.getFurColor());
                        pstmt.setBoolean(9, animalCF.isTrained());
                        pstmt.setString(10, animalCF.getSp().toString());
                        pstmt.setString(11, animalCF.getBreed());
                        pstmt.executeUpdate();
                    }
                    break;

                case "fish":
                    query = "INSERT INTO Fish (name, gender, age, weight, diet, price, fed, color, fish_breed) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Fish fish = (Fish) animal;
                        pstmt.setString(1, fish.getName());
                        pstmt.setString(2, fish.getGender());
                        pstmt.setInt(3, fish.getAge());
                        pstmt.setDouble(4, fish.getWeight());
                        pstmt.setString(5, fish.getDiet());
                        pstmt.setDouble(6, fish.getPrice());
                        pstmt.setBoolean(7, fish.isFed());
                        pstmt.setString(8, fish.getColor());
                        pstmt.setString(9, fish.getFishBreed());
                        pstmt.executeUpdate();
                    }
                    break;

                case "reptiles":
                    query = "INSERT INTO Reptiles (name, gender, age, weight, diet, price, fed, shell_size, tail_length, reptile_type) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Reptiles reptile = (Reptiles) animal;
                        pstmt.setString(1, reptile.getName());
                        pstmt.setString(2, reptile.getGender());
                        pstmt.setInt(3, reptile.getAge());
                        pstmt.setDouble(4, reptile.getWeight());
                        pstmt.setString(5, reptile.getDiet());
                        pstmt.setDouble(6, reptile.getPrice());
                        pstmt.setBoolean(7, reptile.isFed());
                        pstmt.setDouble(8, reptile.getShellSize());
                        pstmt.setInt(9, reptile.getTailLength());
                        pstmt.setString(10, reptile.getReptileType().toString());
                        pstmt.executeUpdate();
                    }
                    break;

                case "rodents":
                    query = "INSERT INTO Rodents (name, gender, age, weight, diet, price, fed, fur_color, size, rodent_type) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Rodents rodent = (Rodents) animal;
                        pstmt.setString(1, rodent.getName());
                        pstmt.setString(2, rodent.getGender());
                        pstmt.setInt(3, rodent.getAge());
                        pstmt.setDouble(4, rodent.getWeight());
                        pstmt.setString(5, rodent.getDiet());
                        pstmt.setDouble(6, rodent.getPrice());
                        pstmt.setBoolean(7, rodent.isFed());
                        pstmt.setString(8, rodent.getFurColor());
                        pstmt.setDouble(9, rodent.getSize());
                        pstmt.setString(10, rodent.getRodentType().toString());
                        pstmt.executeUpdate();
                    }
                    break;

                
                
                default:
                    throw new IllegalArgumentException("Unknown table: " + tableName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean updateAnimal(int id, Animals updatedAnimal, String tableName) {
        String query;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            switch (tableName) {
                case "birds":
                    query = "UPDATE birds SET name = ?, gender = ?, age = ?, weight = ?, diet = ?, price = ?, " +
                            "fed = ?, featherColor = ?, talks = ?, birdType = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Birds bird = (Birds) updatedAnimal;
                        pstmt.setString(1, bird.getName());
                        pstmt.setString(2, bird.getGender());
                        pstmt.setInt(3, bird.getAge());
                        pstmt.setDouble(4, bird.getWeight());
                        pstmt.setString(5, bird.getDiet());
                        pstmt.setDouble(6, bird.getPrice());
                        pstmt.setBoolean(7, bird.isFed());
                        pstmt.setString(8, bird.getFeatherColor());
                        pstmt.setBoolean(9, bird.getTalks());
                        pstmt.setString(10, bird.getBirdType().toString());
                        pstmt.setInt(11, id);
                        return pstmt.executeUpdate() > 0;
                    }
                case "canineandfeline":
                    query = "UPDATE CanineAndFeline SET name = ?, gender = ?, age = ?, weight = ?, diet = ?, price = ?, " +
                            "fed = ?, fur_color = ?, is_trained = ?, species = ?, breed = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Canine_and_Feline animalCF = (Canine_and_Feline) updatedAnimal;
                        pstmt.setString(1, animalCF.getName());
                        pstmt.setString(2, animalCF.getGender());
                        pstmt.setInt(3, animalCF.getAge());
                        pstmt.setDouble(4, animalCF.getWeight());
                        pstmt.setString(5, animalCF.getDiet());
                        pstmt.setDouble(6, animalCF.getPrice());
                        pstmt.setBoolean(7, animalCF.isFed());
                        pstmt.setString(8, animalCF.getFurColor());
                        pstmt.setBoolean(9, animalCF.isTrained());
                        pstmt.setString(10, animalCF.getSp().toString());
                        pstmt.setString(11, animalCF.getBreed());
                        pstmt.setInt(12, id);
                        return pstmt.executeUpdate() > 0;
                    }

                case "fish":
                    query = "UPDATE Fish SET name = ?, gender = ?, age = ?, weight = ?, diet = ?, price = ?, " +
                            "fed = ?, color = ?, fish_breed = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Fish fish = (Fish) updatedAnimal;
                        pstmt.setString(1, fish.getName());
                        pstmt.setString(2, fish.getGender());
                        pstmt.setInt(3, fish.getAge());
                        pstmt.setDouble(4, fish.getWeight());
                        pstmt.setString(5, fish.getDiet());
                        pstmt.setDouble(6, fish.getPrice());
                        pstmt.setBoolean(7, fish.isFed());
                        pstmt.setString(8, fish.getColor());
                        pstmt.setString(9, fish.getFishBreed());
                        pstmt.setInt(10, id);
                        return pstmt.executeUpdate() > 0;
                    }

                case "reptiles":
                    query = "UPDATE Reptiles SET name = ?, gender = ?, age = ?, weight = ?, diet = ?, price = ?, " +
                            "fed = ?, shell_size = ?, tail_length = ?, reptile_type = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Reptiles reptile = (Reptiles) updatedAnimal;
                        pstmt.setString(1, reptile.getName());
                        pstmt.setString(2, reptile.getGender());
                        pstmt.setInt(3, reptile.getAge());
                        pstmt.setDouble(4, reptile.getWeight());
                        pstmt.setString(5, reptile.getDiet());
                        pstmt.setDouble(6, reptile.getPrice());
                        pstmt.setBoolean(7, reptile.isFed());
                        pstmt.setDouble(8, reptile.getShellSize());
                        pstmt.setInt(9, reptile.getTailLength());
                        pstmt.setString(10, reptile.getReptileType().toString());
                        pstmt.setInt(11, id);
                        return pstmt.executeUpdate() > 0;
                    }

                case "rodents":
                    query = "UPDATE Rodents SET name = ?, gender = ?, age = ?, weight = ?, diet = ?, price = ?, " +
                            "fed = ?, fur_color = ?, size = ?, rodent_type = ? WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        Rodents rodent = (Rodents) updatedAnimal;
                        pstmt.setString(1, rodent.getName());
                        pstmt.setString(2, rodent.getGender());
                        pstmt.setInt(3, rodent.getAge());
                        pstmt.setDouble(4, rodent.getWeight());
                        pstmt.setString(5, rodent.getDiet());
                        pstmt.setDouble(6, rodent.getPrice());
                        pstmt.setBoolean(7, rodent.isFed());
                        pstmt.setString(8, rodent.getFurColor());
                        pstmt.setDouble(9, rodent.getSize());
                        pstmt.setString(10, rodent.getRodentType().toString()); // Convert RodentType enum to String
                        pstmt.setInt(11, id);
                        return pstmt.executeUpdate() > 0;
                    }

                default:
                    throw new IllegalArgumentException("Unknown table: " + tableName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id, String tableName) {
        String query = "DELETE FROM " + tableName + " WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
