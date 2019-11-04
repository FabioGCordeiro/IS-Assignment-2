package ejb;

import javax.ejb.Remote;

@Remote
public interface UsersEJBRemote {
    public boolean createUser(String username, String password, String email, String country);
    public boolean checkUserLogin(String email, String password);
    public void updateUser(String username, String password, String email, String country);
    public void deleteUser(String email);
}