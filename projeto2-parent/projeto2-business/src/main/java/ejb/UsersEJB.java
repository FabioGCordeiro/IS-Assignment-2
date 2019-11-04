package ejb;

import javax.ejb.Stateless;
import javax.persistence.Query;
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

    public boolean createUser(String username, String password, String email, String country){
        if(!username.equals("") && !password.equals("") && !email.equals("") && !country.equals("")){
            User newUser = new User(username,password, email, country);
            em.persist(newUser);
            return true;
        }
        return false;
    }

    public void deleteUser(String email){
        Query q = em.createQuery("delete User u where u.email = :e");
        q.setParameter("e",email);
        q.executeUpdate();
    }

    public void updateUser(String username, String password, String email, String country){
        if(!username.equals("")){
            Query q = em.createQuery("update User set username=" + username +" where u.email = :e");
            q.setParameter("e",email);
            q.executeUpdate();
        }
    }

    public boolean checkUserLogin(String email, String password){
        Query q = em.createQuery("from User u where u.email = :e");
        q.setParameter("e",email);
        User user = (User) q.getSingleResult();
        if(password.equals(user.getPassword())){
            return true;
        }
        return false;
    }

}