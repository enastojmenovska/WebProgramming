package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerations.ShoppingCartStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus cartStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;
    @ManyToMany
    private List<TicketOrder> ticketOrders;

    public ShoppingCart(User user) {
        //this.id= (long)(Math.random()*1000);
        this.dateCreated=LocalDateTime.now();
        this.user = user;
        this.ticketOrders=new ArrayList<>();
        this.cartStatus=ShoppingCartStatus.CREATED;
    }

    public ShoppingCart() {

    }
}
