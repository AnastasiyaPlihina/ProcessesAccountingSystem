package by.tms.diploma.web;

import by.tms.diploma.dto.EquipmentDto;
import by.tms.diploma.dto.UserDto;
import by.tms.diploma.entity.*;
import by.tms.diploma.mapper.EquipmentMapper;
import by.tms.diploma.service.DepartmentService;
import by.tms.diploma.service.EquipmentService;
import by.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DepartmentService departmentService;

    private final UserService userService;

    private final EquipmentService equipmentService;

    private final EquipmentMapper equipmentMapper;

    public AdminController(DepartmentService departmentService, UserService userService, EquipmentService equipmentService, EquipmentMapper equipmentMapper) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.equipmentService = equipmentService;
        this.equipmentMapper = equipmentMapper;
    }
    @GetMapping("/showEmployeeList")
    public String showEmployeeList(Model model) {
        List<User> employeeList = userService.findAllEmployees();
        model.addAttribute("employeeList", employeeList);
        return "employeeList";
    }
    @GetMapping("/addDepartment")
    public String addDepartment(@ModelAttribute Department department) {
        return "admin/addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Valid @ModelAttribute Department department,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addDepartment";
        }
        departmentService.saveDepartment(department);
        model.addAttribute("departmentName", department.getName());
        model.addAttribute("department", new Department());
        return "admin/addDepartment";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        if(!user.getAuthorities().contains(Role.ADMIN)) {
            model.addAttribute("department", user.getDepartment());
        }
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("userDto", new UserDto());
        return "admin/addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "admin/addEmployee";
        }
        Optional<User> user = userService.saveUser(userDto);
        model.addAttribute("user", user.get());
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("userDto", new UserDto());
        String username = request.getRemoteUser();
        User currentUser = userService.findUserByUsername(username).get();
        if(!currentUser.getAuthorities().contains(Role.ADMIN)) {
            model.addAttribute("department", currentUser.getDepartment());
        }
        return "admin/addEmployee";
    }

    @GetMapping("/addEquipment")
    public String addEquipment(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        if(!user.getAuthorities().contains(Role.ADMIN)) {
            model.addAttribute("department", user.getDepartment());
        }
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("equipmentDto", new EquipmentDto());
        return "admin/addEquipment";
    }

    @PostMapping("/addEquipment")
    public String addEquipment(@Valid @ModelAttribute EquipmentDto equipmentDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "admin/addEquipment";
        }
        Equipment equipment = equipmentMapper.convertEquipmentDtoToEquipment(equipmentDto);
        equipmentService.saveEquipment(equipment);
        model.addAttribute("equipment", equipment);
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("equipmentDto", new EquipmentDto());
        String username = request.getRemoteUser();
        User currentUser = userService.findUserByUsername(username).get();
        if(!currentUser.getAuthorities().contains(Role.ADMIN)) {
            model.addAttribute("department", currentUser.getDepartment());
        }
        return "admin/addEquipment";
    }

}
