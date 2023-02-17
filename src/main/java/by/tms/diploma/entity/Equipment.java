package by.tms.diploma.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("EQ")
public class Equipment extends AbstractControlObject {
    private String inventoryNumber;
    private LocalDate lastMaintenanceServiceDate;
    public Equipment() {
    }

    public Equipment(String name, String qrCode, String internalCode, String technicalCharacteristic, Department department, LocalDate lastQualificationDate, ObjectStatus objectStatus, boolean isProcess, String inventoryNumber, LocalDate lastMaintenanceServiceDate) {
        super(name, qrCode, internalCode, technicalCharacteristic, department, lastQualificationDate, objectStatus, isProcess);
        this.inventoryNumber = inventoryNumber;
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public LocalDate getLastMaintenanceServiceDate() {
        return lastMaintenanceServiceDate;
    }

    public void setLastMaintenanceServiceDate(LocalDate lastMaintenanceServiceDate) {
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
    }


}
