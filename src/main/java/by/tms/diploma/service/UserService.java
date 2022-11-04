package by.tms.diploma.service;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.User;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.repository.DepartmentRepository;
import by.tms.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentService departmentService;

    public Optional<User> saveUser(User user) {
        if(!userRepository.existsById(user.getId())) {
            User saveUser = userRepository.save(user);
            departmentService.updateDepartment(saveUser.getDepartment().getId(), saveUser);
            return Optional.of(saveUser);
        } else {
            throw new SaveException();
        }
    }
}
