package mk.ukim.finki.wp.lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductionNotFoundException extends RuntimeException {
    public ProductionNotFoundException(long idProduction) {
        super(String.format("Production with id: %d was not found", idProduction));
    }
}
