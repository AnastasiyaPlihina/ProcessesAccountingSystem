package by.tms.diploma.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Department extends AbstractEntity {
private String name;
@OneToMany
private List<User> employees;
@OneToMany
private List<Equipment> equipmentList;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
