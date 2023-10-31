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
@WebServlet("/NewAccount")
public class NewAccountServlet
        extends HttpServlet {
    private static final String LOGIN = "/WEB-INF/jsp/account/login.jsp";
    private static final String REGISTER = "/WEB-INF/jsp/account/register.jsp";

    private User user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验：
        String checkCode=request.getParameter("checkCode");
        HttpSession session=request.getSession();
        String sessionCode=(String)session.getAttribute("CHECK_CODE_KEY");
        //校验验证码
        if (!checkCode.equals(sessionCode))
        {
            request.setAttribute("errorMsg","1");
            request.getRequestDispatcher(REGISTER).forward(request, response);
            return;
        }
        String username = request.getParameter("account");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");
        String languages = request.getParameter("languagePreference");
        String categories = request.getParameter("favouriteCategoryId");

        boolean listOption;
        if(request.getParameter("listOption") != null){
            listOption =  true;
        }else{
            listOption = false;
        }

        boolean bannerOption;
        if(request.getParameter("bannerOption") != null){
            bannerOption =  true;
        }else{
            bannerOption = false;
        }

        if(password.equals(repeatedPassword)){
            user = new User();

            user.setUsername(username);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress1(address1);
            user.setAddress2(address2);
            user.setCity(city);
            user.setState(state);
            user.setZip(zip);
            user.setCountry(country);
            user.setLanguagePreference(languages);
            user.setFavouriteCategoryId(categories);
            user.setListOption(listOption);
            user.setBannerOption(bannerOption);

            AccountService accountService = new AccountService();
            int res=accountService.insertAccount(user);
            //账号已经存在
            if(res==1)
            {
                request.setAttribute("errorMsg","3");
                request.getRequestDispatcher(REGISTER).forward(request, response);
                return;
            }
            //系统异常
            if(res==2)
            {
                request.setAttribute("errorMsg","4");
                request.getRequestDispatcher(REGISTER).forward(request, response);
                return ;
            }
            request.getRequestDispatcher(LOGIN).forward(request,response);
        } else {
            request.setAttribute("errorMsg","2");
            request.getRequestDispatcher(REGISTER).forward(request, response);
            return;
        }

    }
}
