package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RP")
public class RepairProcess extends AbstractProcess {
    private String rp;

    public RepairProcess() {
    }

    public RepairProcess(Equipment equipment) {
        super(equipment);
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }
}
