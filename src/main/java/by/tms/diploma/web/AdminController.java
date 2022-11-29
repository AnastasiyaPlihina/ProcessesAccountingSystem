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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EquipmentMapper equipmentMapper;

    @GetMapping("/addDepartment")
    public String addDepartment(@ModelAttribute("newDepartment") Department department) {
        return "admin/addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Valid @ModelAttribute("newDepartment") Department department,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addDepartment";
        }
        departmentService.saveDepartment(department);
        model.addAttribute("departmentName", department.getName());
        model.addAttribute("newDepartment", new Department());
        return "admin/addDepartment";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("userDto", new UserDto());
        return "admin/addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addEmployee";
        }
        Optional<User> user = userService.saveUser(userDto);
        model.addAttribute("user", user.get());
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("userDto", new UserDto());
        return "admin/addEmployee";
    }

    @GetMapping("/addEquipment")
    public String addEquipment(Model model) {
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("newEquipmentDto", new EquipmentDto());
        return "admin/addEquipment";
    }

    @PostMapping("/addEquipment")
    public String addEquipment(@Valid @ModelAttribute("newEquipmentDto") EquipmentDto equipmentDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addEquipment";
        }
        Equipment equipment = equipmentMapper.convertEquipmentDtoToEquipment(equipmentDto);
        equipmentService.saveEquipment(equipment);
        model.addAttribute("equipment", equipment);
        List<Department> allDepartments = departmentService.findAllDepartments();
        model.addAttribute("departments", allDepartments);
        model.addAttribute("newEquipmentDto", new EquipmentDto());
        return "admin/addEquipment";
    }

}
