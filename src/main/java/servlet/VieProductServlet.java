package servlet;

import domain.Item;
import domain.Product;
import service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 跳转到对应商品页面
 *
 * @author pp
 */
@WebServlet("/ViewProduct")
public class VieProductServlet extends HttpServlet {
    //private CatalogService=new CatalogService();
    private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        CatalogService service = new CatalogService();
        Product product = service.getProduct(productId);
        //获取商品条目
        List<Item> itemList = service.getItemListByProduct(productId);
        //往session中添加商品和商品条目信息
        HttpSession session = request.getSession();
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);
        //转发到商品信息界面
        request.getRequestDispatcher(VIEW_PRODUCT).forward(request, response);
    }
}