package by.tms.diploma.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "P_TYPE")
@Table(name = "process")
public abstract class AbstractProcess extends AbstractEntity {
    private LocalDateTime processStart;
    private LocalDateTime processEnd;
    @ManyToMany
    private List<Equipment> equipment;
    @ManyToOne
    private User employee;

    public AbstractProcess() {
    }

    public AbstractProcess(List<Equipment> equipment, User employee) {
        this.equipment = equipment;
        this.employee = employee;
    }

    public LocalDateTime getProcessStart() {
        return processStart;
    }

    public void setProcessStart(LocalDateTime processStart) {
        this.processStart = processStart;
    }

    public LocalDateTime getProcessEnd() {
        return processEnd;
    }

    public void setProcessEnd(LocalDateTime processEnd) {
        this.processEnd = processEnd;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

}
