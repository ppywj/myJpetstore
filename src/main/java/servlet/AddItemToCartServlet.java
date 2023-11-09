package servlet;

import domain.CartItem;
import domain.Item;
import domain.User;
import service.CartService;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 添加商品到购物车
 *
 * @author pp
 */
@WebServlet("/AddItemToCart")
public class AddItemToCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/viewCart";
    private CartService service = new CartService();
    private CatalogService catalogService = new CatalogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workingItemId = req.getParameter("workingItemId");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        CartItem cartItem = new CartItem();
        Item item = catalogService.getItem(workingItemId);
        cartItem.setItem(item);
        cartItem.setUsername(user.getUsername());
        cartItem.setTotal(item.getListPrice());
        cartItem.setQuantity(1);
        service.AddCartItemToCart(cartItem);
        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}