package service;

import domain.Cart;
import domain.CartItem;
import persistence.AccountDAO;
import persistence.Imp.AccountDAOImp;
import persistence.Imp.CartDAOImp;
import persistence.Imp.ItemDAOImpl;

import java.util.ArrayList;

/**
 * @author pp
 */
public class CartService {
    private ItemDAOImpl itemDao = new ItemDAOImpl();
    private CartDAOImp cartDao = new CartDAOImp();
    private AccountDAO accountDao = new AccountDAOImp();
    //获取用户的购物车对象
    public Cart getCartByUserName(String userName)
    {
       ArrayList<CartItem>cartItems=cartDao.getCartItemsByUserName(userName);
       Cart cart=new Cart();
       cart.setCartItems(cartItems);
       cart.setNumberOfItems(cartItems.size());
       cart.setUserName(userName);
       return cart;
    }
    //根据用户账号获取所有购物车条目
    public ArrayList<CartItem> getCartItemsByUserName(String userName)
    {
        return cartDao.getCartItemsByUserName(userName);
    }
    //根据购物策划条目的ID删除对应的购物车条目
    //返回true表示删除成功 反之删除失败
     public boolean DeleteCartItemByCartItemId(long id)
    {
        return cartDao.deleteCartItem(id)==0;
    }

    //添加购物车条目
    //返回true表示添加成功反之添加失败
    public boolean AddCartItemToCart (CartItem item)
    {
        return cartDao.addCartItem(item)==0;
    }

}