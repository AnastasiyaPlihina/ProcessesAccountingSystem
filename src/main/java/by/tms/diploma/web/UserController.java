package by.tms.diploma.web;

import by.tms.diploma.dto.*;
import by.tms.diploma.entity.*;
import by.tms.diploma.mapper.EquipmentMapper;
import by.tms.diploma.service.DepartmentService;
import by.tms.diploma.service.EquipmentService;
import by.tms.diploma.service.ProcessService;
import by.tms.diploma.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final DepartmentService departmentService;
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final EquipmentMapper equipmentMapper;
    private final ProcessService processService;

    public UserController(DepartmentService departmentService, UserService userService, EquipmentService equipmentService, EquipmentMapper equipmentMapper, ProcessService processService) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.equipmentMapper = equipmentMapper;
        this.processService = processService;
    }

    @GetMapping("/showEmployeeList")
    public String showEmployeeList(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        List<User> employeeList;
        if (user.getAuthorities().contains(Role.ADMIN)) {
            employeeList = userService.findAllEmployees();
        } else {
            employeeList = userService.findEmployeesOfDepartment(user.getDepartment().getId());
        }
        model.addAttribute("employeeList", employeeList);
        return "employeeList";
    }

    @GetMapping("/addDepartment")
    public String addDepartment(@ModelAttribute Department department) {
        return "user/addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Valid @ModelAttribute Department department,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/addDepartment";
        }
        departmentService.saveDepartment(department);
        model.addAttribute("departmentName", department.getName());
        model.addAttribute("department", new Department());
        return "user/addDepartment";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        List<Department> departments = new ArrayList<>();
        if (!user.getAuthorities().contains(Role.ADMIN)) {
            departments.add(user.getDepartment());
        } else {
            departments = departmentService.findAllDepartments();
        }
        model.addAttribute("departments", departments);
        model.addAttribute("userDto", new UserDto());
        return "user/addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User currentUser = userService.findUserByUsername(username).get();
        List<Department> departments = new ArrayList<>();
        if (!currentUser.getAuthorities().contains(Role.ADMIN)) {
            departments.add(currentUser.getDepartment());
        } else {
            departments = departmentService.findAllDepartments();
        }
        model.addAttribute("departments", departments);
        if (bindingResult.hasErrors()) {
            return "user/addEmployee";
        }
        Optional<User> user = userService.saveUser(userDto);
        model.addAttribute("user", user.get());
        model.addAttribute("userDto", new UserDto());
        return "user/addEmployee";
    }

    @PostMapping("/{id}/deleteEmployee")
    public String deleteEmployee(@PathVariable long id, HttpServletRequest request, Model model) {
        userService.deleteEmployee(id);
        String username = request.getRemoteUser();
        List<User> employeeList = userService.findEmployeeList(username);
        model.addAttribute("employeeList", employeeList);
        return "employeeList";
    }

    @GetMapping("/addEquipment")
    public String addEquipment(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User currentUser = userService.findUserByUsername(username).get();
        List<Department> departments = departmentService.findDepartmentList(currentUser);
        model.addAttribute("departments", departments);
        model.addAttribute("equipmentDto", new EquipmentDto());
        return "user/addEquipment";
    }

    @PostMapping("/addEquipment")
    public String addEquipment(@Valid @ModelAttribute EquipmentDto equipmentDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User currentUser = userService.findUserByUsername(username).get();
        List<Department> departments = departmentService.findDepartmentList(currentUser);
        model.addAttribute("departments", departments);
        if (bindingResult.hasErrors()) {
            return "user/addEquipment";
        }
        Equipment equipment = equipmentMapper.convertEquipmentDtoToEquipment(equipmentDto);
        equipmentService.saveEquipment(equipment);
        model.addAttribute("equipment", equipment);
        model.addAttribute("equipmentDto", new EquipmentDto());
        return "user/addEquipment";
    }

    @PostMapping("/{id}/deleteEquipment")
    public String deleteEquipment(@PathVariable long id, HttpServletRequest request, Model model) {
        equipmentService.deleteEquipment(id);
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        List<Equipment> equipmentList;
        if (user.getAuthorities().contains(Role.ADMIN)) {
            equipmentList = equipmentService.findAllEquipment();
        } else {
            long departmentId = user.getDepartment().getId();
            equipmentList = equipmentService.findEquipmentOfDepartment(departmentId);
        }
        model.addAttribute("equipmentList", equipmentList);
        return "equipmentList";
    }

    @GetMapping("/showEquipmentList")
    public String showEquipmentList(HttpServletRequest request, Model model) {
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        List<Equipment> equipmentList;
        if (user.getAuthorities().contains(Role.ADMIN) || user.getAuthorities().contains(Role.SERVICE_ENGINEER)) {
            equipmentList = equipmentService.findAllEquipment();
        } else {
            long departmentId = user.getDepartment().getId();
            equipmentList = equipmentService.findEquipmentOfDepartment(departmentId);
        }
        model.addAttribute("equipmentList", equipmentList);
        return "equipmentList";
    }

    @GetMapping("/selectProcess")
    public String selectProcess(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        List<Equipment> equipmentList;
        if (user.getAuthorities().contains(Role.SERVICE_ENGINEER)) {
            equipmentList = equipmentService.findAllEquipment();
        } else {
            long departmentId = user.getDepartment().getId();
            equipmentList = equipmentService.findFreeEquipmentOfDepartment(departmentId);
        }
        model.addAttribute("equipmentList", equipmentList);
        model.addAttribute("processDto", new ProcessDto());
        return "user/startProcess";
    }

    @PostMapping("/selectProcess")
    public String selectProcess(@Valid @ModelAttribute ProcessDto processDto, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "user/startProcess";
        }
        httpSession.setAttribute("equipmentQrCodeList", processDto.getEquipmentQrCodes());
        return "redirect:/user/" + processDto.getProcessType();
    }

    @GetMapping("/cleaning")
    public String cleaningProcess(@ModelAttribute CleaningProcessDto cleaningProcessDto, HttpSession httpSession, Model model) {
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        model.addAttribute("equipmentList", equipmentList);
        return "process/cleaning";
    }

    @PostMapping("/cleaning")
    public String cleaningProcess(@Valid @ModelAttribute CleaningProcessDto cleaningProcessDto, BindingResult bindingResult, HttpSession httpSession, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "process/cleaning";
        }
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        httpSession.removeAttribute("equipmentQrCodeList");
        String username = request.getRemoteUser();
        Optional<User> userByUsername = userService.findUserByUsername(username);
        assert equipmentList != null;
        CleaningProcess cleaningProcess = processService.startCleaningProcess(userByUsername.get(), equipmentList, cleaningProcessDto);
        Optional<AbstractProcess> process = processService.saveProcess(cleaningProcess);
        model.addAttribute("processType", "cleaning");
        model.addAttribute("process", process.get());
        return "process/inProcess";
    }

    @GetMapping("/production")
    public String productionProcess(@ModelAttribute ProductionProcessDto productionProcessDto, HttpSession httpSession, Model model) {
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        model.addAttribute("equipmentList", equipmentList);
        return "process/production";
    }

    @PostMapping("/production")
    public String productionProcess(@Valid @ModelAttribute ProductionProcessDto productionProcessDto, BindingResult bindingResult, HttpSession httpSession, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "process/production";
        }
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        httpSession.removeAttribute("equipmentQrCodeList");
        String username = request.getRemoteUser();
        Optional<User> userByUsername = userService.findUserByUsername(username);
        assert equipmentList != null;
        ProductionProcess productionProcess = processService.startProductionProcess(userByUsername.get(), equipmentList, productionProcessDto);
        Optional<AbstractProcess> process = processService.saveProcess(productionProcess);
        model.addAttribute("processType", "production");
        model.addAttribute("process", process.get());
        return "process/inProcess";
    }

    @GetMapping("/maintenance")
    public String maintenanceService(@ModelAttribute MaintenanceServiceDto maintenanceServiceDto, HttpSession httpSession, Model model) {
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        model.addAttribute("equipmentList", equipmentList);
        return "process/maintenance";
    }

    @PostMapping("/maintenance")
    public String maintenanceService(@Valid @ModelAttribute MaintenanceServiceDto maintenanceServiceDto,BindingResult bindingResult, HttpSession httpSession, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "process/maintenance";
        }
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        httpSession.removeAttribute("equipmentQrCodeList");
        String username = request.getRemoteUser();
        Optional<User> userByUsername = userService.findUserByUsername(username);
        assert equipmentList != null;
        MaintenanceService maintenanceService = processService.startMaintenanceService(userByUsername.get(), equipmentList, maintenanceServiceDto);
        Optional<AbstractProcess> process = processService.saveProcess(maintenanceService);
        model.addAttribute("processType", "maintenance");
        model.addAttribute("process", process.get());
        return "process/inProcess";
    }

    @GetMapping("/qualification")
    public String qualificationProcess(@ModelAttribute QualificationProcessDto qualificationProcessDto, HttpSession httpSession, Model model) {
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        model.addAttribute("equipmentList", equipmentList);
        return "process/qualification";
    }

    @PostMapping("/qualification")
    public String qualificationProcess(@Valid @ModelAttribute QualificationProcessDto qualificationProcessDto, BindingResult bindingResult, HttpSession httpSession, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "process/qualification";
        }
        List<String> equipmentQrCodeList = (List<String>) httpSession.getAttribute("equipmentQrCodeList");
        List<Equipment> equipmentList = equipmentService.findListOfInternalCodes(equipmentQrCodeList);
        httpSession.removeAttribute("equipmentQrCodeList");
        String username = request.getRemoteUser();
        Optional<User> userByUsername = userService.findUserByUsername(username);
        assert equipmentList != null;
        QualificationProcess qualificationProcess = processService.startQualificationProcess(userByUsername.get(), equipmentList, qualificationProcessDto);
        Optional<AbstractProcess> process = processService.saveProcess(qualificationProcess);
        model.addAttribute("processType", "qualification");
        model.addAttribute("process", process.get());
        return "process/inProcess";
    }

    @GetMapping("/stopProcess")
    public String stopProcess(@RequestParam String equipmentQrCode) {
        processService.stopProcess(equipmentQrCode);
        return "redirect:/";
    }
}
