package by.tms.diploma.dto;

import by.tms.diploma.entity.Department;
import by.tms.diploma.entity.ObjectStatus;

public class EquipmentDto {
    private String name;
    private String qrCode;
    private String internalCode;
    private String inventoryNumber;
    private String technicalCharacteristic;
    private Department department;
    private String lastQualificationDate;
    private String lastMaintenanceServiceDate;
    private ObjectStatus equipmentStatus;
    public EquipmentDto() {
    }

    public EquipmentDto(String name, String qrCode, String internalCode, String inventoryNumber, String technicalCharacteristic, Department department,
                        String lastQualificationDate, String lastMaintenanceServiceDate, ObjectStatus equipmentStatus) {
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

    public ObjectStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(ObjectStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }
}
