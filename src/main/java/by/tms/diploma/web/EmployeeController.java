package by.tms.diploma.web;

import by.tms.diploma.dto.CleaningProcessDto;
import by.tms.diploma.dto.ProcessDto;
import by.tms.diploma.entity.*;
import by.tms.diploma.repository.DepartmentRepository;
import by.tms.diploma.service.DepartmentService;
import by.tms.diploma.service.EquipmentService;
import by.tms.diploma.service.ProcessService;
import by.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ProcessService processService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/showEquipmentList")
    public String showEquipmentList(HttpServletRequest request, Model model) {
        String username = request.getRemoteUser();
        Optional<Department> departmentById = departmentService.findDepartmentById(userService.findUserByUsername(username).get().getDepartment().getId());
        List<Equipment> equipmentOfDepartment = equipmentService.findEquipmentOfDepartment(departmentById.get().getId());
        model.addAttribute("equipmentList", equipmentOfDepartment);
        return "employee/equipmentList";
    }

    @GetMapping("/selectProcess")
    public String selectProcess(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        long departmentId = userService.findUserByUsername(username).get().getDepartment().getId();
        List<Equipment> equipmentOfDepartment = equipmentService.findEquipmentOfDepartment(departmentId);
        model.addAttribute("equipmentList", equipmentOfDepartment);
        model.addAttribute("processDto", new ProcessDto());
        return "employee/startProcess";
    }

    @PostMapping("/selectProcess")
    public String selectProcess(@ModelAttribute ProcessDto processDto, HttpSession httpSession) {
        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(processDto.getEquipmentQrCode());
        httpSession.setAttribute("equipment", equipmentByQrCode.get());
        return "redirect:/employee/" + processDto.getProcessType();
    }

    @GetMapping("/cleaning")
    public String cleaningProcess(@ModelAttribute CleaningProcessDto cleaningProcessDto, HttpSession httpSession, Model model) {
        Equipment equipment = (Equipment) httpSession.getAttribute("equipment");
        model.addAttribute("equipment", equipment);
        return "process/cleaning";
    }

    @PostMapping("/cleaning")
    public String cleaningProcess(@ModelAttribute CleaningProcessDto cleaningProcessDto, Model model, HttpSession httpSession, HttpServletRequest request) {
        Equipment equipment = (Equipment) httpSession.getAttribute("equipment");
        String username = request.getRemoteUser();
        Optional<User> userByUsername = userService.findUserByUsername(username);
        CleaningProcess cleaningProcess = processService.startCleaningProcess(userByUsername.get(), equipment, cleaningProcessDto);
        processService.saveProcess(cleaningProcess);
        httpSession.invalidate();
//        model.addAttribute("cleaningProcessDto", new CleaningProcess());
        return "redirect:/";
    }

}
