package by.tms.diploma.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class EquipmentLog extends AbstractEntity {
    @OneToOne
    private Process process;
    @OneToOne
    private User user;

    public EquipmentLog() {
    }

    public EquipmentLog(Process process, User user) {
        this.process = process;
        this.user = user;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
