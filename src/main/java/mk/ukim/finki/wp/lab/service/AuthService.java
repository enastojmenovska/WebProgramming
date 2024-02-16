package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.UserFullName;

import java.time.LocalDate;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String passwordConfirmation, UserFullName userFullName, LocalDate birthDate);
}
