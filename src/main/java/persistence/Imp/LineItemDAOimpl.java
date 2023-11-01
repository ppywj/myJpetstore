package persistence.Imp;


import domain.LineItem;
import persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static persistence.DatabaseUtil.close;
import static persistence.DatabaseUtil.getConnection;

public class LineItemDAOimpl implements LineItemDAO {
    private static final String getLineItemsByOrderId = "SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String insertLineItem = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";


    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItemList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(getLineItemsByOrderId);
            preparedStatement.setString(1, orderId + "");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                lineItemList.add(lineItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet, preparedStatement, connection);
        }
        return lineItemList;
    }

    @Override
    public boolean insertLineItem(LineItem lineItem) {
        boolean flag = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(insertLineItem);
            preparedStatement.setString(1, lineItem.getOrderId() + "");
            preparedStatement.setString(2, lineItem.getLineNumber() + "");
            preparedStatement.setString(3, lineItem.getItemId());
            preparedStatement.setString(4, lineItem.getQuantity() + "");
            preparedStatement.setString(5, lineItem.getUnitPrice().toString());

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null, preparedStatement, connection);
        }

        return flag;

    }
}
