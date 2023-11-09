package servlet;

import domain.Cart;
import domain.Order;
import domain.User;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author pp
 */
@WebServlet("/viewOrderForm")
public class ViewOrderForm extends HttpServlet {
    private static final String VIEW_ORDER_FORM="/WEB-INF/jsp/order/OrderForm.jsp";
    private static final String LOGIN="/WEB-INF/jsp/order/login.jsp";
    private CartService cartService=new CartService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        if(user==null)
        {
            req.getRequestDispatcher(LOGIN).forward(req,resp);
            return;
        }
        //获取购物车信息
        Cart cart=cartService.getCartByUserName(user.getUsername());
        Order order=new Order();
        order.initOrder(user,cart);
        session.setAttribute("order",order);
        req.getRequestDispatcher(VIEW_ORDER_FORM).forward(req,resp);
    }
}