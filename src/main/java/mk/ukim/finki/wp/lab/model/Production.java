package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productions")
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String address;

    public Production(String name, String country, String address) {
        //this.id= (long)(Math.random()*1000);
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public Production() {

    }
}
