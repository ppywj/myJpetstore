package servlet;


import domain.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {
    private static final String CONFIRMORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String SHIPPINGFORM = "/WEB-INF/jsp/order/ShippingForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cardType = request.getParameter("cardType");
        String creditCard = request.getParameter("creditCard");
        String expiryDate = request.getParameter("expiryDate");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");

        // 修改订单消息
        Order order = (Order)session.getAttribute("order");
        order.setCardType(cardType);
        order.setCreditCard(creditCard);
        order.setExpiryDate(expiryDate);
        order.setBillToFirstName(firstName);
        order.setBillToLastName(lastName);
        order.setShipToFirstName(firstName);
        order.setShipToLastName(lastName);
        order.setBillAddress1(address1);
        order.setBillAddress2(address2);
        order.setShipAddress1(address1);
        order.setShipAddress2(address2);
        order.setBillCity(city);
        order.setShipCity(city);
        order.setBillState(state);
        order.setShipState(state);
        order.setBillZip(zip);
        order.setShipZip(zip);
        order.setBillCountry(country);
        order.setShipCountry(country);

        session.setAttribute("order",order);

        if(request.getParameter("shippingAddressRequired") != null){
            request.getRequestDispatcher(SHIPPINGFORM).forward(request,response);
        }else{
            request.getRequestDispatcher(CONFIRMORDER).forward(request,response);
        }

    }
}
