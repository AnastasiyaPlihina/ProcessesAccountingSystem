package by.tms.diploma.service;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.entity.ObjectStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EquipmentServiceTest {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EquipmentService equipmentService;

    @Test
    void findFreeEquipmentOfDepartment() {
        Department department = new Department("Department");
        Optional<Department> department1 = departmentService.saveDepartment(department);
        Equipment equipment = new Equipment("name", "qr", "internalCode", "inventoryNumber", "technicalCharacteristic", department1.get(), LocalDate.now(), LocalDate.now(), ObjectStatus.ACTIVE);
        Optional<Equipment> equipment1 = equipmentService.saveEquipment(equipment);
        Equipment equipment2 = new Equipment("name1", "qr1", "internalCode1", "inventoryNumber1", "technicalCharacteristic1", department1.get(), LocalDate.now(), LocalDate.now(), ObjectStatus.ACTIVE);
        Optional<Equipment> equipment3 = equipmentService.saveEquipment(equipment2);
        equipment1.get().setProcess(true);
        equipmentService.updateEquipment(equipment1.get());
        List<Equipment> freeEquipmentOfDepartment = equipmentService.findFreeEquipmentOfDepartment(department1.get().getId());
        assertTrue(freeEquipmentOfDepartment.size() == 1);
    }
}