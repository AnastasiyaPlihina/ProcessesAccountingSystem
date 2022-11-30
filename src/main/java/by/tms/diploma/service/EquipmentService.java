package by.tms.diploma.service;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Employee;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private DepartmentService departmentService;

    public Optional<Equipment> saveEquipment(Equipment equipment) {
        if(!equipmentRepository.existsById(equipment.getId())) {
            Equipment saveEquipment = equipmentRepository.save(equipment);
            departmentService.updateDepartmentWithEquipment(saveEquipment.getDepartment().getId(), saveEquipment);
            return Optional.of(saveEquipment);
        } else {
            throw new SaveException();
        }
    }

    public List<Equipment> findAllEquipment() {
        return equipmentRepository.findAll();
    }


    public Optional<Equipment> findEquipmentByQrCode(String equipmentQrCode) {
        return equipmentRepository.findByQrCode(equipmentQrCode);
    }

    public List<Equipment> findEquipmentOfDepartment(long departmentId) {
        Optional<Department> departmentById = departmentService.findDepartmentById(departmentId);
        return departmentById.get().getEquipmentList();
    }
}

