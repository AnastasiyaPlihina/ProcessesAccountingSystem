package by.tms.diploma.service;

import by.tms.diploma.entity.Employee;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentService departmentService;

    public Optional<Employee> saveEmployee(Employee employee) {
        if(!employeeRepository.existsById(employee.getId())) {
            Employee saveEmployee = employeeRepository.save(employee);
            departmentService.updateDepartment(saveEmployee.getDepartment().getId(), saveEmployee);
            return Optional.of(saveEmployee);
        } else {
            throw new SaveException();
        }
    }
}
