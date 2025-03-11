package main.domain;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Enclosure reprezintă habitatul in care animalele locuiesc, de tipul CAGE, TANK, TERRARIUM
 * Clasa are urmatoarele caracteristici: statusul de curățenie, capacitatea maximă, temperatura și lista animalelor aflate în adăpost.
 */


/**
 * Tipurile posibile de habitat.
 */
public class Enclosure {
    public enum EnclosureType {
        CAGE, TANK, TERRARIUM
    }

    private int id;  // ID-ul unic al adăpostului
    private EnclosureType type; 
    private boolean isClean;  // Statusul de curățenie al adăpostului
    private boolean isFull; // Nivelul de ocupare al adăpostului
    private int capacity; 
    private double temperature; 
    private List<Animals> animal; // Lista animalelor din adăpost

    /**
     * Constructor pentru inițializarea unui adăpost cu un anumit id, tip, capacitate și temperatură.
     * Implicit, adăpostul este curat și nu este complet ocupat.
     *
     * @param id ID-ul adăpostului
     * @param type Tipul adăpostului (CAGE, TANK, TERRARIUM)
     * @param capacity Capacitatea maximă a adăpostului
     * @param temperature Temperatura interioară a adăpostului
     */
    public Enclosure(int id, EnclosureType type, int capacity, double temperature) {
        this.id = id;
        this.type = type;
        this.isClean = true; 
        this.isFull = false; 
        this.capacity = capacity;
        this.temperature = temperature;
        this.animal = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnclosureType getType() {
        return type;
    }

    public void setType(EnclosureType type) {
        this.type = type;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        this.isClean = clean;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        this.isFull = full;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public List<Animals> getAnimals() {
        return animal;
    }

    /**
     * Returnează o descriere a adăpostului sub formă de șir de caractere.
     *
     * @return Detalii despre adăpost și animalele din acesta
     */
    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append("Enclosure Details:\n");
        details.append("ID: ").append(id).append("\n");
        details.append("Type: ").append(type).append("\n");
        details.append("Is Clean: ").append(isClean).append("\n");
        details.append("Is Full: ").append(isFull).append("\n");
        details.append("Capacity: ").append(capacity).append("\n");
        details.append("Temperature: ").append(temperature).append("°C\n");
        details.append("Animals:\n");

        if (animal.isEmpty()) {
            details.append("No animals in the enclosure.\n");
        } else {
            for (Animals a : animal) {
                details.append("- ").append(a.getName()).append("\n");
            }
        }
        return details.toString();
    }
    
    /**
     * Returns a string representation of all animals in the enclosure, formatted with curly braces.
     * If there are no animals, it returns "No animals in the enclosure".
     * 
     * @return A string representation of animals in the enclosure.
     */
    public String viewAnimals() {
        if (animal.isEmpty()) {
            return "No animals in the enclosure.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < animal.size(); i++) {
            sb.append(animal.get(i).getName());
            if (i < animal.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
