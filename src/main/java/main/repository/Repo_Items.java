package main.repository;
import main.domain.Items;
import java.util.ArrayList;
import java.util.List;
/**
 * Clasa Repo_Items gestionează operațiunile CRUD pentru obiectele de tip Items și clasele derivate.
 */
public class Repo_Items {
    private List<Items> itemsList; // Lista obiectelor de tip Items
    /**
     * Constructor care inițializează lista de obiecte.
     */
    public Repo_Items() {
        itemsList = new ArrayList<>();
    }
    /**
     * Creează și adaugă un nou item în listă.
     *
     * @param item Obiectul care urmează să fie adăugat
     */
    public void createItem(Items item) {
        itemsList.add(item);
    }
    /**
     * Citește toate obiectele din listă.
     *
     * @return Lista completă de obiecte
     */
    public List<Items> readAllItems() {
        return itemsList;
    }
    /**
     * Găsește un item după ID.
     *
     * @param id ID-ul obiectului căutat
     * @return Obiectul găsit sau null dacă nu există
     */
    public Items readItemById(int id) {
        for (Items item : itemsList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    /**
     * Actualizează un item specific din listă.
     *
     * @param id ID-ul obiectului care trebuie actualizat
     * @param updatedItem Obiectul actualizat
     * @return true dacă actualizarea a reușit, false altfel
     */
    public boolean updateItem(int id, Items updatedItem) {
        for (int i = 0; i < itemsList.size(); i++) {
            if (itemsList.get(i).getId() == id) {
                itemsList.set(i, updatedItem);
                return true;
            }
        }
        return false;
    }
    /**
     * Șterge un item din listă.
     *
     * @param id ID-ul obiectului care trebuie șters
     * @return true dacă ștergerea a fost reușită, false în caz contrar
     */
    public boolean deleteItem(int id) {
        return itemsList.removeIf(item -> item.getId() == id);
    }
}