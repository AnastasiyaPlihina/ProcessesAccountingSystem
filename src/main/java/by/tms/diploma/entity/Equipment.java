package by.tms.diploma.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("EQ")
public class Equipment extends AbstractControlObject {
    private LocalDate lastMaintenanceServiceDate;
    public Equipment() {
    }

    public Equipment(String name, String qrCode, String internalCode, String technicalCharacteristic, Department department, LocalDate lastQualificationDate, ObjectStatus status, boolean isProcess, LocalDate lastMaintenanceServiceDate) {
        super(name, qrCode, internalCode, technicalCharacteristic, department, lastQualificationDate, status, isProcess);
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
    }

    public LocalDate getLastMaintenanceServiceDate() {
        return lastMaintenanceServiceDate;
    }

    public void setLastMaintenanceServiceDate(LocalDate lastMaintenanceServiceDate) {
        this.lastMaintenanceServiceDate = lastMaintenanceServiceDate;
    }


}
