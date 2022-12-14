package by.tms.diploma.web;

import by.tms.diploma.dto.AbstractProcessDto;
import by.tms.diploma.entity.AbstractProcess;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.entity.Role;
import by.tms.diploma.entity.User;
import by.tms.diploma.repository.DepartmentRepository;
import by.tms.diploma.service.EquipmentService;
import by.tms.diploma.service.ProcessService;
import by.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class WebController {

    private final UserService userService;
    private final EquipmentService equipmentService;
    private final ProcessService processService;

    public WebController(UserService userService, EquipmentService equipmentService, ProcessService processService) {
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.processService = processService;
    }

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

    @GetMapping("/selectEquipment")
    public String selectEquipment(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        List<Equipment> equipmentList;
        if (user.getAuthorities().contains(Role.AUDITOR)) {
            equipmentList = equipmentService.findAllEquipment();
        } else {
            long departmentId = user.getDepartment().getId();
            equipmentList = equipmentService.findEquipmentOfDepartment(departmentId);
        }
        model.addAttribute("equipmentList", equipmentList);
        return "selectEquipment";
    }

    @GetMapping("/equipmentLog")
    public String showEquipmentLog(String equipmentQr, Model model) {
        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(equipmentQr);
        equipmentByQrCode.ifPresent(equipment -> model.addAttribute("equipment", equipment));
        List<AbstractProcessDto> processListByEquipment = processService.findProcessListByEquipment(equipmentQr);
        model.addAttribute("processList", processListByEquipment);
        return "equipmentLog";
    }

}
