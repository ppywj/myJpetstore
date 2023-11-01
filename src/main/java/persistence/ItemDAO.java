package persistence;

import java.util.List;
import java.util.Map;
import domain.Item;

public interface ItemDAO {

    void updateInventoryQuantity(Map<String, Object> param);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);
}
