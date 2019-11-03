package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import data.User;

/**
 * Session Bean implementation class PlayersEJB
 */
@Stateless
public class UsersEJB implements UsersEJBRemote {
 @PersistenceContext(name="Users")
 EntityManager em;

    public void UsersEJB() {
        //TODO CONSTRUCTER
    }

    public void createUser(String username, String password){
        User newUser = new User(username,password);
        em.persist(newUser); 
    }
    
}