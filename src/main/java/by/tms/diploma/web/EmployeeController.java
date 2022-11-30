package by.tms.diploma.web;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.repository.DepartmentRepository;
import by.tms.diploma.service.DepartmentService;
import by.tms.diploma.service.EquipmentService;
import by.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentService equipmentService;


    @GetMapping("/startProcess")
    public String startProcess(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        long departmentId = userService.findUserByUsername(username).get().getDepartment().getId();
        List<Equipment> equipmentOfDepartment = equipmentService.findEquipmentOfDepartment(departmentId);
        model.addAttribute("equipmentList", equipmentOfDepartment);
        return "employee/process";
    }

    @PostMapping("/chooseEquipment")
    public String chooseEquipment() {
        return "employee/process";
    }
}
