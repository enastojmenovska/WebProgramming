package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket_orders")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String movieTitle;
    //private String clientName;
    //private String clientAddress;
    @ManyToOne
    private User user;
    @ManyToOne
    private Movie movie;
    private Long numberOfTickets;
    private LocalDateTime dateCreated;

    public TicketOrder(User user, Movie movie, Long numberOfTickets, LocalDateTime dateCreated) {
        //this.id= (long)(Math.random()*1000);
        this.user = user;
        this.movie = movie;
        this.numberOfTickets = numberOfTickets;
        this.dateCreated = dateCreated;
    }

    public TicketOrder() {

    }
}
