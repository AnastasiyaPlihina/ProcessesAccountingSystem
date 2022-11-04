package by.tms.diploma.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class EquipmentLog extends AbstractEntity {
    @OneToOne
    private Process process;
    @OneToOne
    private Employee user;

    public EquipmentLog() {
    }

    public EquipmentLog(Process process, Employee user) {
        this.process = process;
        this.user = user;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }


    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }
}
