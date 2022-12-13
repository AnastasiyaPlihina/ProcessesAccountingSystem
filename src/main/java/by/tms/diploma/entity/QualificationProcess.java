package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("QP")
public class QualificationProcess extends AbstractProcess {
    private String description;

    public QualificationProcess() {
    }

    public QualificationProcess(List<Equipment> equipment, User employee, String description) {
        super(equipment, employee);
        this.description = description;
    }

    public QualificationProcess(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
