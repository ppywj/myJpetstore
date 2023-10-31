package persistence;

import domain.User;

/**
 * @author pp
 * 有关账户的数据库操作接口
 */
public interface AccountDAO {
    User getAccountByUsername(String username);

    User getAccountByUsernameAndPassword(User user);

    int insertAccount(User user);

    int insertProfile(User user);

    int  insertSignon(User user);

    int  updateAccount(User user);

    int updateProfile(User user);

    int  updateSignon(User user);
}
