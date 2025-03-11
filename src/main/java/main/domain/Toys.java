package main.domain;

/**
 * Clasa derivată din Items care reprezintă articolele de tip jucării pentru animale.
 */
public class Toys extends Items{
	private String material; 
    private boolean isInteractive;
    double size;


    /**
     * Constructor implicit pentru un obiect Toys.
     */
    public Toys() {}

    
    /**
     * Constructor pentru inițializarea unui articol de tip jucărie.
     *
     * @param material      Materialul din care este fabricată jucăria
     * @param isInteractive Indică dacă jucăria este interactivă
     */
    public Toys(int id, String name, String brand, String forAnimal, double price, int stock, String material, boolean isInteractive, double size) {
        super(id, name, brand, forAnimal, price, stock);
        this.material = material;
        this.isInteractive = isInteractive;
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isInteractive() {
        return isInteractive;
    }

    public void setInteractive(boolean interactive) {
        isInteractive = interactive;
    }
    
    public double getSize() {
        return size;}

    public void setSize(double i) {
        size = i;}

    
    /**
     * Returnează o descriere a articolului de tip jucărie sub formă de șir de caractere.
     *
     * @return Detaliile articolului de tip jucărie
     */
    @Override
    public String toString() {
        return super.toString() + "\nMaterial: " + material + "\nInteractive: " + isInteractive + "\nSize" + size;
    }
}
