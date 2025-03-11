package main.domain;

/**
 * Clasa Rodent reprezintă * Clasa Rodents reprezintă diverse tipuri de rozătoare, cum ar fi iepurii și hamsterii, și extinde clasa Animals.
 */
public class Rodents extends Animals {
    private String furColor; 
    private double size; 
    private RodentType rodentType;
    
    /**
     * Enum pentru specificarea tipurilor de rozătoare.
     */
    public enum RodentType {
        RABBIT, HAMSTER
    }

    /**
     * Constructor pentru inițializarea unui obiect.
     *
     * @param furColor  Culoarea blanii
     * @param size Dimensiunea animalului
     * @param rodentType Tipul de rozatoare
     */
    
    
    public Rodents(int id, String name, String gender, int age, double weight, String diet, double price, boolean fed, String furColor, double size, RodentType rodentType) {
    	super(id, name, gender, age, weight, diet, price, fed);
    	this.furColor = furColor;
    	this.size = size;
    	this.rodentType = rodentType;
    	//super.setSpecies(rodentType.toString());  // Setez specia in functie de tipul de rozatoare
    }

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public RodentType getRodentType() {
        return rodentType;
    }

    public void setRodentType(RodentType rodentType) {
        this.rodentType = rodentType;
        //super.setSpecies(rodentType.toString()); // Actualizez atributul species in Animals
    }

    @Override
    public String toString() {
        return super.toString() + ", Fur Color: " + furColor + ", Size: " + size + ", Rodent Type: " + rodentType;
    }
}
