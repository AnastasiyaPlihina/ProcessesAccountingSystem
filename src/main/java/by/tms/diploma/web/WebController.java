package by.tms.diploma.web;

import by.tms.diploma.entity.Admin;
import by.tms.diploma.entity.Employee;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.service.EquipmentService;
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
    private EquipmentService equipmentService;

    @GetMapping
    public String startPage(Model model) {
        model.addAttribute("equipmentList", equipmentService.findAllEquipment());
        return "startPage";
    }

    @PostMapping("/authorisation")
    public String authorisation(String userRole, Model model) {
        if (userRole.equals("admin")) {
            model.addAttribute("admin", new Admin());
            return "admin/authorisation";
        } else {
            model.addAttribute("user", new Employee());
            return "user/authorisation";
        }
    }

    @PostMapping("/equipmentInfo")
    public String showEquipmentInfo(String equipmentQr, Model model) {
        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(equipmentQr);
        equipmentByQrCode.ifPresent(equipment -> model.addAttribute("equipment", equipment));
        return "equipmentInfo";

    }
}
