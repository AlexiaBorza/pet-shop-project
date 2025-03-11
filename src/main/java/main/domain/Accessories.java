package main.domain;

/**
 * Clasa derivată din Items care reprezintă articolele de tip accesorii pentru animale (haine, hamuri, lese, sisteme/decoratiuni acvarii si terarii).
 */
public class Accessories extends Items{
	private String color;
    private String type;
    private double size;

    /**
     * Constructor implicit pentru un obiect Accessories.
     */
    public Accessories() {}

    
    /**
     * Constructor pentru inițializarea unui articol de tip accesoriu.
     *
     * @param color   Culoarea accesoriului
     * @param type    Tipul accesoriului
     */
    public Accessories(int id, String name, String brand, String forAnimal, double price, int stock, String color, String type, double size) {
        super(id, name, brand, forAnimal, price, stock);
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public double getSize() {
        return size;}

    public void setSize(double i) {
        size = i;}
    
    /**
     * Returnează o descriere a articolului de tip accesoriu sub formă de șir de caractere.
     *
     * @return Detaliile articolului de tip accesoriu
     */
    @Override
    public String toString() {
        return super.toString() + "\nColor: " + color + "\nType: " + type + "\nSize" + size;
    }
}

