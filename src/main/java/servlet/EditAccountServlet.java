package servlet;

import service.AccountService;

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
@WebServlet("/editAccount")
public class EditAccountServlet extends HttpServlet {
    private AccountService service = new AccountService();
    private static final String EDITACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> languages = new ArrayList<String>();
        languages.add("english");
        languages.add("japanese");
        session.setAttribute("languages", languages);

        List<String> categories = new ArrayList<>();
        categories.add("FISH");
        categories.add("DOGS");
        categories.add("REPTILES");
        categories.add("CATS");
        categories.add("BIRDS");
        session.setAttribute("categories", categories);
        request.getRequestDispatcher(EDITACCOUNT).forward(request, response);
    }
}
