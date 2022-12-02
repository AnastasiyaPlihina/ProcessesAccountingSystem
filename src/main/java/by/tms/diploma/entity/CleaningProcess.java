package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CP")
public class CleaningProcess extends AbstractProcess {
    private String cleaningType;
    private String procedure;

    public CleaningProcess() {
    }

    public CleaningProcess(Equipment equipment) {
        super(equipment);
    }

    public CleaningProcess(Equipment equipment, String cleaningType, String procedure) {
        super(equipment);
        this.cleaningType = cleaningType;
        this.procedure = procedure;
    }

    public String getCleaningType() {
        return cleaningType;
    }

    public void setCleaningType(String cleaningType) {
        this.cleaningType = cleaningType;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
}
