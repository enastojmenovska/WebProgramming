package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.ShoppingCart;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepositoryJpa extends
        JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndCartStatus(User user, ShoppingCartStatus created);
    Optional<ShoppingCart> findByUserUsernameAndCartStatus(String username, ShoppingCartStatus created);
    Optional<ShoppingCart> findByUser_UsernameAndCartStatus(String username, ShoppingCartStatus created);
}
