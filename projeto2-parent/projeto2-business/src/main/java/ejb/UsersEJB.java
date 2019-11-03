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
 @PersistenceContext(name="User")
 EntityManager em;

    public void createUser(String username, String password, String email, String country){
        User[] users = {
            new User(username,password, email, country),
        };

        for(User u : users){
            em.persist(u);
        }
    }

}