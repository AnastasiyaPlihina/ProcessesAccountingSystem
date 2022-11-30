package by.tms.diploma.web;

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
        userService.saveAuditor();
        return "login";
    }


    @PostMapping("/equipmentInfo")
    public String showEquipmentInfo(String equipmentQr, Model model) {
        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(equipmentQr);
        equipmentByQrCode.ifPresent(equipment -> model.addAttribute("equipment", equipment));
        return "equipmentInfo";
    }

}
