package by.tms.diploma.web;

import by.tms.diploma.entity.Admin;
import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Employee;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.repository.DepartmentRepository;
import by.tms.diploma.service.EquipmentService;
import by.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class WebController {
    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public String startPage(Model model) {
        model.addAttribute("equipmentList", equipmentService.findAllEquipment());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        userService.saveSuperAdmin();
        return "login";
    }

//    @PostMapping("/authorisation")
//    public String authorisation(String userRole, Model model) {
//        if (userRole.equals("admin")) {
//            model.addAttribute("admin", new Admin());
//            return "admin/authorisation";
//        } else {
//            List<Department> departmentList = departmentRepository.findAll();
//            model.addAttribute("departmentList", departmentList);
//            return "employee/authorisation";
//        }
//    }

    @PostMapping("/equipmentInfo")
    public String showEquipmentInfo(String equipmentQr, Model model) {
        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(equipmentQr);
        equipmentByQrCode.ifPresent(equipment -> model.addAttribute("equipment", equipment));
        return "equipmentInfo";
    }

//    @GetMapping("/logout")
//    public String logout(HttpSession httpSession) {
//        httpSession.invalidate();
//        return "redirect:/";
//    }
}
