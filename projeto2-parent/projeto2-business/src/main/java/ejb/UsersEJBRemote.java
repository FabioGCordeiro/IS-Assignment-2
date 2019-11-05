package ejb;

import javax.ejb.Remote;

import data.User;

@Remote
public interface UsersEJBRemote {
    public boolean createUser(String username, String password, String email, String country);
    public boolean checkUserLogin(String email, String password);
    public void updateUser(String username, String password, String email, String country);
    public void deleteUser(String email);
    public User getUser(String email);
}