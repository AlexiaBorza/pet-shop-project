package main.domain;
import main.services.ItemsServices;
import java.util.ArrayList;
import java.util.List; 

/**
 * Clasa Clients reprezintă un client cu un ID unic, un email și un istoric de achiziții.
 * Aceasta permite adăugarea achizițiilor și oferă metode pentru accesarea și modificarea informațiilor despre clienți.
 */
public class Clients {
	private int id; 
    private String email; 
    private List<String> purchases; 
    
    /**
     * Constructor pentru crearea unui client cu ID și email specificate.
     *
     * @param id ID-ul clientului
     * @param email Adresa de email a clientului
     */
    public Clients(int id, String email, List<String> purchases) {
        this.id = id;
        this.email = email;
        this.purchases = new ArrayList<>();
    }
    
    /**
     * Constructor implicit pentru crearea unui client anonim.
     * Inițializează ID-ul la 0, email-ul la "None" și istoricul achizițiilor ca listă goală.
     */
    public Clients() {
        this.id = 0;
        this.email = "None";
        this.purchases = new ArrayList<>();
    }

    public int getId() {
        return id;}

    public void setId(int id) {
        this.id = id;}

    public String getEmail() {
        return email;}

    public void setEmail(String email) {
        this.email = email;}

    public List<String> getPurchases() {
        return purchases;}

    /**
     * Adauga un obiect cumparat la istoricul tranzactilor clientului si le reduce din stocul total
     *
     * @param item Obiectul care este cumparat
     * @param itemsServices The ItemsServices instance to handle stock reduction
     */
    public void addPurchase(Items item, ItemsServices itemsServices) {
        itemsServices.reduceStock(item); // Reducem obiectul din stoc
        purchases.add(item.getPurchaseDetails());  // Adaug detalile obiectului la istoricul de tranzactii
    }
    
    
    /**
     * Returnează o descriere a clientului sub formă de șir de caractere.
     * Afișează detalii despre ID-ul clientului, email-ul și istoricul achizițiilor.
     *
     * @return Descrierea clientului
     */
    @Override
    public String toString() {
        StringBuilder client_det = new StringBuilder();
        client_det.append("Client Details:\n");
        client_det.append("ID: ").append(id == 0 ? "Anonymous" : id).append("\n");
        client_det.append("Email: ").append(email).append("\n");
        client_det.append("Purchase History:\n");
        if (purchases.isEmpty()) {
        	client_det.append("No purchases made yet.\n");
        } else {
            for (String p : purchases) {
            	client_det.append("- ").append(p).append("\n");
            }
        }
        return client_det.toString();
    }
}
