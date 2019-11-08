package ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import data.Item;
import data.User;

@Stateless
public class ItemsEJB implements ItemsEJBRemote {
 @PersistenceContext(name="User")
 EntityManager em;


 Logger logger = Logger.getLogger(ItemsEJB.class.getName());

    public boolean createItem(String name, String category, String countryOrigin, Float price, String userEmail,Integer insertionDate, byte[] photo) {
        if (!name.equals("") && !category.equals("") && !countryOrigin.equals("") && price!=null) {
            Item newItem = new Item(name, category, countryOrigin, price, insertionDate);
            logger.info("Trying to create a new item: "+newItem.toString());
            Query q = em.createQuery("from User u where u.email = :e");
            q.setParameter("e", userEmail);
            User user = (User) q.getSingleResult();
            newItem.setUser(user);
            newItem.setPhoto(photo);
            em.persist(newItem);
            logger.info("Item created");
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

    public void deleteItem(int itemId, String userEmail){
        Query q = em.createQuery("from User u where u.email = :e");
        q.setParameter("e",userEmail);
        User user = (User) q.getSingleResult();

        // DELETE ITEMS
        for (Item i : user.getItems()) {
            if(i.getId().equals(itemId)){
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

    public List<Item> getItemsByPrice(Float lowestPrice, Float highestPrice){
        if(lowestPrice!=null && highestPrice!=null){
            Query q = em.createQuery("from Item where price >= :p1 and price <= :p2");
            q.setParameter("p1",lowestPrice);
            q.setParameter("p2", highestPrice);
            List <Item> items = q.getResultList();
            return items;
        }

        return null;
    }

    public List<Item> getItemsByDate(int date){
        if((Integer)date!=null){
            Query q = em.createQuery("from Item where insertionDate >= :p1");
            q.setParameter("p1",date);
            List <Item> items = q.getResultList();
            return items;
        }

        return null;
    }

    public List<Item> getItemsByCategory(String category){
        if(!category.equals("")){
            Query q = em.createQuery("from Item where category like '%"+ category +"%'");
            List <Item> items = q.getResultList();
            
            return items;
        }

        return null;
    }

    public List<Item> getItemsByName(String name){
        if(!name.equals("")){
            Query q = em.createQuery("from Item where name like '%"+ name +"%'");
            List <Item> items = q.getResultList();
            return items; 
        }

        return null;
    }

    public List<Item> getItemsByCountry(String country){
        if(!country.equals("")){
            Query q = em.createQuery("from Item where countryOrigin = :c");
            q.setParameter("c",country);
            List <Item> items = q.getResultList();
            
            return items;
        }

        return null;
    }

    public Item getItem(Integer id){
        if(id!=null){
            Query q = em.createQuery("from Item i where i.id = :i");
            q.setParameter("i",id);
            Item item = (Item) q.getSingleResult();
            return item;
        }

        return null;
    }

    public List<Item> getNewestItems(){
        Query q = em.createQuery("from Item");
        List <Item> items = q.getResultList();

        List<Item> newestItems = new ArrayList<Item>();
        
        for(int i=0; i<3; i++){
            newestItems.add(i, items.get(i));
        }
        return newestItems;
    }

    
}