package by.tms.diploma.service;

import by.tms.diploma.dto.UserDto;
import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Role;
import by.tms.diploma.entity.User;
import by.tms.diploma.repository.DepartmentRepository;
import by.tms.diploma.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserRepository userRepository;

    @Test
    void updateDepartmentWithEmployee() {
        Department department = new Department("Department");
        Optional<Department> department1 = departmentService.saveDepartment(department);
        User user = new User("username", "password", "firstName", "secondName", department1.get(), Set.of(Role.HEAD_OF_DEPARTMENT));
        User saveUser = userRepository.save(user);
        departmentService.updateDepartmentWithEmployee(department1.get().getId(), saveUser);
        Optional<Department> departmentById = departmentService.findDepartmentById(department1.get().getId());
        List<User> employees = departmentById.get().getEmployees();
        boolean result = false;
        for (User employee:employees) {
            if(employee.getId() == saveUser.getId()){
                result = true;
                break;
            }
        }
        assertTrue(result);
    }

}