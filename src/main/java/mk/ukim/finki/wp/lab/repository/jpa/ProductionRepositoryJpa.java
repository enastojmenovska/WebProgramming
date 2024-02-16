package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepositoryJpa
        extends JpaRepository<Production,Long> {
}
