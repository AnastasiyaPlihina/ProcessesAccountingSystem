package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("RP")
public class RepairProcess extends AbstractProcess {
    private String description;

    public RepairProcess() {
    }

    public RepairProcess(Equipment equipment) {
        super(equipment);
    }

    public RepairProcess(Equipment equipment, String description) {
        super(equipment);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
