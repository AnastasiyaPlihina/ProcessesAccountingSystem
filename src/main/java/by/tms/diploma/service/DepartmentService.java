package by.tms.diploma.service;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.entity.User;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.repository.DepartmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DepartmentService {
    private static final Logger logger = LogManager.getLogger(DepartmentService.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    public Optional<Department> saveDepartment(Department department) {
        if(!departmentRepository.existsById(department.getId())) {
            logger.info("add department " + department.getName());
            return Optional.of(departmentRepository.save(department));
        } else {
            throw new SaveException();
        }
    }

    public List<Department> findAllDepartments() {
       return departmentRepository.findAll();
    }
    public Optional<Department> findDepartmentById(long id) {
        return departmentRepository.findById(id);
    }

    public void updateDepartmentWithEmployee(long departmentId, User employee) {
        Optional<Department> departmentById = departmentRepository.findById(departmentId);
        Department department = departmentById.get();
        department.getEmployees().add(employee);
        logger.info("update department " + department.getName());
        departmentRepository.save(department);
    }
    public void updateDepartmentWithEquipment(long departmentId, Equipment equipment) {
        Optional<Department> departmentById = departmentRepository.findById(departmentId);
        Department department = departmentById.get();
        department.getEquipmentList().add(equipment);
        logger.info("update department " + department.getName());
        departmentRepository.save(department);
    }
    public void updateDepartment(Department department) {
        departmentRepository.save(department);
    }
}

