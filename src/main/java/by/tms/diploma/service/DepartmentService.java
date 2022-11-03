package by.tms.diploma.service;

import by.tms.diploma.entity.Department;
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
        Optional<Department> departmentByName = departmentRepository.findByName(department.getName());
        if(departmentByName.isEmpty()) {
            return Optional.of(departmentRepository.save(department));
        } else {
            throw new SaveException();
        }
    }

    public List<Department> findAllDepartments() {
       return departmentRepository.findAll();
    }
}

