package mk.ukim.finki.wp.lab.repository.inMemory;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    /*private List<Movie> movies=new ArrayList<>();

    @PostConstruct
    public void init(){
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
    }*/

    public List<Movie> findAll(){
        return DataHolder.movies;
    }

    public List<Movie> searchMovies(String text){
        return DataHolder.movies.stream()
                .filter(m->m.getTitle().contains(text) || m.getSummary().contains(text))
                .collect(Collectors.toList());
    }
    public List<Movie> filterMovies(String text, double rate){
        return DataHolder.movies.stream().filter(
                m->m.getTitle().contains(text) && m.getRating()>=rate
        ).collect(Collectors.toList());
    }
    public Optional<Movie> save(String title, String summary, double rating, Production production){
        Movie movie= new Movie(title,summary,rating,production);
        DataHolder.movies.removeIf(m->m.getTitle().equals(title));
        DataHolder.movies.add(movie);
        return Optional.of(movie);
    }

    public Optional<Movie> findById(long id){
        return DataHolder.movies.stream().filter(m->m.getId()==id)
                .findFirst();
    }

    public void delete(long id){
        DataHolder.movies.removeIf(m->m.getId().equals(id));
    }

    public Optional<Movie> findByTitle(String title){
        return DataHolder.movies.stream().filter(m->m.getTitle().equals(title)).findFirst();
    }
}
