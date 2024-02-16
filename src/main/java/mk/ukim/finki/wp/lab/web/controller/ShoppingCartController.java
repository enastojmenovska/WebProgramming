package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.ShoppingCartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        User user = (User) req.getSession().getAttribute("user");
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        model.addAttribute("ticketOrders", this.shoppingCartService.listAllTicketOrdersInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent","shopping-cart");
        return "master-template";
    }

    @PostMapping
    public String filterOrdersInShoppingCart(@RequestParam("dateTimeFrom")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeFrom,
                                             @RequestParam("dateTimeTo")
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTimeTo,
                                             HttpServletRequest req,
                                             Model model){
        User user = (User) req.getSession().getAttribute("user");
        List<TicketOrder> filteredTickets=shoppingCartService.filterTicketOrdersByDateBetween(user.getUsername(),dateTimeFrom,dateTimeTo);
        model.addAttribute("ticketOrders",filteredTickets);
        model.addAttribute("bodyContent","shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-product/{id}")
    public String addTicketOrderToShoppingCart(@PathVariable Long ticketOrderId, HttpServletRequest req) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            //metodot addProductToShoppingCart frla dva isklucoci koi pak nasleduvaat od RuntimeException
            ShoppingCart shoppingCart = this.shoppingCartService.addTicketOrderToShoppingCart(user.getUsername(), ticketOrderId);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

}
