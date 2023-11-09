package servlet;

import domain.Cart;
import domain.CartItem;
import domain.User;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author pp
 * 查看购物车 访问路径为/ViewCart
 */

@WebServlet("/viewCart")
public class ViewCartServlet extends HttpServlet {
    private CartService cartService = new CartService();
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Cart cart = new Cart();
            cart.setUserName(user.getUsername());
            ArrayList<CartItem> list = cartService.getCartItemsByUserName(user.getUsername());
            cart.setNumberOfItems(list.size());
            cart.setCartItems(list);
            session.setAttribute("cart",cart);
            req.getRequestDispatcher(VIEW_CART).forward(req,resp);
        }
    }
}