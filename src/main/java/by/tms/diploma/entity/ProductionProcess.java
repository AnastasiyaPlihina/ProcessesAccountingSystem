package by.tms.diploma.entity;

import javax.persistence.Entity;

@Entity
public class ProductionProcess extends AbstractProcess {
    private String pp;

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }
}
