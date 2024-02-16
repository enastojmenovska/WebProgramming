package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.MovieService;
import mk.ukim.finki.wp.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketOrdersController {
    private final TicketOrderService orderService;
    private final MovieService movieService;

    public TicketOrdersController(MovieService movieService,TicketOrderService orderService) {
        this.orderService = orderService;
        this.movieService=movieService;
    }

    @GetMapping
    public String getOrders(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("orders",orderService.listAllOrders());
        return "listTicketOrders";
    }

    @PostMapping
    public String filterOrders(HttpServletRequest req, Model model){
        String movieTitleFilter=req.getParameter("movieTitleFilter");
        int numTicketsFilter=Integer.parseInt(req.getParameter("numTicketsFilter"));
        model.addAttribute("orders",orderService.listFilteredOrders(movieTitleFilter,numTicketsFilter));
        return "listTicketOrders";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "redirect:/tickets";
    }

    @GetMapping("/edit-form/{id}")
    public String editOrder(@PathVariable Long id,Model model){
        if(orderService.findByIdOrder(id).isPresent()){
            TicketOrder order = orderService.findByIdOrder(id).get();
            List<Movie> movies= movieService.listAll();
            Movie orderMovie=movieService.findByTitle(order.getMovie().getTitle()).get();
            model.addAttribute("movies",movies);
            model.addAttribute("orderMovie",orderMovie);
            model.addAttribute("order",order);
            model.addAttribute("id",id);
            return "editOrder";
        }
        return "redirect:/tickets?error=OrderNotFound";
    }
    @PostMapping("/update")
    public String updateOrder(@RequestParam long idMovie,
                            @RequestParam long numTickets,
                              //@RequestParam String clientAddress,
                              //@RequestParam String clientName,
                              @RequestParam Long id, HttpServletRequest req){
        Movie movie=movieService.findMovieById(idMovie).get();
        User user = (User) req.getSession().getAttribute("user");
        //orderService.save(id,movie.getTitle(),numTickets,clientName,clientAddress);
        orderService.save(id,movie,numTickets,user, LocalDateTime.now());
        return "redirect:/tickets";
    }
}
