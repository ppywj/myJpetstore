package service;

import domain.User;
import persistence.AccountDAO;
import persistence.Imp.AccountDAOImp;

/**
 * @author pp
 */
public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAOImp();
    }

    public User getAccount(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    //根据账号密码获取用户详细信息
    public User getAccount(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return accountDAO.getAccountByUsernameAndPassword(user);
    }

    public int insertAccount(User user) {
        int res1 = accountDAO.insertAccount(user);
        if (res1 != 0)
            return res1;
        int res2 = accountDAO.insertProfile(user);
        int res3 = accountDAO.insertSignon(user);
        return 0;
    }

    public int updateAccount(User user) {
        int res1 = accountDAO.updateAccount(user);
        if (res1 != 0)
            return res1;
        int res2 = accountDAO.updateProfile(user);
        int res3 = 0;
        if (user.getPassword() != null && user.getPassword().length() > 0) {
            res3 = accountDAO.updateSignon(user);
        }
        return 0;
    }

}
