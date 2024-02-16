package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Random;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private double rating;
    @ManyToOne
    private Production production;

    public Movie(String title, String summary, double rating, Production production) {
        //this.id= (long)(Math.random()*1000);
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production=production;
    }

    public Movie() {

    }
}
