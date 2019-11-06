package ejb;

import java.util.List;

import javax.ejb.Remote;

import data.Item;

@Remote
public interface ItemsEJBRemote {
    public boolean createItem(String name, String category, String countryOrigin, Float price, String userEmail);
    public void deleteItem(String itemName, String userEmail);
    public boolean editItem(String name, String category, String countryOrigin, Float price, int id);
    public List<Item> getItems();
    public List<Item> getItemsByPrice(Float lowestPrice, Float HighestPrice);
    public List<Item> getItemsByCategory(String category);
    public List<Item> getItemsByCountry(String country);
	public Item getItem(int id);
}