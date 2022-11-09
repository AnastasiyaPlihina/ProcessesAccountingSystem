package by.tms.diploma.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@Entity
public class Equipment extends AbstractEntity {
    private String name;
    private String qrCode;
    private String internalCode;
    private String inventoryNumber;
    private String technicalCharacteristic;
    @ManyToOne
    private Department department;
    private LocalDate lastQualificationDate;
    private LocalDate lastMaintenanceServiceDate;
    @Enumerated(EnumType.STRING)
    private ObjectStatus equipmentStatus;
    private boolean isProcess;

    public Equipment() {
    }

    public Equipment(String name, String qrCode, String internalCode, String inventoryNumber, String technicalCharacteristic, Department department,
                     LocalDate lastQualificationDate, LocalDate lastMaintenanceServiceDate, ObjectStatus equipmentStatus) {
        this.name = name;
        this.qrCode = qrCode;
        this.internalCode = internalCode;
        this.inventoryNumber = inventoryNumber;
        this.technicalCharacteristic = technicalCharacteristic;
        this.department = department;
        this.lastQualificationDate = lastQualificationDate;
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
        this.equipmentStatus = equipmentStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
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

    public ObjectStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(ObjectStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public boolean isProcess() {
        return isProcess;
    }

    public void setProcess(boolean process) {
        isProcess = process;
    }
}
