package by.tms.diploma.dto;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.ObjectStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EquipmentDto {
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]{3,16}")
    private String name;
    @NotBlank
    @Pattern(regexp = ".{3,16}")
    private String qrCode;
    @NotBlank
    @Pattern(regexp = ".{3,10}")
    private String internalCode;
    @NotBlank
    @Pattern(regexp = "[0-9]{3,10}")
    private String inventoryNumber;
    @NotBlank
    @Pattern(regexp = ".{3,60}")
    private String technicalCharacteristic;
    @NotNull
    private Department department;
    @NotNull
    private String lastQualificationDate;
    @NotNull
    private String lastMaintenanceServiceDate;
    @NotNull
    private ObjectStatus objectStatus;
    public EquipmentDto() {
    }

    public EquipmentDto(String name, String qrCode, String internalCode, String inventoryNumber, String technicalCharacteristic, Department department, String lastQualificationDate, String lastMaintenanceServiceDate, ObjectStatus objectStatus) {
        this.name = name;
        this.qrCode = qrCode;
        this.internalCode = internalCode;
        this.inventoryNumber = inventoryNumber;
        this.technicalCharacteristic = technicalCharacteristic;
        this.department = department;
        this.lastQualificationDate = lastQualificationDate;
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
        this.objectStatus = objectStatus;
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

    public String getLastQualificationDate() {
        return lastQualificationDate;
    }

    public void setLastQualificationDate(String lastQualificationDate) {
        this.lastQualificationDate = lastQualificationDate;
    }

    public String getLastMaintenanceServiceDate() {
        return lastMaintenanceServiceDate;
    }

    public void setLastMaintenanceServiceDate(String lastMaintenanceServiceDate) {
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
    }

    public ObjectStatus getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(ObjectStatus objectStatus) {
        this.objectStatus = objectStatus;
    }
}
