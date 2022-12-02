package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CP")
public class CleaningProcess extends AbstractProcess {
    private String cp;

    public CleaningProcess() {
    }

    public CleaningProcess(Equipment equipment) {
        super(equipment);
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
