package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketOrderService {
    TicketOrder placeOrder(User user, Movie movie, int numTickets, LocalDateTime dateCreated);
    List<TicketOrder> listAllOrders();
    List<TicketOrder> listFilteredOrders(String name, int numTickets);
    void addTicketOrder(TicketOrder order);
    void deleteOrder(Long id);
    Optional<TicketOrder> findByIdOrder(Long id);
    Optional<TicketOrder> save(Long id, Movie movie, long numTickets, User user, LocalDateTime dateCreated);
    List<TicketOrder> filterByUsernameAndDateBetween(String username,LocalDateTime from,LocalDateTime to);
}
