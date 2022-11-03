package by.tms.diploma.web;

import by.tms.diploma.entity.Admin;
import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.User;
import by.tms.diploma.service.DepartmentService;
import by.tms.diploma.service.UserService;
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
    private UserService userService;

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
        return "admin/addDepartment";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("newUser") User user, Model model) {
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        return "admin/addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addEmployee";
        }
        userService.saveUser(user);
        return "admin/addEmployee";
    }
}
