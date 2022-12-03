package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CP")
public class CleaningProcess extends AbstractProcess {
    private String cleaningType;
    private String procedure;

    public CleaningProcess() {
    }

    public CleaningProcess(String cleaningType, String procedure) {
        this.cleaningType = cleaningType;
        this.procedure = procedure;
    }

    public CleaningProcess(Equipment equipment, User employee, String cleaningType, String procedure) {
        super(equipment, employee);
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
