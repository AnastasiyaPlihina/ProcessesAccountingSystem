package by.tms.diploma.web;

import by.tms.diploma.dto.CleaningProcessDto;
import by.tms.diploma.dto.ProcessDto;
import by.tms.diploma.entity.*;
import by.tms.diploma.repository.DepartmentRepository;
import by.tms.diploma.service.DepartmentService;
import by.tms.diploma.service.EquipmentService;
import by.tms.diploma.service.ProcessService;
import by.tms.diploma.service.UserService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
//        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(processDto.getEquipmentQrCode());
//        httpSession.setAttribute("equipment", equipmentByQrCode.get());
        httpSession.setAttribute("equipmentQrCodeList", processDto.getEquipmentQrCodes());
        return "redirect:/employee/" + processDto.getProcessType();
    }

    @GetMapping("/cleaning")
    public String cleaningProcess(@ModelAttribute CleaningProcessDto cleaningProcessDto, HttpSession httpSession, Model model) {
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        httpSession.removeAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        model.addAttribute("equipmentList", equipmentList);
        return "process/cleaning";
    }

    @PostMapping("/cleaning")
    public String cleaningProcess(@ModelAttribute CleaningProcessDto cleaningProcessDto, Model model, HttpServletRequest request) {
        List<Equipment> equipmentList = (List<Equipment>) model.getAttribute("equipmentList");
        String username = request.getRemoteUser();
        Optional<User> userByUsername = userService.findUserByUsername(username);
        CleaningProcess cleaningProcess = processService.startCleaningProcess(userByUsername.get(), equipmentList, cleaningProcessDto);
        processService.saveProcess(cleaningProcess);
//        model.addAttribute("cleaningProcessDto", new CleaningProcess());
        return "redirect:/";
    }

}
