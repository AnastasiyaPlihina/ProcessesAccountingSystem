package by.tms.diploma.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class EquipmentLog extends AbstractEntity {
    @OneToOne
    private AbstractProcess process;
    @OneToOne
    private Employee user;

    public EquipmentLog() {
    }

    public EquipmentLog(AbstractProcess process, Employee user) {
        this.process = process;
        this.user = user;
    }

    public AbstractProcess getProcess() {
        return process;
    }

    public void setProcess(AbstractProcess process) {
        this.process = process;
    }


    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
}
