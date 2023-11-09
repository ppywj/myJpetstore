package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pp
 */
@WebServlet("/checkRepeatPwd")
public class checkRepeatPwdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPwd = req.getParameter("newPwd");
        String repeatPwd = req.getParameter("repeatPwd");
        System.out.println(newPwd + " " + repeatPwd);
        if (!repeatPwd.equals(newPwd)) {
            resp.getWriter().write("not");
        }
            else
            resp.getWriter().write("yes");
    }
}