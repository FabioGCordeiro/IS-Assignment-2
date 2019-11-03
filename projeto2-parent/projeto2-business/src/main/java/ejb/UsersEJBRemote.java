package ejb;

import javax.ejb.Remote;

@Remote
public interface UsersEJBRemote {
    public void UsersEJB();
    public void createUser(String username, String password);
}