package by.tms.diploma.service;

import by.tms.diploma.dto.UserDto;
import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Role;
import by.tms.diploma.entity.User;
import by.tms.diploma.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentService departmentService;

    @Test
    void saveUser() {
        Department department = new Department("Department");
        departmentService.saveDepartment(department);
        UserDto userDto = new UserDto("username", "password", "firstName", "secondName", department, Set.of(Role.HEAD_OF_DEPARTMENT));
        Optional<User> user = userService.saveUser(userDto);
        assertTrue(userRepository.existsByUsername(user.get().getUsername()));
    }
}