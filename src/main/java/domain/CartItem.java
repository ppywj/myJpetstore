package domain;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 购物车条目
 *太坑了 这里属性的首字母不能大写不然jsp中使用el访问的时候找不到
 * @author pp
 */


public class CartItem implements Serializable {
    private static final long serialVersionUID = 6620528781626504362L;

    public long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
    }

    private long cartItemId;//购物车id
    private Item item;//商品
    private int quantity;//数量
    private boolean inStock;//是否有库存
    private BigDecimal total;//总价


    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    public void incrementQuantity() {
        quantity++;
        calculateTotal();
    }

    private void calculateTotal() {
        if (item != null && item.getListPrice() != null) {
            total = item.getListPrice().multiply(new BigDecimal(quantity));
        }
        else {
            total = null;
        }
    }

}
