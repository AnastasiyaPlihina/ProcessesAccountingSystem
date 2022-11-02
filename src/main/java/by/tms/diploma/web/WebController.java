package by.tms.diploma.web;

import by.tms.diploma.entity.Admin;
import by.tms.diploma.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WebController {
    @GetMapping
    public String startPage() {
        return "startPage";
    }

    @PostMapping("/authorisation")
    public String authorisation(String userRole, Model model) {
        if(userRole.equals("admin")) {
            model.addAttribute("admin", new Admin());
            return "admin/authorisation";
        } else {
            model.addAttribute("user", new User());
            return "user/authorisation";
        }
    }
}
