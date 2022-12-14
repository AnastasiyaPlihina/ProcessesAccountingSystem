package by.tms.diploma.service;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.exception.EquipmentNotFoundException;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.repository.EquipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentService {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    private final EquipmentRepository equipmentRepository;

    private final DepartmentService departmentService;

    public EquipmentService(EquipmentRepository equipmentRepository, DepartmentService departmentService) {
        this.equipmentRepository = equipmentRepository;
        this.departmentService = departmentService;
    }

    public Optional<Equipment> saveEquipment(Equipment equipment) {
        if(!equipmentRepository.existsById(equipment.getId())) {
            Equipment saveEquipment = equipmentRepository.save(equipment);
            departmentService.updateDepartmentWithEquipment(saveEquipment.getDepartment().getId(), saveEquipment);
            logger.info("save equipment " + saveEquipment.getInternalCode());
            return Optional.of(saveEquipment);
        } else {
            throw new SaveException();
        }
    }
    public Optional<Equipment> updateEquipment(Equipment equipment) {
        if(equipmentRepository.existsById(equipment.getId())) {
            Equipment updateEquipment = equipmentRepository.save(equipment);
            logger.info("update equipment " + updateEquipment.getInternalCode());
            return Optional.of(updateEquipment);
        } else {
            throw new EquipmentNotFoundException();
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
    public List<Equipment> findListOfInternalCodes(List<String> equipmentQrCodeList) {
       return equipmentQrCodeList.stream().map(s -> equipmentRepository.findByQrCode(s).get()).toList();
    }

    public List<Equipment> findFreeEquipmentOfDepartment(long departmentId) {
        List<Equipment> equipmentOfDepartment = findEquipmentOfDepartment(departmentId);
        return equipmentOfDepartment.stream().filter(equipment -> !equipment.isProcess()).toList();
    }

    public void deleteEquipment(long id) {
        Optional<Equipment> equipmentById = equipmentRepository.findById(id);
        equipmentRepository.deleteById(id);
        Department department = equipmentById.get().getDepartment();
        department.getEquipmentList().remove(equipmentById.get());
        departmentService.updateDepartment(department);
    }
}

