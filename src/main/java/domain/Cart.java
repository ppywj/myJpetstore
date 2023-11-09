package domain;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author pp
 * 购物车类 表示一个人的购物车
 */
public class Cart {
    private ArrayList<CartItem> cartItems;//购物车条目列表
    private int numberOfItems;//购物车条目数量
    private String userName;//购物车所有者的账号
    private BigDecimal subTotal = new BigDecimal("0");

    private void calculateTotal(ArrayList<CartItem> items) {
        if (items == null)
            return;
        for (CartItem item : items) {
            subTotal=subTotal.add(item.getTotal());
        }
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }


    public Cart() {

    }

    public Cart(ArrayList<CartItem> cartItems, int numberOfItems, String userName) {
        this.cartItems = cartItems;
        this.numberOfItems = numberOfItems;
        this.userName = userName;
        this.calculateTotal(cartItems);
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
        this.calculateTotal(cartItems);
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}