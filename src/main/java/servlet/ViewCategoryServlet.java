package servlet;

import domain.Category;
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
 * 查看某种商品的信息 根据种类id转发到对应页面
 * @author pp
 */
@WebServlet("/viewCategory")
public class ViewCategoryServlet extends HttpServlet {
    private CatalogService service = new CatalogService();
    private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);
        HttpSession session = req.getSession();
        session.setAttribute("category", category);
        session.setAttribute("productList", productList);
        req.getRequestDispatcher(VIEW_CATEGORY).forward(req, resp);
    }
}