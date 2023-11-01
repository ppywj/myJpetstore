package persistence;


import domain.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    boolean insertOrder(Order order);

    boolean insertOrderStatus(Order order);
}
