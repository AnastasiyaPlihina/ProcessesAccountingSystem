package by.tms.diploma.web;

import by.tms.diploma.dto.EmployeeDto;
import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Employee;
import by.tms.diploma.entity.Role;
import by.tms.diploma.repository.DepartmentRepository;
import by.tms.diploma.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/chooseDepartment")
    public String chooseDepartment(long departmentId, Model model) {
        Optional<Department> departmentById = departmentRepository.findById(departmentId);
        Department department = departmentById.get();
        model.addAttribute("department", department);
        model.addAttribute("employees", department.getEmployees());
        model.addAttribute("employeeDto", new EmployeeDto());
        return "employee/authorisation";
    }

    @PostMapping("/authorisation")
    public String authorisation(@ModelAttribute EmployeeDto employeeDto, Model model) {
        Optional<Employee> employee = employeeService.findEmployee(employeeDto);
        System.out.println(employee.get());
        model.addAttribute("employee", employee.get());
        if (employee.get().getRole().equals(Role.HEAD_OF_DEPARTMENT)) {
            return "employee/headPersonalAccount";
        } else {
            return "employee/workerPersonalAccount";
        }
    }
}
