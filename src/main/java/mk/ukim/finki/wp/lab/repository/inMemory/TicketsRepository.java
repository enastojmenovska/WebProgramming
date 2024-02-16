package mk.ukim.finki.wp.lab.repository.inMemory;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TicketsRepository{
    private List<TicketOrder> ticketOrders=new ArrayList<>();

    public void addTicketOrder(TicketOrder ticketOrder){
        ticketOrders.add(ticketOrder);
    }

    public List<TicketOrder> listOrders(){
        return ticketOrders;
    }

    public List<TicketOrder> filterOrders(String name, int numTickets){
        /*return ticketOrders.stream().filter(
                o->o.getMovieTitle().contains(name) && o.getNumberOfTickets()>=numTickets
        ).collect(Collectors.toList());*/
        return ticketOrders.stream().filter(o->o.getMovie().getTitle().contains(name)
        && o.getNumberOfTickets()>=numTickets).collect(Collectors.toList());
    }
    public void deleteOrder(Long id){
        ticketOrders.removeIf(o->o.getId().equals(id));
    }

    public Optional<TicketOrder> findOrderById(Long id){
        return ticketOrders.stream().filter(o->o.getId().equals(id)).findFirst();
    }

    public Optional<TicketOrder> save(Long id, Movie movie, long numTickets, User user, LocalDateTime dateCreated){
        //TicketOrder order=new TicketOrder(movieTitle,clientName,clientAddess,numTickets);
        TicketOrder order=new TicketOrder(user,movie,numTickets,dateCreated);
        ticketOrders.removeIf(o->o.getId().equals(id));
        ticketOrders.add(order);
        return Optional.of(order);
    }
}
