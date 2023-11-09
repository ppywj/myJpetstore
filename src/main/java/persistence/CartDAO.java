package persistence;

import domain.Cart;
import domain.CartItem;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author pp
 * 购物车数据库操作接口
 */
public interface CartDAO {
    /**
     * 根据用户名查找他的所有购物车条目
     * @param userName
     * @return
     */
    ArrayList<CartItem>getCartItemsByUserName(String userName);

    /**
     * 添加购物车条目
     * @param cartItem
     * @return
     */
    int addCartItem(CartItem cartItem);

    /**
     * 根绝购物车条目id删除条目
     * @param itemId
     * @return
     */
     int deleteCartItem(long itemId);
     int clearCart(String UserName);

}