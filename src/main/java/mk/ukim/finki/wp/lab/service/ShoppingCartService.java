package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService {
    List<TicketOrder> listAllTicketOrdersInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addTicketOrderToShoppingCart(String username, Long ticketOrderId);
    List<TicketOrder> filterTicketOrdersByDateBetween(String username, LocalDateTime from, LocalDateTime to);
}