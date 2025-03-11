package main.repository;

import main.domain.Enclosure;
import main.domain.Animals;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Repo_Enclosure gestionează operațiunile CRUD pentru adăposturi.
 */
public class Repo_Enclosure {
    private List<Enclosure> enclosures;
    private static final String FILE_NAME = "C:\\Users\\ALEXIA\\git\\p3-proiect-gr2-AndreeaBorza\\src\\main\\java\\main\\domain\\data\\Enclosures.txt";

    /**
     * Constructor care inițializează lista de adăposturi și încarcă datele din fișier.
     */
    public Repo_Enclosure() {
        this.enclosures = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Încarcă datele din fișierul specificat în lista de adăposturi.
     */
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    int id = Integer.parseInt(data[0]);
                    Enclosure.EnclosureType type = Enclosure.EnclosureType.valueOf(data[1]);
                    boolean isClean = Boolean.parseBoolean(data[2]);
                    boolean isFull = Boolean.parseBoolean(data[3]);
                    int capacity = Integer.parseInt(data[4]);
                    double temperature = Double.parseDouble(data[5]);

                    Enclosure enclosure = new Enclosure(id, type, capacity, temperature);
                    enclosure.setClean(isClean);
                    enclosure.setFull(isFull);

                    // Load animals if present
                    if (data.length > 6) {
                        for (int i = 6; i < data.length; i++) {
                            Animals animal = new Animals(); // Assuming Animals has a default constructor
                            animal.setName(data[i]); // Use a setter to assign the name
                            enclosure.getAnimals().add(animal);
                        }
                    }

                    enclosures.add(enclosure);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Salvează lista de adăposturi în fișierul specificat.
     */
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Enclosure e : enclosures) {
                StringBuilder line = new StringBuilder();
                line.append(e.getId()).append(",")
                        .append(e.getType()).append(",")
                        .append(e.isClean()).append(",")
                        .append(e.isFull()).append(",")
                        .append(e.getCapacity()).append(",")
                        .append(e.getTemperature());

                // Append animals
                for (Animals a : e.getAnimals()) {
                    line.append(",").append(a.getName()); // Assuming Animals has a getName() method
                }

                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Creează și adaugă un nou adăpost în listă.
     *
     * @param enclosure Adăpostul care urmează să fie adăugat
     */
    public void createEnclosure(Enclosure enclosure) {
        // Check if an enclosure with the same ID already exists
        for (Enclosure e : enclosures) {
            if (e.getId() == enclosure.getId()) {
                System.out.println("Enclosure with this ID already exists.");
                return;
            }
        }
        enclosures.add(enclosure);
        saveToFile();  // Save changes to file
    }

    /**
     * Citește toate adăposturile din listă.
     *
     * @return Lista tuturor adăposturilor
     */
    public List<Enclosure> readAllEnclosures() {
        return enclosures;
    }

    /**
     * Găsește un adăpost după ID.
     *
     * @param id ID-ul adăpostului căutat
     * @return Adăpostul găsit sau null dacă ID-ul nu este valid
     */
    public Enclosure readEnclosureById(int id) {
        for (Enclosure e : enclosures) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     * Actualizează un adăpost specific din listă.
     *
     * @param id ID-ul adăpostului care trebuie actualizat
     * @param updatedEnclosure Noul obiect cu valorile actualizate
     * @return true dacă actualizarea a fost reușită, false în caz contrar
     */
    public boolean updateEnclosure(int id, Enclosure updatedEnclosure) {
        for (int i = 0; i < enclosures.size(); i++) {
            if (enclosures.get(i).getId() == id) {
                enclosures.set(i, updatedEnclosure);
                saveToFile();  // Save changes to file
                return true;
            }
        }
        return false;
    }

    /**
     * Șterge un adăpost din listă după ID.
     *
     * @param id ID-ul adăpostului care trebuie șters
     * @return true dacă ștergerea a fost reușită, false în caz contrar
     */
    public boolean deleteEnclosure(int id) {
        for (int i = 0; i < enclosures.size(); i++) {
            if (enclosures.get(i).getId() == id) {
                enclosures.remove(i);
                saveToFile();  // Save changes to file
                return true;
            }
        }
        return false;
    }
}
