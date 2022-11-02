package by.tms.diploma.web;

import by.tms.diploma.entity.Admin;
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
}
