package persistence;

import domain.User;

/**
 * @author pp
 * 有关账户的数据库操作接口
 */
public interface AccountDAO {
    User getAccountByUsername(String username);

    User getAccountByUsernameAndPassword(User user);

    boolean insertAccount(User user);

    boolean insertProfile(User user);

    boolean insertSignon(User user);

    boolean updateAccount(User user);

    boolean updateProfile(User user);

    boolean updateSignon(User user);
}
