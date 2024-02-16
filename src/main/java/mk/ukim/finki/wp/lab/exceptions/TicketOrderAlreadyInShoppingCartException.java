package mk.ukim.finki.wp.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class TicketOrderAlreadyInShoppingCartException extends RuntimeException {
    public TicketOrderAlreadyInShoppingCartException(Long ticketOrderId, String username) {
        super(String.format("Ticket order with id: %d already exists in shopping cart for user with username %s", ticketOrderId, username));
    }
}
