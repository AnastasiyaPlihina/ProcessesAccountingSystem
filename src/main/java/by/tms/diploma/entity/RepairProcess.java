package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("RP")
public class RepairProcess extends AbstractProcess {
    private String description;

    public RepairProcess() {
    }

    public RepairProcess(List<Equipment> equipment, User employee, String description) {
        super(equipment, employee);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
