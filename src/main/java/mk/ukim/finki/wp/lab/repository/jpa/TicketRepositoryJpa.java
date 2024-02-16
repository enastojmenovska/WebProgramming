package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepositoryJpa extends
        JpaRepository<TicketOrder, Long> {
    List<TicketOrder> findByMovieTitleContainingIgnoreCaseAndNumberOfTicketsGreaterThanEqual(String name, int numTickets);
    //List<TicketOrder> findByDateCreatedBetween(String username,LocalDateTime from,LocalDateTime to);
    List<TicketOrder> findByUser_UsernameAndDateCreatedBetween(String username,LocalDateTime from,LocalDateTime to);
}
