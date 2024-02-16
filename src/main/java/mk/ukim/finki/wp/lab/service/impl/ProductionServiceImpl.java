package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.repository.inMemory.ProductionRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ProductionRepositoryJpa;
import mk.ukim.finki.wp.lab.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepositoryJpa productionRepository;

    @Autowired
    public ProductionServiceImpl(ProductionRepositoryJpa productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Production> findAll() {
        return productionRepository.findAll();
    }

    @Override
    public Optional<Production> findById(long id) {
        return productionRepository.findById(id);
    }
}
