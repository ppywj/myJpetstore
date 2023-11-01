package service;

import domain.Category;
import domain.Item;
import domain.Product;
import persistence.CategoryDAO;
import persistence.Imp.CategoryDAOImpl;
import persistence.Imp.ItemDAOImpl;
import persistence.Imp.ProductDAOImpl;
import persistence.ItemDAO;
import persistence.ProductDAO;

import java.util.List;

/**
 * 商品日志
 * @author pp
 */
public class CatalogService {

    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private ItemDAO itemDAO;

    public CatalogService(){
        categoryDAO = new CategoryDAOImpl();
        productDAO = new ProductDAOImpl();
        itemDAO = new ItemDAOImpl();
    }

    /**
     * 获取商品种类信息列表
     * @return
     */
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    /**
     * 根据种类id获取种类信息
     * @param categoryId
     * @return
     */
    public Category getCategory(String categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    /**
     * 根据商品id获取商品信息
     * @param productId
     * @return
     */
    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
    }

    /**
     * 根据种类id获取该类所有商品信息
     * @param categoryId
     * @return
     */
    public List<Product> getProductListByCategory(String categoryId) {
        return productDAO.getProductListByCategory(categoryId);
    }

    /**
     * 根据关键字获取商品列表（实现搜索功能）
     * @param keyword
     * @return
     */
    public List<Product> searchProductList(String keyword) {
        return productDAO.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    /**
     * 更具商品ID获取
     * @param productId
     * @return
     */
    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    /**
     * 根据商品项id获取商品项信息
     * @param itemId
     * @return
     */
    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    /**
     *商品是否还有库存
     * @param itemId
     * @return
     */
    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }

}
