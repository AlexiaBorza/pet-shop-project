package main.domain;
import java.util.List;
import java.util.ArrayList;

/**
 * Clasa derivată din Items care reprezintă articolele de tip mâncare pentru animale.
 */
public class Food extends Items{
	    private String diet, expiration_date;
	    private List<String> ingredients;

	    
	    /**
	     * Constructor implicit pentru un obiect Food.
	     */
	    public Food() {}
	     
	    /**
	     * Constructor pentru inițializarea unui articol de tip mâncare.
	     *
	     * @param diet Tipul de dietă
	     * @param expiration_date Data expirarii
	     * @param ingredients Lista ingredientelor
	     */
	    public Food(int id, String name, String brand, String forAnimal, double price, int stock, String diet, String expiration_date,  List<String> ingredients) {
	        super(id, name, brand, forAnimal, price, stock);
	        this.diet = diet;
	        this.expiration_date = expiration_date;
	        this.ingredients = new ArrayList<>();
	    }
	    	
		
		 public String getDiet() {
			return diet;
		}

		public void setDiet(String diet) {
			this.diet = diet;
		}

		public String getExpiration_date() {
			return expiration_date;
		}


		public void setExpiration_date(String expiration_date) {
			this.expiration_date = expiration_date;
		}


		public List<String> getIngredients() {
	        return ingredients;
	    }


		public void setIngredients(List<String> ingredients) {
	        this.ingredients = new ArrayList<>(ingredients);
	    }

		/**
	     * Returneaza o descriere a articolului de tip mâncare sub forma de sir de caractere.
	     *
	     * @return Detaliile articolului de tip mâncare
	     */
		@Override
		public String toString() {
		    return "ID: " + id + "\n" +
		           "Name: " + name + "\n" +
		           "Brand: " + brand + "\n" +
		           "Animal Destined To: " + forAnimal + "\n" +
		           "Price: $" + price + "\n" +
		           "Stock Quantity: " + stock + "\n" +
		           "Diet Type: " + diet + "\n" +
		           "Expires at: " + expiration_date + "\n" +
		           "Ingredients: " + String.join(", ", ingredients);
		}
}
