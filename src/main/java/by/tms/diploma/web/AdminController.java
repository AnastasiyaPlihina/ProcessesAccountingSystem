package by.tms.diploma.web;

import by.tms.diploma.entity.Admin;
import by.tms.diploma.entity.Department;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
    public String addTeacher(@Valid @ModelAttribute("newDepartment") Department department,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/addDepartment";        }

        return "redirect:/admin/personalAccount";
    }
}
