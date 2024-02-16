package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    List<Movie> filterMovies(String text, double rate);
    Optional<Movie> save(String title, String summary, double rating, Production production);
    Optional<Movie> findMovieById(long id);
    void delete(long id);
    Optional<Movie> findByTitle(String title);
    Optional<Movie> edit(Long id, String movieTite, String summary, double rating, long idProduction);
}
