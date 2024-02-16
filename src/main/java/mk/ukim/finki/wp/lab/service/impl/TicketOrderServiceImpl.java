package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.inMemory.TicketsRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TicketRepositoryJpa;
import mk.ukim.finki.wp.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    //private final TicketsRepository ticketsRepository;
    private final TicketRepositoryJpa ticketsRepository;

    public TicketOrderServiceImpl(TicketRepositoryJpa ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    @Override
    public TicketOrder placeOrder(User user, Movie movie, int numTickets, LocalDateTime dateCreated) {
        TicketOrder order=new TicketOrder(user,movie,(long)numTickets,dateCreated);
        return order;
    }

    @Override
    public List<TicketOrder> listAllOrders() {
        return ticketsRepository.findAll();
    }

    @Override
    public List<TicketOrder> listFilteredOrders(String name,int numTickets) {
        //return ticketsRepository.filterOrders(name,numTickets);
        return ticketsRepository.findByMovieTitleContainingIgnoreCaseAndNumberOfTicketsGreaterThanEqual(name,numTickets);
    }

    @Override
    public void addTicketOrder(TicketOrder order) {
        ticketsRepository.save(order);
        //ticketsRepository.addTicketOrder(order);
    }

    @Override
    public void deleteOrder(Long id) {
        //ticketsRepository.deleteOrder(id);
        TicketOrder ticketOrder=findByIdOrder(id).get();
        ticketsRepository.delete(ticketOrder);
    }

    @Override
    public Optional<TicketOrder> findByIdOrder(Long id) {
        return ticketsRepository.findById(id);
    }

    @Override
    public Optional<TicketOrder> save(Long id, Movie movie, long numTickets, User user, LocalDateTime dateCreated) {
        //return ticketsRepository.save(id,movie,numTickets,user,dateCreated);
        return Optional.of(ticketsRepository.save(new TicketOrder(user,movie,numTickets,dateCreated)));
    }

    @Override
    public List<TicketOrder> filterByUsernameAndDateBetween(String username, LocalDateTime from, LocalDateTime to) {
        return ticketsRepository.findByUser_UsernameAndDateCreatedBetween(username,from,to);
    }


}
