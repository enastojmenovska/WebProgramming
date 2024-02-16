package mk.ukim.finki.wp.lab.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Movie;
import mk.ukim.finki.wp.lab.model.Production;
import mk.ukim.finki.wp.lab.service.MovieService;
import mk.ukim.finki.wp.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, ProductionService productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("movies",movieService.listAll());
        model.addAttribute("bodyContent","listMovies");
        return "master-template";
    }

    @PostMapping
    public String filterMovies(HttpServletRequest request,Model model){
        String text=(String) request.getParameter("movieTitleFilter");
        double rate=Double.parseDouble(request.getParameter("movieRate"));
        model.addAttribute("movies",movieService.filterMovies(text,rate));
        model.addAttribute("bodyContent","listMovies");
        return "master-template";
    }
    @GetMapping("/add-form")
    public String getAddMoviePage(Model model){
        List<Production> productionList=productionService.findAll();
        model.addAttribute("productions",productionList);
        model.addAttribute("bodyContent","addMovie");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveMovie(@RequestParam String movieTitle,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam long idProduction){
        Production production=productionService.findById(idProduction).get();
        //movieService.addNewMovie(new Movie(movieTitle,summary,rating,production));
        movieService.save(movieTitle,summary,rating,production);
        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{movieId}")
    public String editMovie(@PathVariable Long movieId,Model model){
        if(movieService.findMovieById(movieId).isPresent()){
            Movie movie= movieService.findMovieById(movieId).get();
            List<Production> productions= productionService.findAll();
            model.addAttribute("movie",movie);
            model.addAttribute("productions",productions);
            model.addAttribute("bodyContent","edit-movie");
            return "master-template";
        }
        return "redirect:/movies?error=MovieNotFound";
    }
    @PostMapping("/edit/{id}")
    public String saveProduct(@PathVariable("id") Long id,
                              @RequestParam String movieTitle,
                              @RequestParam String summary,
                              @RequestParam double rating,
                              @RequestParam long idProduction) {
        this.movieService.edit(id, movieTitle, summary, rating, idProduction);
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.delete(id);
        return "redirect:/movies";
    }
}
