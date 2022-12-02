package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PP")
public class ProductionProcess extends AbstractProcess {
    private String pp;

    public ProductionProcess() {
    }

    public ProductionProcess(Equipment equipment) {
        super(equipment);
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }
}
