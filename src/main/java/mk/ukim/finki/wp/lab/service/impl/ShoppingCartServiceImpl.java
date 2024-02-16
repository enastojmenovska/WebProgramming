package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.wp.lab.exceptions.TicketOrderAlreadyInShoppingCartException;
import mk.ukim.finki.wp.lab.exceptions.TicketOrderNotFoundException;
import mk.ukim.finki.wp.lab.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wp.lab.repository.inMemory.ShoppingCartRepository;
import mk.ukim.finki.wp.lab.repository.inMemory.UserRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ShoppingCartRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryJpa;
import mk.ukim.finki.wp.lab.service.ShoppingCartService;
import mk.ukim.finki.wp.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    /*private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;*/
    private final ShoppingCartRepositoryJpa shoppingCartRepository;
    private final UserRepositoryJpa userRepository;
    private final TicketOrderService ticketOrderService;

    public ShoppingCartServiceImpl(ShoppingCartRepositoryJpa shoppingCartRepository, UserRepositoryJpa userRepository, TicketOrderService ticketOrderService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    public List<TicketOrder> listAllTicketOrdersInShoppingCart(Long cartId) {
        if (!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getTicketOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.shoppingCartRepository
                .findByUser_UsernameAndCartStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addTicketOrderToShoppingCart(String username, Long ticketOrderId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        TicketOrder ticketOrder=this.ticketOrderService.findByIdOrder(ticketOrderId)
                .orElseThrow(()-> new TicketOrderNotFoundException(ticketOrderId));
        if (shoppingCart.getTicketOrders()
                .stream().filter(i -> i.getId().equals(ticketOrderId))
                .collect(Collectors.toList()).size() > 0)
            throw new TicketOrderAlreadyInShoppingCartException(ticketOrderId, username);
        shoppingCart.getTicketOrders().add(ticketOrder);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public List<TicketOrder> filterTicketOrdersByDateBetween(String username,LocalDateTime from,LocalDateTime to){
        //List<TicketOrder> ordersFromTo=ticketOrderService.filterByUsernameAndDateBetween(username,from,to);
        ShoppingCart shoppingCart=shoppingCartRepository.findByUser_UsernameAndCartStatus(username,ShoppingCartStatus.CREATED).get();
        return shoppingCart.getTicketOrders().stream().filter(o->o.getDateCreated().isAfter(from)&& o.getDateCreated().isBefore(to))
                .collect(Collectors.toList());
    }
}
