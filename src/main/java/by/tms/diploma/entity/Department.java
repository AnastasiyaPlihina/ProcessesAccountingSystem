package by.tms.diploma.entity;

import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Department extends AbstractEntity {
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]{3,16}")
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @CollectionTable(name = "department_employees")
    private List<User> employees;
    @OneToMany
    @CollectionTable(name = "department_equipment_list")
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

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                ", equipmentList=" + equipmentList +
                '}';
    }
}
