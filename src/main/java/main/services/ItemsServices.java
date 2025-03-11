package main.services;
import main.domain.Items;

public class ItemsServices {
	 /**
     * Decreases the stock of the specified item by 1.
     * Throws an exception if there is insufficient stock.
     *
     * @param item The item whose stock needs to be reduced
     */
    public void reduceStock(Items item) {
        if (item.getStock() <= 0) {
            throw new IllegalArgumentException("Insufficient stock for item: " + item.getName());
        }
        item.setStockQuantity(item.getStock() - 1);
    }

    /**
     * Checks the available stock of the specified item.
     *
     * @param item The item to check
     * @return The available stock
     */
    public int checkStock(Items item) {
        return item.getStock();
    }
}
