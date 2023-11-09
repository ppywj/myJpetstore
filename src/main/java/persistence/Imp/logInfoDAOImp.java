package persistence.Imp;

import persistence.logInfoDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static persistence.DatabaseUtil.*;

/**
 * @author pp
 */
@SuppressWarnings({"all"})
public class logInfoDAOImp implements logInfoDAO {
    private static final String ADDVIEWCATEGORYRECORD = "insert into viewrecord (userAccount,category,date) values(?,?,?)";
    private static final String ADDLOGINRECORD = "insert into loginrecord (userAccount,ip,date) values(?,?,?)";
    private static final String ADDREGISTERRECORD = "insert into registerrecord (userAccount,ip,date) values(?,?,?)";
    private static final String ADDORDERRECORD = "insert into orderrecord (userAccount,total,category,date) values(?,?,?,?)";
    private static final String ADDVIEWITEMRECORD="insert into viewitemrecord (userAccount,productname,date) values(?,?,?)";

    @Override
    public void addViewCategoryRecord(String userAccount, String category, Timestamp datetTime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            startTransaction(connection);
            preparedStatement = connection.prepareStatement(ADDVIEWCATEGORYRECORD);
            preparedStatement.setString(1,userAccount);
            preparedStatement.setString(2,category);
            preparedStatement.setTimestamp(3,datetTime);
            preparedStatement.executeUpdate();
            commitTransaction(connection);
        } catch (SQLException e) {
            rollbackTransaction(connection);
            e.printStackTrace();
        } finally {
            close(null, preparedStatement, connection);
        }
    }

    @Override
    public void addLoginRecord(String userAccount, Timestamp dateTime, String ip) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            startTransaction(connection);
            preparedStatement = connection.prepareStatement(ADDLOGINRECORD);
            preparedStatement.setString(1,userAccount);
            preparedStatement.setString(2,ip);
            preparedStatement.setTimestamp(3,dateTime);
            preparedStatement.executeUpdate();
            commitTransaction(connection);
        } catch (SQLException e) {
            rollbackTransaction(connection);
            e.printStackTrace();
        } finally {
            close(null, preparedStatement, connection);
        }
    }

    @Override
    public void addRegisterRecord(String userAccount, String ip, Timestamp dateTime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            startTransaction(connection);
            preparedStatement = connection.prepareStatement(ADDREGISTERRECORD);
            preparedStatement.setString(1,userAccount);
            preparedStatement.setString(2,ip);
            preparedStatement.setTimestamp(3,dateTime);
            preparedStatement.executeUpdate();
            commitTransaction(connection);
        } catch (SQLException e) {
            rollbackTransaction(connection);
            e.printStackTrace();
        } finally {
            close(null, preparedStatement, connection);
        }
    }

    @Override
    public void addOrderRecord(String userAccount, BigDecimal total, String category, Timestamp dateTime){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            startTransaction(connection);
            preparedStatement = connection.prepareStatement(ADDORDERRECORD);
            preparedStatement.setString(1,userAccount);
            preparedStatement.setBigDecimal(2,total);
            preparedStatement.setString(3,category);
            preparedStatement.setTimestamp(4,dateTime);
            preparedStatement.executeUpdate();
            commitTransaction(connection);
        } catch (SQLException e) {
            rollbackTransaction(connection);
            e.printStackTrace();
        } finally {
            close(null, preparedStatement, connection);
        }
    }

    @Override
    public void addViewItemRecord(String userAccount, String itemName, Timestamp dateTime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            startTransaction(connection);
            preparedStatement = connection.prepareStatement(ADDVIEWITEMRECORD);
            preparedStatement.setString(1,userAccount);
            preparedStatement.setString(2,itemName);
            preparedStatement.setTimestamp(3,dateTime);
            preparedStatement.executeUpdate();
            commitTransaction(connection);
        } catch (SQLException e) {
            rollbackTransaction(connection);
            e.printStackTrace();
        } finally {
            close(null, preparedStatement, connection);
        }
    }
}