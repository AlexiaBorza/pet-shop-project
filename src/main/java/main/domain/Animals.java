package main.domain;

/**
 * Clasa Animals reprezintă un animal cu diverse atribute, cum ar fi ID-ul, numele, tipul, specia/rasa, genul, vârsta, greutatea, dieta, prețul în lei și statusul hrănirii.
 * Această clasă oferă metode getter și setter pentru accesarea și modificarea atributelor clasei.
 */

public class Animals {
	int id, age; 
	public String name, diet, gender; 
	double price, weight; 
	boolean fed; 
	
	
	/**
     * Constructor implicit pentru clasa Animals.
     */
	public Animals() {}
	
	/**
     * Constructor cu parametri pentru clasa Animals.
     *
     * @param id ID-ul unic al animalului
     * @param name Numele animalului
     * @param gender Genul animalului (masculin/feminin)
     * @param age Vârsta animalului în luni
     * @param weight Greutatea animalului în kilograme
     * @param diet Dieta animalului (omnivor, ierbivor, carnivor)
     * @param price Prețul animalului în RON
     * @param fed Statusul hrănirii animalului (true dacă a fost hrănit azi, false altfel)
     */
    public Animals(int id, String n, String gen, int a, double w, String d, double p, boolean f) {
        this.id = id;
        name = n;
        gender = gen;
        age = a;
        weight = w;
        diet = d;
        price = p;
        fed = f;
    }
	
   
	 public int getId() {
	        return id;}
	 
	    public void setId(int id) {
	        this.id = id;}
	    
	    public int getAge() {
	        return age;}
	    
	    public void setAge(int age) {
	        this.age = age;}
	    
	    public String getName() {
	        return name;}
	    
	    public void setName(String name) {
	        this.name = name;}
	    
	    public String getDiet() {
	        return diet;}
	    
	    public void setDiet(String diet) {
	        this.diet = diet;}
	    
	    public String getGender() {
	        return gender;}
	    
	    public void setGender(String gen) {
	        gender = gen;}
	    
	    public double getPrice() {
	        return price;}
	    
	    public void setPrice(double price) {
	        this.price = price;}
	    
	    public double getWeight() {
	        return weight;}
	    
	    public void setWeight(double w) {
	        weight = w;}

	    public boolean isFed() {
	        return fed;}
	    
	    public void setFed(boolean f) {
	        fed = f;}
	    
	    /**
	     * Returnează o descriere a obiectului Animals sub formă de șir de caractere.
	     *
	     * @return O descriere text a animalului
	     */														
	    @Override
	    public String toString() {
	        return "Animals { ID: " + id + ", Name: " + name + ", of type: " + ", Gender: " + gender +
	            ", Age: " + age + ", Weights: " + weight + "kg, Diet is: " + diet + 
	            ", Price: " + price + " RON, Was fed today: " + fed + " }";
	    }	

}
