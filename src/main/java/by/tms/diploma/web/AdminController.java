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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
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
        List<Department> departments = new ArrayList<>();
        if (!user.getAuthorities().contains(Role.ADMIN)) {
            departments.add(user.getDepartment());
        } else {
            departments = departmentService.findAllDepartments();
        }
        model.addAttribute("departments", departments);
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
        model.addAttribute("userDto", new UserDto());
        String username = request.getRemoteUser();
        User currentUser = userService.findUserByUsername(username).get();
        List<Department> departments = new ArrayList<>();
        if (!currentUser.getAuthorities().contains(Role.ADMIN)) {
            departments.add(currentUser.getDepartment());
        } else {
            departments = departmentService.findAllDepartments();
        }
        model.addAttribute("departments", departments);
        return "admin/addEmployee";
    }

    @PostMapping("/{id}/deleteEmployee")
    public String deleteEmployee(@PathVariable long id, HttpServletRequest request, Model model) {
        userService.deleteEmployee(id);
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

    @GetMapping("/addEquipment")
    public String addEquipment(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        List<Department> departments = new ArrayList<>();
        if (!user.getAuthorities().contains(Role.ADMIN)) {
            departments.add(user.getDepartment());
        } else {
            departments = departmentService.findAllDepartments();
        }
        model.addAttribute("departments", departments);
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
        model.addAttribute("equipmentDto", new EquipmentDto());
        String username = request.getRemoteUser();
        User currentUser = userService.findUserByUsername(username).get();
        List<Department> departments = new ArrayList<>();
        if (!currentUser.getAuthorities().contains(Role.ADMIN)) {
            departments.add(currentUser.getDepartment());
        } else {
            departments = departmentService.findAllDepartments();
        }
        model.addAttribute("departments", departments);
        return "admin/addEquipment";
    }
    @PostMapping("/{id}/deleteEquipment")
    public String deleteEquipment(@PathVariable long id, HttpServletRequest request, Model model) {
        equipmentService.deleteEquipment(id);
        String username = request.getRemoteUser();
        User user = userService.findUserByUsername(username).get();
        List<Equipment> equipmentList;
        if(user.getAuthorities().contains(Role.SERVICE_ENGINEER)) {
            equipmentList = equipmentService.findAllEquipment();
        } else {
            long departmentId = user.getDepartment().getId();
            equipmentList = equipmentService.findEquipmentOfDepartment(departmentId);
        }
        model.addAttribute("equipmentList", equipmentList);
        return "employee/equipmentList";
    }
}
