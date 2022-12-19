package by.tms.diploma.service;

import by.tms.diploma.dto.ProductionProcessDto;
import by.tms.diploma.dto.UserDto;
import by.tms.diploma.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProcessServiceTest {
    @Autowired
    ProcessService processService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserService userService;
    @Autowired
    EquipmentService equipmentService;

    @Test
    void startProductionProcess() {
        Department department = new Department("Department");
        Optional<Department> department1 = departmentService.saveDepartment(department);
        UserDto userDto = new UserDto("username", "password", "firstName", "secondName", department1.get(), Set.of(Role.PRODUCTION_WORKER));
        User user = userService.saveUser(userDto).get();
        Equipment equipment = new Equipment("name", "qr", "internalCode", "inventoryNumber", "technicalCharacteristic", department1.get(), LocalDate.now(), LocalDate.now(), ObjectStatus.ACTIVE);
        Optional<Equipment> equipment1 = equipmentService.saveEquipment(equipment);
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(equipment1.get());
        ProductionProcessDto productionProcessDto = new ProductionProcessDto("product", "number");
        ProductionProcess productionProcess = processService.startProductionProcess(user, equipmentList, productionProcessDto);
        assertTrue(productionProcess.getProcessStart() != null);
    }
}