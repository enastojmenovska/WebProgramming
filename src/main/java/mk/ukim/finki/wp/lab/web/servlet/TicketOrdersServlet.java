package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ticket-orders",urlPatterns = "/servlet/tickets")
public class TicketOrdersServlet extends HttpServlet {
    private SpringTemplateEngine springTemplateEngine;
    private TicketOrderService ticketOrderService;

    public TicketOrdersServlet(SpringTemplateEngine springTemplateEngine, TicketOrderService ticketOrderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context=new WebContext(webExchange);
        context.setVariable("orders",ticketOrderService.listAllOrders());
        springTemplateEngine.process("listTicketOrders.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieTitleFilter=req.getParameter("movieTitleFilter");
        int numTicketsFilter=Integer.parseInt(req.getParameter("numTicketsFilter"));

        IWebExchange webExchange= JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context=new WebContext(webExchange);
        context.setVariable("orders",ticketOrderService.listFilteredOrders(movieTitleFilter,numTicketsFilter));
        springTemplateEngine.process("listTicketOrders.html",context,resp.getWriter());

    }
}
