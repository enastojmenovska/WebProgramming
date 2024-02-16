package mk.ukim.finki.wp.lab.repository.inMemory;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductionRepository {
    /*private List<Production> productions;

    @PostConstruct
    public void init(){
        productions=new ArrayList<>();
        productions.add(new Production("Universal Pictures","USA","Adress 1"));
        productions.add(new Production("Warner Bros","USA","Adress 2"));
        productions.add(new Production("20th Century Studios","USA","Adress 3"));
        productions.add(new Production("Disney","USA","Adress 4"));
        productions.add(new Production("Dream Works","USA","Adress 5"));
    }*/

    public List<Production> findAll(){
        return DataHolder.productions;
    }

    public Production findProductionById(long id){
        return DataHolder.productions.stream()
                .filter(p->p.getId()==id)
                .findFirst().get();
    }
}
