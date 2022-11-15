package by.tms.diploma.entity;

import javax.persistence.Entity;

@Entity
public class MaintenanceService extends AbstractProcess {
    private String ms;

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }
}
