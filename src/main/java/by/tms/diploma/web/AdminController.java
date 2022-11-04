package by.tms.diploma.web;

import by.tms.diploma.entity.Admin;
import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Employee;
import by.tms.diploma.service.DepartmentService;
import by.tms.diploma.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/authorisation")
    public String authorisation(@Valid @ModelAttribute("admin") Admin admin,
                                BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "admin/authorisation";
        }
        httpSession.setAttribute("currentAdmin", admin);
        return "redirect:/admin/personalAccount";
    }

    @GetMapping("/personalAccount")
    public String personalAccount() {
        return "admin/personalAccount";
    }

    @GetMapping("/addDepartment")
    public String addDepartment(@ModelAttribute("newDepartment") Department department) {
        return "admin/addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Valid @ModelAttribute("newDepartment") Department department,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addDepartment";
        }
        departmentService.saveDepartment(department);
        model.addAttribute("departmentName", department.getName());
        model.addAttribute("newDepartment", new Department());
        return "admin/addDepartment";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("newEmployee", new Employee());
        return "admin/addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute("newEmployee") Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addEmployee";
        }
        employeeService.saveEmployee(employee);
        model.addAttribute("employee", employee);
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("newEmployee", new Employee());
        return "admin/addEmployee";
    }
}
