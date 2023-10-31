package persistence.Imp;

import domain.User;
import persistence.AccountDAO;
import persistence.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static persistence.DatabaseUtil.close;
import static persistence.DatabaseUtil.getConnection;

/**
 * @author pp
 * 有关账户的数据库操作的实现类
 * 待优化：对于更新数据的操作使用事务机制
 */
@SuppressWarnings({"all"})
public class AccountDAOImp implements AccountDAO {
    private static final String getAccountByUsername = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1," +
            "ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String getAccountByUsernameAndPassword = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1," +
            "ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String updateAccount = "UPDATE ACCOUNT SET EMAIL = ?,FIRSTNAME = ?,LASTNAME = ?,STATUS = ?,ADDR1 = ?," +
            "ADDR2 = ?,CITY = ?,STATE = ?,ZIP = ?,COUNTRY = ?,PHONE = ? WHERE USERID = ?";

    private static final String insertAccount = "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES" +
            "(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";

    private static final String updateProfile = "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ?,mylistopt = ?,banneropt = ? WHERE USERID = ?";

    private static final String insertProfile = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID, mylistopt, banneropt) VALUES (?, ?, ?, ?, ?)";

    private static final String updateSignon = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";

    private static final String insertSignon = "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";

    @Override
    public User getAccountByUsername(String username) {
        User user = null;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsername);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                user = new User();
                user.setUsername(resultSet.getString(1));
                user.setEmail(resultSet.getString(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setStatus(resultSet.getString(5));
                user.setAddress1(resultSet.getString(6));
                user.setAddress2(resultSet.getString(7));
                user.setCity(resultSet.getString(8));
                user.setState(resultSet.getString(9));
                user.setZip(resultSet.getString(10));
                user.setCountry(resultSet.getString(11));
                user.setPhone(resultSet.getString(12));
                user.setLanguagePreference(resultSet.getString(13));
                user.setFavouriteCategoryId(resultSet.getString(14));
                user.setListOption(resultSet.getInt(15) == 1);
                user.setBannerOption(resultSet.getInt(16) == 1);
                user.setBannerName(resultSet.getString(18));

                close(resultSet,preparedStatement,connection);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getAccountByUsernameAndPassword(User user) {
        User checkUser = null;
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameAndPassword);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                System.out.println("有数据");
                checkUser = new User();
                checkUser.setUsername(resultSet.getString(1));
                checkUser.setEmail(resultSet.getString(2));
                checkUser.setFirstName(resultSet.getString(3));
                checkUser.setLastName(resultSet.getString(4));
                checkUser.setStatus(resultSet.getString(5));
                checkUser.setAddress1(resultSet.getString(6));
                checkUser.setAddress2(resultSet.getString(7));
                checkUser.setCity(resultSet.getString(8));
                checkUser.setState(resultSet.getString(9));
                checkUser.setZip(resultSet.getString(10));
                checkUser.setCountry(resultSet.getString(11));
                checkUser.setPhone(resultSet.getString(12));
                checkUser.setLanguagePreference(resultSet.getString(13));
                checkUser.setFavouriteCategoryId(resultSet.getString(14));
                checkUser.setListOption((resultSet.getInt(15) == 1));
                checkUser.setBannerOption((resultSet.getInt(16) == 1));
                checkUser.setBannerName(resultSet.getString(17));

                close(resultSet,preparedStatement,connection);

            }
            else
            {
                System.out.println("没数据");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return checkUser;
    }

    @Override
    public boolean insertAccount(User user) {
        boolean flag = false;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertAccount);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getStatus());
            preparedStatement.setString(5, user.getAddress1());
            preparedStatement.setString(6, user.getAddress2());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getState());
            preparedStatement.setString(9, user.getZip());
            preparedStatement.setString(10, user.getCountry());
            preparedStatement.setString(11, user.getPhone());
            preparedStatement.setString(12, user.getUsername());
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            close(null,preparedStatement,connection);
        } catch (Exception e){
            e.printStackTrace();
        }


        return flag;
    }

    @Override
    public boolean insertProfile(User user) {
        boolean flag = false;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertProfile);
            preparedStatement.setString(1, user.getLanguagePreference());
            preparedStatement.setString(2, user.getFavouriteCategoryId());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setInt(4,(user.isListOption())?1:0);
            preparedStatement.setInt(5,(user.isBannerOption())?1:0);
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }
            close(null,preparedStatement,connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean insertSignon(User user) {
        boolean flag = false;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSignon);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

          close(null,preparedStatement,connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateAccount(User user) {
        boolean flag = false;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateAccount);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getStatus());
            preparedStatement.setString(5, user.getAddress1());
            preparedStatement.setString(6, user.getAddress2());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getState());
            preparedStatement.setString(9, user.getZip());
            preparedStatement.setString(10, user.getCountry());
            preparedStatement.setString(11, user.getPhone());
            preparedStatement.setString(12, user.getUsername());
            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            close(null,preparedStatement,connection);
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateProfile(User user) {
        boolean flag = false;
        //updateProfile
        //UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ?,mylistopt = ?,banneropt = ? WHERE USERID = ?

        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateProfile);
            preparedStatement.setString(1, user.getLanguagePreference());
            preparedStatement.setString(2, user.getFavouriteCategoryId());
            preparedStatement.setInt(3, user.isListOption()?1:0);
            preparedStatement.setInt(4, user.isBannerOption()?1:0);
            preparedStatement.setString(5, user.getUsername());

            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            close(null,preparedStatement,connection);
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public boolean updateSignon(User user) {
        //UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?
        boolean flag = false;
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSignon);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());

            int row = preparedStatement.executeUpdate();

            if(row == 1){
                flag = true;
            }

            close(null,preparedStatement,connection);
        } catch (Exception e){
            e.printStackTrace();
        }

        return flag;

    }
}
