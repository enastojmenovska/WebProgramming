package mk.ukim.finki.wp.lab.repository.inMemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    public Optional<User> findByUsername(String username){
        return DataHolder.users.stream().filter(
                u->u.getUsername().equals(username)
        ).findFirst();
    }
    public Optional<User> findByUsernameAndPass(String username, String pass){
        return DataHolder.users.stream().filter(
                u->u.getUsername().equals(username) &&
                        u.getPassword().equals(pass)
        ).findFirst();
    }
    public User saveOrUpdate(User user){
        DataHolder.users.removeIf(u->u.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);
        return user;
    }

    public void delete(String username){
        DataHolder.users.removeIf(u->u.getUsername().equals(username));
    }
}
