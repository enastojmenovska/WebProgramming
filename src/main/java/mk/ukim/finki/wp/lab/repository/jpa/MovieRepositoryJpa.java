package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepositoryJpa
    extends JpaRepository<Movie,Long> {
    List<Movie> findByTitleContainingOrSummaryContaining(String title, String summary);
    List<Movie> findByTitleContainingAndRatingGreaterThanEqual(String title, double rating);
    Optional<Movie> findByTitle(String title);
}

