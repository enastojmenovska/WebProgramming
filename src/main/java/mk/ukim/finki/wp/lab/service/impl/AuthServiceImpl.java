package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.lab.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.UserFullName;
import mk.ukim.finki.wp.lab.repository.inMemory.UserRepository;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryJpa;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {
    //private final UserRepository userRepository;
    private final UserRepositoryJpa userRepository;

    public AuthServiceImpl(UserRepositoryJpa userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(isBlank(username)||isBlank(password)){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String passwordConfirmation, UserFullName userFullName, LocalDate birthDate) {
        if(isBlank(username)||isBlank(password)){
            throw new InvalidArgumentsException();
        }
        if(!password.equals(passwordConfirmation)){
            throw new PasswordsDoNotMatchException();
        }
        User user= new User(username,userFullName.getName(),userFullName.getSurname(),password,birthDate);
        return userRepository.save(user);
    }
    private boolean isBlank(String string){
        return string== null || string.isEmpty();
    }
}
