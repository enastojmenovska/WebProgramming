package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.exceptions.MovieNotFoundException;
import mk.ukim.finki.wp.lab.exceptions.ProductionNotFoundException;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.repository.inMemory.MovieRepository;
import mk.ukim.finki.wp.lab.repository.jpa.MovieRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.ProductionRepositoryJpa;
import mk.ukim.finki.wp.lab.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    //private final MovieRepository movieRepository;
    private final MovieRepositoryJpa movieRepository;
    private final ProductionRepositoryJpa productionRepository;
    public MovieServiceImpl(MovieRepositoryJpa movieRepository,ProductionRepositoryJpa productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository=productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.findByTitleContainingOrSummaryContaining(text,text);
    }

    @Override
    public List<Movie> filterMovies(String text, double rate) {
        return movieRepository.findByTitleContainingAndRatingGreaterThanEqual(text,rate);
    }

    @Override
    public Optional<Movie> save(String title, String summary, double rating, Production production) {
        Movie movie=new Movie(title,summary,rating,production);
        //return movieRepository.save(movie);
        return Optional.of(movieRepository.save(movie));
    }

    @Override
    public Optional<Movie> findMovieById(long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }
    @Override
    public Optional<Movie> edit(Long id, String movieTite, String summary, double rating, long idProduction) {
        Movie movie=movieRepository.findById(id).orElseThrow(()->new MovieNotFoundException(id));
        Production production=productionRepository.findById(idProduction).orElseThrow(()->new ProductionNotFoundException(idProduction));
        movie.setTitle(movieTite);
        movie.setSummary(summary);
        movie.setRating(rating);
        movie.setProduction(production);
        movieRepository.save(movie);
        return Optional.of(movie);
    }
}
