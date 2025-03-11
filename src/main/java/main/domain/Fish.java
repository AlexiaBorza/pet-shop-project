package main.domain;

/**
 * Clasa Fish reprezintă un pește și extinde clasa Animals.
 */
public class Fish extends Animals {
    private String color; 
    private String fishBreed;

    public Fish() {}
    /**
     * Constructor pentru inițializarea unui obiect Fish.
     *
     * @param color     Culoarea peștelui
     * @param fishBreed Specia de peste
     */
    public Fish(int id, String name, String gender, int age, double weight, String diet, double price, boolean fed, String color, String fishBreed) {
        super(id, name, gender, age, weight, diet, price, fed);
        this.color = color;
        this.fishBreed = fishBreed;
        //super.setSpecies("Fish"); // Setez specia sa fie mereu "Fish" pentru aceasta clasa
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    

    public String getFishBreed() {
		return fishBreed;
	}

	public void setFishBreed(String fishBreed) {
		this.fishBreed = fishBreed;
	}

	@Override
    public String toString() {
        return super.toString() + ", Color: " + color + ", Fish Breed: " + fishBreed;
    }
}
