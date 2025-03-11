package main.domain;

/**
 * Clasa de bază pentru articolele care pot fi vândute.
 * Reprezintă articole cum ar fi jucării, accesorii, mâncare sau medicamente.
 */
public class Items {
	public int id; 
    public String name; 
    public String brand; 
    public String forAnimal;
    public double price; 
    public int stock; 
    
    
    /**
     * Constructor implicit pentru un obiect Items.
     */
    public Items() {}
    
    /**
     * Constructor pentru inițializarea unui articol cu atribute specifice.
     *
     * @param id ID-ul articolului
     * @param name Numele articolului
     * @param brand Brand-ul articolului
     * @param forAnimal Specia de animal pentru care este destinat articolul
     * @param price Prețul articolului
     * @param stock Cantitatea disponibilă în stoc
     */
    public Items(int id, String name, String brand, String forAnimal, double price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.forAnimal = forAnimal;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;}

    public void setId(int id) {
        this.id = id;}

    public String getName() {
        return name;}

    public void setName(String n) {
        name = n;}

    public String getBrand() {
        return brand;}

    public void setBrand(String b) {
        brand = b;}

    public String forAnimal() {
        return forAnimal;}

    public void setForAnimal(String a) {
        forAnimal = a;}

    public double getPrice() {
        return price;}

    public void setPrice(double p) {
        price = p;}

    public int getStock() {
        return stock;}

    public void setStockQuantity(int s) {
        stock = s;
        
    }
    /**
     *  Returnează o descriere cu detaliile articolului pentru istoricul de tranzactii
     *
     * @return A concise description of the item
     */
    public String getPurchaseDetails() {
        return name + " (Brand: " + brand + ", Price: $" + price + ")";
    }
    
    /**
     * Returnează o descriere a articolului sub formă de șir de caractere.
     *
     * @return Detaliile articolului
     */
    @Override
    public String toString() {
        return "Item Details:\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Brand: " + brand + "\n" +
                "Animal Destined To: " + forAnimal + "\n" +
                "Price: $" + price + "\n" +
                "Stock Quantity: " + stock;
}
}
