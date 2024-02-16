package mk.ukim.finki.wp.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketOrderNotFoundException extends RuntimeException {
    public TicketOrderNotFoundException(Long ticketOrderId) {
        super(String.format("Ticket order with id: %d was not found", ticketOrderId));
    }
}
