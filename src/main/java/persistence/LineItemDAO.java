package persistence;

import domain.LineItem;
import java.util.List;

public interface LineItemDAO {
    List<LineItem> getLineItemsByOrderId(int orderId);
    boolean insertLineItem(LineItem lineItem);
}
