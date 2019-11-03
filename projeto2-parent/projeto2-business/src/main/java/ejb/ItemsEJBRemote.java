package ejb;

import javax.ejb.Remote;

@Remote
public interface ItemsEJBRemote {
    public void createItem(String name, String category, String countryOrigin);
}