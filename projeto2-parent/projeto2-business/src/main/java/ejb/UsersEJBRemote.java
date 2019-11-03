package ejb;

import javax.ejb.Remote;

@Remote
public interface UsersEJBRemote {
    public void createUser(String username, String password, String email, String country);
}