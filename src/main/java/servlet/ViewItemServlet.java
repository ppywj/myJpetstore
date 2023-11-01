package servlet;

import domain.Item;
import service.CatalogService;

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
@WebServlet("/ViewItem")
public class ViewItemServlet extends HttpServlet {
    private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
    private CatalogService service = new CatalogService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据商品条目id获取商品条目信息放到session里面 转发到商品信息页面
        String itemId = req.getParameter("itemId");
        Item item = service.getItem(itemId);
        HttpSession session = req.getSession();
        session.setAttribute("item",item);
        req.getRequestDispatcher(VIEW_ITEM).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}