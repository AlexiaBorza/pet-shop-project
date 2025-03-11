package main.domain;

/**
 * Clasa derivată din Animals care reprezintă un animal de tip pisică sau câine, cu atribute specifice, 
 * cum ar fi culoarea blănii.
 */
public class Canine_and_Feline extends Animals {
    private String furColor, breed; 
    private boolean isTrained;
    private Species sp;
    
    public enum Species {
        CAT, DOG
    }

    /**
     * Constructor implicit pentru clasa.
     * @param species 
     * @param g 
     * @param f 
     * @param string5 
     * @param string4 
     * @param b 
     * @param e 
     * @param string3 
     * @param d 
     * @param j 
     * @param string2 
     * @param string 
     * @param i 
     * @param species 
     * @param g 
     * @param string5 
     * @param f 
     * @param string4 
     * @param b 
     * @param e 
     * @param string3 
     * @param d 
     * @param j 
     * @param string2 
     * @param string 
     * @param i 
     */
    public Canine_and_Feline() {}

    /**
     * Constructor cu parametri pentru inițializarea unui obiect de tip câine sau pisică.
     *
     * @param furColor      Culoarea blănii
     * @param breed      Rasa animalului
     * @param isTrained  Statusul antrenamentului câinelui (true dacă este antrenat)
     */
    public Canine_and_Feline(int id, String name, String gender, int age, double weight, String diet, double price, boolean fed, String furColor, boolean isTrained, Species sp, String breed) {
        super(id, name, gender, age, weight, diet, price, fed);
        this.furColor = furColor;
        this.breed = breed;
        this.isTrained = isTrained;
        this.sp = sp;
        
    }

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    public String getBreed() {
        return breed;}
    
    public void setBreed(String breed) {
        this.breed = breed;}
    
    public Species getSp() {
        return sp;
    }

    public void setSp(Species sp) {
        this.sp = sp;
        //super.setSpecies(sp.toString());}  // Actualizez atributul species in Animals
    }

    public boolean isTrained() {
		return isTrained;
	}

	public void setTrained(boolean isTrained) {
		this.isTrained = isTrained;
	}

	/**
     * Returnează o descriere a obiectului Cat sub formă de șir de caractere.
     *
     * @return Detaliile pisicii sau cainelui
     */
    
    @Override
    public String toString() {
        return super.toString() + ", Species: " + sp + ", Fur Color: " + furColor +
               ", Breed: " + breed + ", Is Trained: " + isTrained;
    }
    }
