package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.MovieService;
import mk.ukim.finki.wp.lab.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ticket-order",urlPatterns = "/servlet/ticketOrder")
public class TicketOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final TicketOrderService ticketOrderService;

    public TicketOrderServlet(SpringTemplateEngine springTemplateEngine, TicketOrderService ticketOrderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String movieTitle=(String) req.getParameter("selectedMovie");
        int numberOfTickets= Integer.parseInt(req.getParameter("numTickets"));
        LocalDateTime dateCreated=LocalDateTime.now();
        User user = (User) req.getSession().getAttribute("user");
        *//*String clientName=(String) req.getParameter("name");
        String clientSurname=(String) req.getParameter("surname");
        if(clientName==null){
            clientName="";
        }
        if(clientSurname==null){
            clientSurname="";
        }*//*
        TicketOrder order = ticketOrderService.placeOrder(user,movie,numberOfTickets,dateCreated);
        ticketOrderService.addTicketOrder(order);
        IWebExchange webExchange= JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context=new WebContext(webExchange);
        context.setVariable("ipAddressClient",req.getRemoteAddr());
        context.setVariable("clientName",clientName);
        context.setVariable("clientSurname",clientSurname);
        context.setVariable("order",order);
        springTemplateEngine.process("orderConfirmation.html",context,resp.getWriter());*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
