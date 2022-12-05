package by.tms.diploma.service;

import by.tms.diploma.dto.UserDto;
import by.tms.diploma.entity.Role;
import by.tms.diploma.entity.User;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.mapper.UserMapper;
import by.tms.diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserMapper userMapper;
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
}
