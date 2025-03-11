package main.domain;

/**
 * Clasa reptiles reprezintă reptile precum țestoase și dragoni bărboși, extinde clasa Animals.
 */
public class Reptiles extends Animals {
    private double shellSize;
    private int tailLength;
    private ReptileType reptileType;

    /**
     * Enum pentru specificarea tipurilor de reptile.
     */
    public enum ReptileType {
        TURTLE, BEARDED_DRAGON
    }

    /**
     * Constructor pentru inițializarea unui obiect Reptiles.
     *
     * @param shellSize   Dimensiunea carapacei
     * @param tailLength  Lungimea cozii
     * @param reptileType Tipul de reptilă
     */
    public Reptiles(int id, String name, String gender, int age, double weight, String diet, double price, boolean fed, double shellSize, int tailLength, ReptileType reptileType) {
        super(id, name, gender, age, weight, diet, price, fed);
        this.shellSize = shellSize;
        this.tailLength = tailLength;
        this.reptileType = reptileType;
    }

    public double getShellSize() {
        return shellSize;
    }

    public void setShellSize(double shellSize) {
        this.shellSize = shellSize;
    }

    public int getTailLength() {
        return tailLength;
    }

    public void setTailLength(int tailLength) {
        this.tailLength = tailLength;
    }

    public ReptileType getReptileType() {
        return reptileType;
    }

    public void setReptileType(ReptileType reptileType) {
        this.reptileType = reptileType;
        //super.setSpecies(reptileType.toString()); // Update species in superclass
    }

    @Override
    public String toString() {
        return super.toString() + ", Shell Size: " + shellSize + " cm, Tail Length: " + tailLength + " cm, Reptile Type: " + reptileType;
    }
}
