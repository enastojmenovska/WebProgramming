package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.MovieService;
import mk.ukim.finki.wp.lab.service.ShoppingCartService;
import mk.ukim.finki.wp.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final ShoppingCartService shoppingCartService;
    private final MovieService movieService;

    public TicketOrderController(TicketOrderService ticketOrderService, ShoppingCartService shoppingCartService, MovieService movieService) {
        this.ticketOrderService = ticketOrderService;
        this.shoppingCartService = shoppingCartService;
        this.movieService = movieService;
    }

    @GetMapping
    public String makeOrderPage(Model model){
        model.addAttribute("movies",movieService.listAll());
        model.addAttribute("bodyContent","makeOrder");
        return "master-template";
    }

    @PostMapping
    public String confirmOrder(@RequestParam("dateTime")
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime, HttpServletRequest req, Model model) {
        long movieId = Long.parseLong(req.getParameter("selectedMovie"));
        Movie movie= movieService.findMovieById(movieId).get();
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));
        //LocalDateTime dateCreated=LocalDateTime.now();

        User user = (User) req.getSession().getAttribute("user");
        TicketOrder order = ticketOrderService.placeOrder(user,movie,numberOfTickets,dateTime);
        ticketOrderService.addTicketOrder(order);
        shoppingCartService.addTicketOrderToShoppingCart(user.getUsername(), order.getId());
        model.addAttribute("clientAddress",req.getRemoteAddr());
        model.addAttribute("order", order);
        model.addAttribute("bodyContent","orderConfirmation");
        return "master-template";
    }
}
