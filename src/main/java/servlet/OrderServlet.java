package servlet;

import domain.Cart;
import domain.Order;
import domain.User;
import service.AccountService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;

/**
 * @author pp
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private static final String VIEWORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String LOGIN = "/WEB-INF/jsp/account/login.jsp";
    private AccountService service = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            request.getRequestDispatcher(LOGIN).forward(request, response);
        Order order = (Order) session.getAttribute("order");
        session.setAttribute("lineItems", order.getLineItems());
        OrderService orderService = new OrderService();
        orderService.insertOrder(order);
        // 重置购物车
        service.clearCart(user.getUsername());
        Cart cart = new Cart();
        session.setAttribute("cart", cart);
        request.getRequestDispatcher(VIEWORDER).forward(request, response);
    }
}