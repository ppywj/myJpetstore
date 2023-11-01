package persistence.Imp;

import domain.Product;
import persistence.ProductDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static persistence.DatabaseUtil.close;
import static persistence.DatabaseUtil.getConnection;

public class ProductDAOImpl implements ProductDAO {
    private static final String getProductListByCategoryString = "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";
    private static final String getProductString = "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String searchProductListString = "select PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{

           connection = getConnection();
            preparedStatement = connection.prepareStatement(getProductListByCategoryString);
            preparedStatement.setString(1,categoryId);
             resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(resultSet,preparedStatement,connection);
        }
        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
             connection =getConnection();
            preparedStatement = connection.prepareStatement(getProductString);
            preparedStatement.setString(1,productId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(resultSet,preparedStatement,connection);
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{

            connection = getConnection();
            preparedStatement = connection.prepareStatement(searchProductListString);
            preparedStatement.setString(1,keywords);
            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           close(resultSet,preparedStatement,connection);
        }
        return productList;
    }
}
