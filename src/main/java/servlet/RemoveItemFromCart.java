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
 */
@WebServlet("/removeItemFromCart")
public class RemoveItemFromCart extends HttpServlet {
    private CartService cartService=new CartService();
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long cartItemId= Long.parseLong(req.getParameter("workingCartItemId"));
        if(cartService.DeleteCartItemByCartItemId(cartItemId))
        {
            //删除成功 更新session数据
            HttpSession session=req.getSession();
            User user = (User) session.getAttribute("user");
            Cart cart = new Cart();
            cart.setUserName(user.getUsername());
            ArrayList<CartItem> list = cartService.getCartItemsByUserName(user.getUsername());
            cart.setNumberOfItems(list.size());
            cart.setCartItems(list);
            session.setAttribute("cart",cart);
            session.setAttribute("cart",cart);
        }
        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }
}