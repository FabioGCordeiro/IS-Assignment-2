package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import data.Item;
import data.User;

/**
 * Session Bean implementation class PlayersEJB
 */
@Stateless
public class ItemsEJB implements ItemsEJBRemote {
 @PersistenceContext(name="Users")
 EntityManager em;

    public boolean createItem(String name, String category, String countryOrigin, String userEmail){
        if(!name.equals("") && !category.equals("") && !countryOrigin.equals("")){
            Item newItem = new Item(name, category, countryOrigin);
            Query q = em.createQuery("from User u where u.email = :e");
            q.setParameter("e",userEmail);
            User user = (User) q.getSingleResult();
            newItem.setUser(user);
            em.persist(newItem);
            return true; 
        }
        return false;
    }

    public void deleteItem(String itemName, String userEmail){
        Query q = em.createQuery("from User u where u.email = :e");
        q.setParameter("e",userEmail);
        User user = (User) q.getSingleResult();

        // DELETE ITEMS
        for (Item i : user.getItems()) {
            if(i.getName().equals(itemName)){
                q = em.createQuery("delete Item item where item.id = :i");
                q.setParameter("i",i.getId());
            }
        }
        q.executeUpdate();
    }

    public List<Item> getItemsByCategory(String category){
        Query q = em.createQuery("from Item where category = :c");
        q.setParameter("c",category);
        List <Item> items = q.getResultList();
        
        return items;
    }

    public List<Item> getItemsByCountry(String country){
        Query q = em.createQuery("from Item where countryOrigin = :c");
        q.setParameter("c",country);
        List <Item> items = q.getResultList();
        
        return items;
    }
    
}