package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("MP")
public class MaintenanceService extends AbstractProcess {

    private String maintenanceInstruction;

    public MaintenanceService() {
    }

    public MaintenanceService(String maintenanceInstruction) {
        this.maintenanceInstruction = maintenanceInstruction;
    }

    public MaintenanceService(List<Equipment> equipment, User employee, String maintenanceInstruction) {
        super(equipment, employee);
        this.maintenanceInstruction = maintenanceInstruction;
    }

    public String getMaintenanceInstruction() {
        return maintenanceInstruction;
    }

    public void setMaintenanceInstruction(String maintenanceInstruction) {
        this.maintenanceInstruction = maintenanceInstruction;
    }
}
