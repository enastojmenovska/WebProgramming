package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "movie-list",urlPatterns = "/servlet/movies")
public class MovieListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final MovieService movieService;

    public MovieListServlet(SpringTemplateEngine springTemplateEngine, MovieService movieService) {
        this.springTemplateEngine = springTemplateEngine;
        this.movieService = movieService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String name=req.getParameter("name");
        String surname=req.getParameter("surname");
        if(name==null){
            name="";
        }
        if(surname==null){
            surname="";
        }*/
        IWebExchange webExchange= JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context=new WebContext(webExchange);
        context.setVariable("movies",movieService.listAll());
        /*context.setVariable("name",name);
        context.setVariable("surname",surname);*/
        springTemplateEngine.process("listMovies.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String clientName=(String) req.getParameter("name");
        String clientSurname=(String) req.getParameter("surname");*/
        String text=(String) req.getParameter("movieTitleFilter");
        double rate=Double.parseDouble(req.getParameter("movieRate"));
        IWebExchange webExchange= JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context=new WebContext(webExchange);
        context.setVariable("movies",movieService.filterMovies(text,rate));
        /*context.setVariable("name",clientName);
        context.setVariable("surname",clientSurname);*/
        springTemplateEngine.process("listMovies.html",context,resp.getWriter());
    }
}
