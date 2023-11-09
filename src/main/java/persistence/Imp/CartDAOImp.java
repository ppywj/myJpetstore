package persistence.Imp;

import domain.Cart;
import domain.CartItem;
import domain.Item;
import persistence.CartDAO;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static persistence.DatabaseUtil.*;

/**
 * @author pp
 */
@SuppressWarnings({"all"})
public class CartDAOImp implements CartDAO {
    private static String GETITEMBYUSERNAMESQL =
            "SELECT ci.id AS cart_item_id,\n" +
                    "    ci.itemid AS cart_item_itemid,\n" +
                    "    ci.username AS cart_item_username,\n" +
                    "    ci.quantity AS cart_item_quantity,\n" +
                    "    ci.total AS cart_item_total,\n" +
                    "    it.itemid AS item_itemid,\n" +
                    "    it.productid AS item_productid,\n" +
                    "    it.listprice AS item_listprice,\n" +
                    "    it.unitcost AS item_unitcost,\n" +
                    "    it.supplier AS item_supplier,\n" +
                    "    it.status AS item_status,\n" +
                    "    it.attr1 AS item_attr1,\n" +
                    "    it.attr2 AS item_attr2,\n" +
                    "    it.attr3 AS item_attr3,\n" +
                    "    it.attr4 AS item_attr4,\n" +
                    "    it.attr5 AS item_attr5\n" +
                    "FROM cartitem ci\n" +
                    "JOIN item it ON ci.itemid = it.itemid\n" +
                    "WHERE ci.username =?";
    private static String ADDCARTITEM = ("insert into cartitem (itemid,username,quantity,total) values(?,?,?,?)");
    private static String DELETEITEM = "DELETE FROM cartitem WHERE id = ?";
    private static String CLEARCART="DELETE FROM cartitem where username=?";
    @Override
    public ArrayList<CartItem> getCartItemsByUserName(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(GETITEMBYUSERNAMESQL);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            ArrayList<CartItem> list = new ArrayList<>();
            while (resultSet.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setCartItemId(resultSet.getLong("cart_item_id"));
                cartItem.setUsername(resultSet.getString("cart_item_username"));
                Item item = new Item();
                item.setItemId(resultSet.getString("item_itemid"));
                item.setProductId(resultSet.getString("item_productid"));
                item.setListPrice(resultSet.getBigDecimal("item_listprice"));
                item.setUnitCost(resultSet.getBigDecimal("item_unitcost"));
                item.setSupplierId(resultSet.getInt("item_supplier"));
                item.setStatus(resultSet.getString("item_status"));
                item.setAttribute1(resultSet.getString("item_attr1"));
                item.setAttribute2(resultSet.getString("item_attr2"));
                item.setAttribute3(resultSet.getString("item_attr3"));
                item.setAttribute4(resultSet.getString("item_attr4"));
                item.setAttribute5(resultSet.getString("item_attr5"));
                cartItem.setItem(item);
                cartItem.setQuantity(resultSet.getInt("cart_item_quantity"));
                cartItem.setInStock(item.getQuantity() > 0);
                cartItem.setTotal(resultSet.getBigDecimal("cart_item_total"));
                list.add(cartItem);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int addCartItem(CartItem cartItem) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(ADDCARTITEM);
            startTransaction(connection);
            preparedStatement.setString(1, cartItem.getItem().getItemId());
            preparedStatement.setString(2, cartItem.getUsername());
            preparedStatement.setInt(3, cartItem.getQuantity());
            preparedStatement.setBigDecimal(4, cartItem.getTotal());
            preparedStatement.executeUpdate();
            commitTransaction(connection);
            return 0;
        } catch (SQLException e) {
            rollbackTransaction(connection);
            e.printStackTrace();
            return 1;
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /**
     * 返回0表示删除成功 返回1表示系统删除失败
     *
     * @param itemId
     * @return
     */
    @Override
    public int deleteCartItem(long itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETEITEM);
            startTransaction(connection);
            preparedStatement.setLong(1, itemId);
            preparedStatement.executeUpdate();
            commitTransaction(connection);
            return 0;
        } catch (SQLException e) {
            rollbackTransaction(connection);
            e.printStackTrace();
            return 1;
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public int clearCart(String UserName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(CLEARCART);
            startTransaction(connection);
            preparedStatement.setString(1, UserName);
            preparedStatement.executeUpdate();
            commitTransaction(connection);
            return 0;
        } catch (SQLException e) {
            rollbackTransaction(connection);
            e.printStackTrace();
            return 1;
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }
}