package ejb;

import javax.ejb.Remote;

@Remote
public interface ItemsEJBRemote {
    public boolean createItem(String name, String category, String countryOrigin, String userEmail);
    public void deleteItem(String itemName, String userEmail);
}