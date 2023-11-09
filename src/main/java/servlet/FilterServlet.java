package servlet;

import domain.User;
import persistence.Imp.logInfoDAOImp;
import service.CatalogService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author pp
 */
@WebFilter("/*")
public class FilterServlet implements Filter {
    private CatalogService service = new CatalogService();
    private logInfoDAOImp log = new logInfoDAOImp();
    //这三个请求直接放行
    private static final String[] excludeUrls1 = new String[]{"/index.html", "/help.html", "/Main", "/register"};
    private static final String[] excludeUrls2 = new String[]{"/login", "/NewAccount", "/viewCategory", "/ViewItem"};
    private static final String[] excludeUrls3 = new String[]{"/removeItemFromCart", "/viewOrderForm", "/viewCart","/editAccount","/editAccountAction","/confirmShip","/confirmOrder","/sinOut","order","viewOrderForm"};

    private void log(int i, HttpServletRequest req, HttpSession session) {
        User user = (User) session.getAttribute("user");
        switch (i) {
            case 0:
                String account = req.getParameter("account");
                if (account != null)
                    log.addLoginRecord(account, new Timestamp(System.currentTimeMillis()), req.getRemoteAddr());
                break;
            case 1:
                String account1 = req.getParameter("account");
                log.addRegisterRecord(account1, req.getRemoteAddr(), new Timestamp(System.currentTimeMillis()));
                break;
            case 2:
                if (user != null)
                    log.addViewCategoryRecord(user.getUsername(), req.getParameter("categoryId"), new Timestamp(System.currentTimeMillis()));
                break;
            case 3:
                String itemId=req.getParameter("itemId");
                if(itemId!=null&&user!=null)
                {
                    String itemName=service.getItem(itemId).getProduct().getName();
                    log.addViewItemRecord(user.getUsername(),itemName,new Timestamp(System.currentTimeMillis()));
                }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        //第一道过滤 直接放行的请求
        String excludeUrl = null;
        String contextPath = req.getContextPath();
        String requestUrl = req.getRequestURI().substring(contextPath.length());
        if (requestUrl == null) {
            resp.sendRedirect(contextPath + "/Main");
        }
        for (int i = 0; i < excludeUrls1.length; i++) {
            excludeUrl = excludeUrls1[i];
            if (requestUrl.startsWith(excludeUrl)) {
                filterChain.doFilter(req, resp);
                return;
            }
        }
        //第二道过滤 需要记录日志的请求
        for (int i = 0; i < excludeUrls2.length; i++) {
            excludeUrl = excludeUrls2[i];
            if (requestUrl.startsWith(excludeUrl)) {
                log(i, req, session);
                break;
            }
        }
        //第三道过滤:如果用户没有登录那么直接跳转到登录界面
        for (int i = 0; i < excludeUrls3.length; i++) {
            excludeUrl = excludeUrls3[i];
            if (requestUrl.startsWith(excludeUrl)) {
                if (session.getAttribute("user") != null) {
                    filterChain.doFilter(req, resp);
                    return;
                }
                else {
                    resp.sendRedirect(contextPath + "/login");
                    return;
                }
            }
        }
        //在其他的就直接放行
        filterChain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }
}