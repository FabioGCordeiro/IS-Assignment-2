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
 @PersistenceContext(name="User")
 EntityManager em;

    public boolean createItem(String name, String category, String countryOrigin, Float price, String userEmail){
        if(!name.equals("") && !category.equals("") && !countryOrigin.equals("")){
            Item newItem = new Item(name, category, countryOrigin, price);
            Query q = em.createQuery("from User u where u.email = :e");
            q.setParameter("e",userEmail);
            User user = (User) q.getSingleResult();
            newItem.setUser(user);
            em.persist(newItem);
            return true; 
        }
        return false;
    }


    public boolean editItem(String name, String category, String countryOrigin, Float price, int id){
        if(!name.equals("") && !category.equals("") && !countryOrigin.equals("") && price!=null){
            Query q = em.createQuery("update Item set name='" + name + "' where id = "+ id);
            q.executeUpdate();
            q = em.createQuery("update Item set category='" + category + "' where id = "+ id);
            q.executeUpdate();
            q = em.createQuery("update Item set countryOrigin='" + countryOrigin + "' where id = "+ id);
            q.executeUpdate();
            q = em.createQuery("update Item set price=" + price + " where id = "+ id);
            q.executeUpdate();
            return true;
        }
        else{
            return false;
        }

        
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

    public List<Item> getItems(){
        Query q = em.createQuery("from Item");
        List <Item> items = q.getResultList();
        
        return items;
    }

    public List<Item> getItemsByPrice(Float lowestPrice, Float HighestPrice){
        Query q = em.createQuery("from Item where price >= :p1 and price <= :p2");
        q.setParameter("p1",lowestPrice);
        q.setParameter("p2", HighestPrice);
        List <Item> items = q.getResultList();

        return items;
    }

    public List<Item> getItemsByCategory(String category){
        Query q = em.createQuery("from Item where category like '%"+ category +"%'");
        List <Item> items = q.getResultList();
        
        return items;
    }

    public List<Item> getItemsByCountry(String country){
        Query q = em.createQuery("from Item where countryOrigin = :c");
        q.setParameter("c",country);
        List <Item> items = q.getResultList();
        
        return items;
    }

    public Item getItem(int id){
        Query q = em.createQuery("from Item i where i.id = :i");
        q.setParameter("i",id);
        Item item = (Item) q.getSingleResult();
        return item;
    }


    
}