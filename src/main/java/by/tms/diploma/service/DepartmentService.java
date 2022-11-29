package by.tms.diploma.service;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.Employee;
import by.tms.diploma.entity.Equipment;
import by.tms.diploma.entity.User;
import by.tms.diploma.exception.SaveException;
import by.tms.diploma.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Optional<Department> saveDepartment(Department department) {
        if(!departmentRepository.existsById(department.getId())) {
            return Optional.of(departmentRepository.save(department));
        } else {
            throw new SaveException();
        }
    }

    public List<Department> findAllDepartments() {
       return departmentRepository.findAll();
    }

    public void updateDepartmentWithEmployee(long departmentId, User employee) {
        Optional<Department> departmentById = departmentRepository.findById(departmentId);
        Department department = departmentById.get();
        department.getEmployees().add(employee);
        departmentRepository.save(department);
    }
    public void updateDepartmentWithEquipment(long departmentId, Equipment equipment) {
        Optional<Department> departmentById = departmentRepository.findById(departmentId);
        Department department = departmentById.get();
        department.getEquipmentList().add(equipment);
        departmentRepository.save(department);
    }
}

