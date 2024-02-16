package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService {
    List<Production> findAll();
    Optional<Production> findById(long id);
}
