package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@Table(name = "cinema_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    //private String name;
    //private String surname;
    @Convert(converter = UserFullNameConverter.class)
    private UserFullName userFullName;
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User(String username, String name, String surname, String password, LocalDate dateOfBirth) {
        //this.id= (long)(Math.random()*1000);
        this.username = username;
        //this.name = name;
        //this.surname = surname;
        //this.userFullName=userFullName;
        UserFullName userFullName=new UserFullName();
        userFullName.setName(name);
        userFullName.setSurname(surname);
        this.userFullName=userFullName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {

    }
}
