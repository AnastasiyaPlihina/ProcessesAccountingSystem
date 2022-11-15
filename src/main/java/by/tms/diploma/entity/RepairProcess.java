package by.tms.diploma.entity;

import javax.persistence.Entity;

@Entity
public class RepairProcess extends AbstractProcess {
    private String rp;

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }
}
