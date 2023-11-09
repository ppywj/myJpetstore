package persistence;

import persistence.Imp.logInfoDAOImp;

import java.math.BigDecimal;
import java.sql.*;

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
//        ItemDAOImpl imp = new ItemDAOImpl();
//        Item item = imp.getItem("EST-10");
//        CartDAOImp im = new CartDAOImp();
//        CartItem cartItem = new CartItem();
//        cartItem.setItem(item);
//        cartItem.setQuantity(10);
//        cartItem.setTotal(item.getListPrice().multiply(new BigDecimal(10)));
//        cartItem.setUsername("1234");
//        im.addCartItem(cartItem);
//        im.deleteCartItem(1);
//        ArrayList<CartItem> list = im.getCartItemsByUserName("1234");
//        for (CartItem i : list) {
//            System.out.println(i.getCartItemId());
//        }

        logInfoDAOImp logInfoDAO = new logInfoDAOImp();

        // 测试 addViewCategoryRecord 方法
        logInfoDAO.addViewCategoryRecord("testUser", "testItem", new Timestamp(System.currentTimeMillis()));

        // 测试 addLoginRecord 方法
        logInfoDAO.addLoginRecord("testUser", new Timestamp(System.currentTimeMillis()), "192.168.1.100");

        // 测试 addRegisterRecord 方法
        logInfoDAO.addRegisterRecord("testUser", "192.168.1.100", new Timestamp(System.currentTimeMillis()));

        // 测试 addOrderRecord 方法
        logInfoDAO.addOrderRecord("testUser", new BigDecimal("100.00"), "testCategory", new Timestamp(System.currentTimeMillis()));
    }
}