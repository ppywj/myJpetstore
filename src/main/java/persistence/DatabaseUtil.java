package persistence;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import static com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource;

/**
 * @author pp
 * 数据库给工具类--提供获取数据源，关闭数据库资源，开启事务，提交事务，回滚事务
 */

public class DatabaseUtil {
    //数据源
    private static DataSource source;

    //在静态代码块中完成初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(DatabaseUtil.class.getClassLoader().getResourceAsStream("dataBaseConfig.properties"));
            source = createDataSource(properties);
            System.out.println("test");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }


    //关闭连接--返回连接池
    public static void close(ResultSet res, Statement statement, Connection connection) {
        try {
            if (res != null)
                res.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //开启事务
    public static void startTransaction(Connection connection) {
        if (connection == null)
            return;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提交事务
    public static void commitTransaction(Connection connection) {
        if (connection == null)
            return;
        try {
            if (!connection.getAutoCommit())
                connection.commit();
            else
                return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //回滚事务
    public static void rollbackTransaction(Connection connection) {
        if (connection == null)
            return;
        try {
            if (!connection.getAutoCommit())
                return;
            else
                connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}