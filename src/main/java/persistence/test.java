package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static persistence.DatabaseUtil.getConnection;
import static persistence.DatabaseUtil.startTransaction;

/**
 * @author pp
 */
public class test {
    private static final String getAccountByUsernameAndPassword = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1," +
            "ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference," +
            "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    public static void main(String[] args) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement statement=connection.prepareStatement(getAccountByUsernameAndPassword);
        statement.setString(1,"ACID");
        statement.setString(2,"ACID");
        ResultSet resultSet=statement.executeQuery();
        if (resultSet.next())
        {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
            System.out.println(resultSet.getString(5));
            System.out.println(resultSet.getString(6));
        }
    }
}