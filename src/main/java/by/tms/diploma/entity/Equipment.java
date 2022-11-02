package by.tms.diploma.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Entity
public class Equipment extends AbstractEntity {
    private String name;
    private String internalCode;
    private String inventoryNumber;
    private String technicalCharacteristic;
    @ManyToOne
    private Department department;
    private LocalDate lastQualificationDate;
    private LocalDate lastMaintenanceServiceDate;

    public Equipment() {
    }

    public Equipment(String name, String internalCode, String inventoryNumber, String technicalCharacteristic, Department department, LocalDate lastQualificationDate,
                     LocalDate lastMaintenanceServiceDate) {
        this.name = name;
        this.internalCode = internalCode;
        this.inventoryNumber = inventoryNumber;
        this.technicalCharacteristic = technicalCharacteristic;
        this.department = department;
        this.lastQualificationDate = lastQualificationDate;
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return internalCode;
    }

    public void setCode(String code) {
        this.internalCode = code;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getTechnicalCharacteristic() {
        return technicalCharacteristic;
    }

    public void setTechnicalCharacteristic(String technicalCharacteristic) {
        this.technicalCharacteristic = technicalCharacteristic;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getLastQualificationDate() {
        return lastQualificationDate;
    }

    public void setLastQualificationDate(LocalDate lastQualificationDate) {
        this.lastQualificationDate = lastQualificationDate;
    }

    public LocalDate getLastMaintenanceServiceDate() {
        return lastMaintenanceServiceDate;
    }

    public void setLastMaintenanceServiceDate(LocalDate lastMaintenanceServiceDate) {
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
    }
}
