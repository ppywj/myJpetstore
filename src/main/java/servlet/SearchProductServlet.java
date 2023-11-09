package servlet;

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
 * @author pp
 */
@WebServlet("/searchProduct")
public class SearchProductServlet extends HttpServlet {
    private static final String VIEW_SEARCH_PRODUCT = "/WEB-INF/jsp/catalog/SearchProducts.jsp";
    private String keyword;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword = request.getParameter("keyword");

        CatalogService service = new CatalogService();

        List<Product> productList = service.searchProductList(keyword);

        HttpSession session = request.getSession();

        session.setAttribute("productList",productList);

        request.getRequestDispatcher(VIEW_SEARCH_PRODUCT).forward(request,response);

    }
}
