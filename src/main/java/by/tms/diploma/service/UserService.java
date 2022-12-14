package by.tms.diploma.service;

import by.tms.diploma.dto.UserDto;
import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Role;
import by.tms.diploma.entity.User;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.mapper.UserMapper;
import by.tms.diploma.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    private final UserRepository userRepository;

    private final DepartmentService departmentService;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, DepartmentService departmentService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.departmentService = departmentService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public void saveSuperAdmin() {
        if (!userRepository.existsByUsername("superAdmin")) {
            User superAdmin = new User("superAdmin", encoder.encode("superAdmin"), "Anastasia",
                    "Pligina", Set.of(Role.ADMIN));
            userRepository.save(superAdmin);
        }
    }

    public void saveAuditor() {
        if (!userRepository.existsByUsername("auditor")) {
            User auditor = new User("auditor", encoder.encode("auditor"), Set.of(Role.AUDITOR));
            userRepository.save(auditor);
        }
    }

    public Optional<User> saveUser(UserDto userDto) {
        User user = userMapper.convertUserDtoToUser(userDto);
        if (!userRepository.existsById(user.getId())) {
            user.setPassword(encoder.encode(user.getPassword()));
            User saveUser = userRepository.save(user);
            logger.info(saveUser.getFirstName() + " " + saveUser.getSecondName() + " was saved");
            departmentService.updateDepartmentWithEmployee(saveUser.getDepartment().getId(), saveUser);
            return Optional.of(saveUser);
        } else {
            throw new SaveException();
        }
    }

    public Optional<User> findUserByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public List<User> findAllEmployees() {
       return userRepository.findAllEmployees();
    }
    public List<User> findEmployeesOfDepartment(long departmentId) {
        return userRepository.findByDepartment(departmentId);
    }

    public void deleteEmployee(long id) {
        Optional<User> userById = userRepository.findById(id);
        userRepository.deleteById(id);
        Department department = userById.get().getDepartment();
        department.getEmployees().remove(userById.get());
        departmentService.updateDepartment(department);
    }
}
