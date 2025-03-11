package main.domain;

/**
 * Clasa Birds reprezintă diverse tipuri de pasari, precum papagali si perusi, și extinde clasa Animals.
 */
public class Birds extends Animals {
    private String featherColor; 
    private boolean talks; 
    private BirdType birdType; 

    /**
     * Enum pentru specificarea tipurilor de pasari.
     */
    public enum BirdType {
        PARROT, PARAKEET, CANARY, COCKATOO
    }


    /**
     * Constructor pentru inițializarea unui obiect Birds.
     *
     * @param featherColor    Culoarea penajului
     * @param talks           Indică dacă papagalul poate vorbi
     */
    public Birds(int id, String name, String gender, int age, double weight, String diet, double price, boolean fed, String featherColor, boolean talks, BirdType birdType) {
        super(id, name, gender, age, weight, diet, price, fed);
        this.featherColor = featherColor;
        this.talks = talks;
        this.birdType = birdType;
    }

    public String getFeatherColor() {
        return featherColor;
    }

    public void setFeatherColor(String featherColor) {
        this.featherColor = featherColor;
    }

    public boolean getTalks() {
        return talks;
    }

    public void setTalks(boolean talks) {
        this.talks = talks;
    }

    public BirdType getBirdType() {
        return birdType;
    }

    public void setBirdType(BirdType birdType) {
    	if (birdType == null) {
            throw new IllegalArgumentException("Bird type cannot be null");
        }
        this.birdType = birdType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Feather Color: " + featherColor + ", Talks: " + talks + ", Bird Type: " + birdType;
    }
}
