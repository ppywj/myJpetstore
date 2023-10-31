package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pp
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String REGISTER = "/WEB-INF/jsp/account/register.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<String> languages = new ArrayList<String>();
        languages.add("English");
        languages.add("Chinese");
        session.setAttribute("languages",languages);

        List<String> categories = new ArrayList<>();
        categories.add("FISH");
        categories.add("DOGS");
        categories.add("REPTILES");
        categories.add("CATS");
        categories.add("BIRDS");
        session.setAttribute("categories",categories);

        request.getRequestDispatcher(REGISTER).forward(request,response);
    }
}
