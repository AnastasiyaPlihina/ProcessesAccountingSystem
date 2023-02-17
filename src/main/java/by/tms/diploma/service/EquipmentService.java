package by.tms.diploma.service;

import by.tms.diploma.entity.AbstractControlObject;
import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.exception.EquipmentNotFoundException;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.repository.ControlObjectRepository;
import by.tms.diploma.repository.EquipmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentService {
    private static final Logger logger = LogManager.getLogger(EquipmentService.class);

    private final ControlObjectRepository controlObjectRepository;

    private final DepartmentService departmentService;

    public EquipmentService(ControlObjectRepository controlObjectRepository, DepartmentService departmentService) {
        this.controlObjectRepository = controlObjectRepository;
        this.departmentService = departmentService;
    }

    public Optional<Equipment> saveEquipment(Equipment equipment) {
        if(!controlObjectRepository.existsById(equipment.getId())) {
            Equipment saveEquipment = controlObjectRepository.save(equipment);
            departmentService.updateDepartmentWithEquipment(saveEquipment.getDepartment().getId(), saveEquipment);
            logger.info("save equipment " + saveEquipment.getInternalCode());
            return Optional.of(saveEquipment);
        } else {
            throw new SaveException();
        }
    }
    public Optional<Equipment> updateEquipment(Equipment equipment) {
        if(controlObjectRepository.existsById(equipment.getId())) {
            Equipment updateEquipment = controlObjectRepository.save(equipment);
            logger.info("update equipment " + updateEquipment.getInternalCode());
            return Optional.of(updateEquipment);
        } else {
            throw new EquipmentNotFoundException();
        }
    }

    public List<Equipment> findAllEquipment() {
        return controlObjectRepository.findAllEquipment();
    }


    public Optional<Equipment> findEquipmentByQrCode(String equipmentQrCode) {
        return controlObjectRepository.findEquipmentByQrCode(equipmentQrCode);
    }

    public List<Equipment> findEquipmentOfDepartment(long departmentId) {
        Optional<Department> departmentById = departmentService.findDepartmentById(departmentId);
        return departmentById.get().getEquipmentList();
    }
    public List<Equipment> findListOfInternalCodes(List<String> equipmentQrCodeList) {
       return equipmentQrCodeList.stream().map(s -> controlObjectRepository.findEquipmentByQrCode(s).get()).toList();
    }

    public List<Equipment> findFreeEquipmentOfDepartment(long departmentId) {
        List<Equipment> equipmentOfDepartment = findEquipmentOfDepartment(departmentId);
        return equipmentOfDepartment.stream().filter(equipment -> !equipment.isProcess()).toList();
    }

    public void deleteEquipment(long id) {
        Optional<AbstractControlObject> equipmentById = controlObjectRepository.findById(id);
        controlObjectRepository.deleteById(id);
        Department department = equipmentById.get().getDepartment();
        department.getEquipmentList().remove(equipmentById.get());
        departmentService.updateDepartment(department);
    }
}

