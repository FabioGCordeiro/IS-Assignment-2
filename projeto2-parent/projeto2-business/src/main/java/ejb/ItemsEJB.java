package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import data.Item;

/**
 * Session Bean implementation class PlayersEJB
 */
@Stateless
public class ItemsEJB implements ItemsEJBRemote {
 @PersistenceContext(name="Users")
 EntityManager em;

    public void createItem(String name, String category, String countryOrigin){
        Item newItem = new Item(name, category, countryOrigin);
        em.persist(newItem); 
    }
    
}