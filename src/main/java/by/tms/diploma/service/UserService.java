package by.tms.diploma.service;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.User;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> saveUser(User user) {
        if(user.getId() == 0) {
            return Optional.of(userRepository.save(user));
        } else {
            throw new SaveException();
        }
    }
}
