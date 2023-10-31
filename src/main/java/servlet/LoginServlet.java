package servlet;

import domain.User;
import service.AccountService;

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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String LOGIN = "/WEB-INF/jsp/account/login.jsp";
    AccountService accountService = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        if (account == null || password == null) {
            request.getRequestDispatcher(LOGIN).forward(request, response);
            return;
        }
        //验证码校验：
        String checkCode=request.getParameter("checkCode");
        HttpSession session=request.getSession();
        String sessionCode=(String)session.getAttribute("CHECK_CODE_KEY");
        //校验验证码
        if (!checkCode.equals(sessionCode))
        {
            request.setAttribute("errorMsg","1");
            request.getRequestDispatcher(LOGIN).forward(request, response);
            return;
        }

        User user = accountService.getAccount(account, password);

        if (user == null) {
            request.setAttribute("errorMsg","2");
            request.getRequestDispatcher(LOGIN).forward(request, response);
            return;
        }
        else {
            session.setAttribute("user", user);
            request.getRequestDispatcher(MAIN).forward(request, response);
        }

    }
}
