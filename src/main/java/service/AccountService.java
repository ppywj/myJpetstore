package service;

import domain.User;
import persistence.AccountDAO;
import persistence.Imp.AccountDAOImp;

/**
 * @author pp
 */
public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAOImp();
    }

    public User getAccount(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    //根据账号密码获取用户详细信息
    public User getAccount(String username, String password) {
        User user= new User();
        user.setUsername(username);
        user.setPassword(password);
        return accountDAO.getAccountByUsernameAndPassword(user);
    }

    public void insertAccount(User user) {
        accountDAO.insertAccount(user);
        accountDAO.insertProfile(user);
        accountDAO.insertSignon(user);
    }

    public void updateAccount(User user) {
        accountDAO.updateAccount(user);
        accountDAO.updateProfile(user);

        if (user.getPassword() != null && user.getPassword().length() > 0) {
            accountDAO.updateSignon(user);
        }
    }

}
