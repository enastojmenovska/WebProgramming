package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.repository.jpa.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movies=null;
    public static List<Production> productions=null;
    public static List<ShoppingCart> shoppingCarts = null;
    public static List<User> users=null;
    public static List<TicketOrder> orders=null;

    private final MovieRepositoryJpa movieRepositoryJpa;
    private final ProductionRepositoryJpa productionRepositoryJpa;
    private final ShoppingCartRepositoryJpa shoppingCartRepositoryJpa;
    private final TicketRepositoryJpa ticketRepositoryJpa;
    private final UserRepositoryJpa userRepositoryJpa;

    public DataHolder(MovieRepositoryJpa movieRepositoryJpa, ProductionRepositoryJpa productionRepositoryJpa, ShoppingCartRepositoryJpa shoppingCartRepositoryJpa, TicketRepositoryJpa ticketRepositoryJpa, UserRepositoryJpa userRepositoryJpa) {
        this.movieRepositoryJpa = movieRepositoryJpa;
        this.productionRepositoryJpa = productionRepositoryJpa;
        this.shoppingCartRepositoryJpa = shoppingCartRepositoryJpa;
        this.ticketRepositoryJpa = ticketRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @PostConstruct
    public void init(){
        movies=new ArrayList<>();
        productions=new ArrayList<>();
        shoppingCarts=new ArrayList<>();
        users=new ArrayList<>();
        orders=new ArrayList<>();

        /*
        movies.add(new Movie("Movie1","Summary of Movie1",7.6,new Production("Universal Pictures","USA","Adress 1")));
        movies.add(new Movie("Movie2","Summary of Movie2",8.0,new Production("Warner Bros","USA","Adress 2")));
        movies.add(new Movie("Movie3","Summary of Movie3",9.5,new Production("Disney","USA","Adress 4")));
        movies.add(new Movie("Movie4","Summary of Movie4",5.0,new Production("Dream Works","USA","Adress 5")));
        movies.add(new Movie("Movie5","Summary of Movie5",4.5,new Production("Universal Pictures","USA","Adress 2")));
        movies.add(new Movie("Movie6","Summary of Movie6",2.5,new Production("20th Century Studios","USA","Adress 3")));
        movies.add(new Movie("Movie7","Summary of Movie7",6.9,new Production("Warner Bros","USA","Adress 2")));
        movies.add(new Movie("Movie8","Summary of Movie8",8.8,new Production("Disney","USA","Adress 4")));
        movies.add(new Movie("Movie9","Summary of Movie9",9.6,new Production("20th Century Studios","USA","Adress 3")));
        movies.add(new Movie("Movie10","Summary of Movie10",7.9,new Production("Dream Works","USA","Adress 5")));

        productions.add(new Production("Universal Pictures","USA","Adress 1"));
        productions.add(new Production("Warner Bros","USA","Adress 2"));
        productions.add(new Production("20th Century Studios","USA","Adress 3"));
        productions.add(new Production("Disney","USA","Adress 4"));
        productions.add(new Production("Dream Works","USA","Adress 5"));

        users.add(new User("ena.stojmenovska","Ena","Strojmenovska","es", LocalDate.of(2001,8,19)));
        */
        shoppingCartRepositoryJpa.deleteAll();
        ticketRepositoryJpa.deleteAll();

        if(userRepositoryJpa.count()==0){
            users.add(new User("ena.stojmenovska","Ena","Stojmenovska","es", LocalDate.of(2001,8,19)));
            //users.add(new User("ena.stojmenovska",new UserFullName("Ena","Stojmenovska"),"es",LocalDate.of(2001,8,19)));
            userRepositoryJpa.saveAll(users);
        }
        if(productionRepositoryJpa.count()==0){
            productions.add(new Production("Universal Pictures","USA","100 Universal City Plaza Drive in Universal City, California"));
            productions.add(new Production("Warner Bros","USA","4000 Warner Blvd., Burbank, CA 91522"));
            productions.add(new Production("20th Century Studios","USA","500 S Buena Vista St, Burbank, CA"));
            productions.add(new Production("Disney","USA","1375 East Buena Vista Drive Lake Buena Vista, FL 32830"));
            productions.add(new Production("Dream Works","USA","1000 Flower St, Glendale, CA"));
            productionRepositoryJpa.saveAll(productions);
        }
        if(movieRepositoryJpa.count()==0){
            List<Production> savedProductions = productionRepositoryJpa.findAll();
            movies.add(new Movie("The Martian","The Martian Summary",7.6,savedProductions.get(2)));
            movies.add(new Movie("Harry Potter and the Philosopher's Stone","Harry Potter and the Philosopher's Stone Summary",8.0,savedProductions.get(1)));
            movies.add(new Movie("Home Alone","Home Alone Summary",9.5,savedProductions.get(2)));
            movies.add(new Movie("Sleeping Beauty","Sleeping Beauty Summary",6.0,savedProductions.get(3)));
            movies.add(new Movie("Shrek 1","Shrek 1 Summary",4.5,savedProductions.get(4)));
            movies.add(new Movie("Barbie","Barbie Summary",2.5,savedProductions.get(1)));
            movies.add(new Movie("E.T. the Extra-Terrestrial","E.T Summary",6.9,savedProductions.get(0)));
            movies.add(new Movie("Gone girl","Gone Girl Summary",8.8,savedProductions.get(2)));
            movies.add(new Movie("Madagascar","Madagascar Summary",9.6,savedProductions.get(4)));
            movies.add(new Movie("Peter Pan","Peter Pan Summary",7.9,savedProductions.get(3)));
            movieRepositoryJpa.saveAll(movies);
        }

    }

}
