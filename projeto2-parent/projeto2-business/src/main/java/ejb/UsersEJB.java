package ejb;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import data.Item;
import data.User;
import data.Hash;

/**
 * Session Bean implementation class PlayersEJB
 */
@Stateless
public class UsersEJB implements UsersEJBRemote {
    @PersistenceContext(name = "User")
    EntityManager em;

    Logger logger = Logger.getLogger(UsersEJB.class.getName());

    public boolean createUser(String username, String password, String email, String country) {
        if (!username.equals("") && !password.equals("") && !email.equals("") && !country.equals("")) {
            Hash passwordHash = new Hash();
            String hashedPassword = "";
            try {
                hashedPassword = passwordHash.createHash(password);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            User newUser = new User(username,hashedPassword, email, country);
            em.persist(newUser);
            return true;
        }
        return false;
    }

    public void deleteUser(String email){
        Query q = em.createQuery("from User u where u.email = :e");
        q.setParameter("e",email);
        User user = (User) q.getSingleResult();

        // DELETE USER ITEMS
        if(!(user.getItems().isEmpty())){
            for (Item i : user.getItems()) {
                q = em.createQuery("delete Item item where item.id = :i");
                q.setParameter("i",i.getId()); 
                q.executeUpdate();
            }
        }

        //DELETE USER
        q = em.createQuery("delete User u where u.email = :e");
        q.setParameter("e",email);
        q.executeUpdate();
    }

    public boolean editUserInfo(String username, String password, String email, String country, String emailS){
        if(!username.equals("") && !password.equals("") && !email.equals("") && !country.equals("")){
            Hash passwordHash = new Hash();
            String hashedPassword = "";
            try {
                hashedPassword = passwordHash.createHash(password);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Query q = em.createQuery("update User set username='" + username + "' where email = '"+ emailS+"'");
            q.executeUpdate();
            q = em.createQuery("update User set password='" + hashedPassword + "' where email = '"+ emailS+"'");
            q.executeUpdate();
            q = em.createQuery("update User set country='" + country + "' where email = '"+ emailS+"'");
            q.executeUpdate();
            q = em.createQuery("update User set email='" + email + "' where email = '"+ emailS+"'");
            q.executeUpdate();
            return true;
        }
        else{
            return false;
        }

        
    }

    public boolean checkUserLogin(String email, String password){
        logger.info("CHECK CREDENTIALS");
        User user = null;
        try{
            Query q = em.createQuery("from User u where u.email = :e");
            q.setParameter("e",email);
            user = (User) q.getSingleResult();
        }catch(NoResultException e){
            return false;
        }
        if(user!=null){
            Hash passwordHash = new Hash();
            try {
                if (passwordHash.validatePassword(password, user.getPassword())) {
                    return true;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }
        else{
            return false;
        }
        
    }

    public User getUser(String email){
        User user = null;
        try{
            Query q = em.createQuery("from User u where u.email = :e");
            q.setParameter("e",email);
            user = (User) q.getSingleResult();
            return user;
        }catch(NoResultException e){
            return null;
        }
    }
}