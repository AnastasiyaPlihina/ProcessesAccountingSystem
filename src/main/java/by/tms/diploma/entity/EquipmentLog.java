package by.tms.diploma.entity;

import java.time.LocalDateTime;

public class EquipmentLog extends AbstractEntity {

    private Process process;

    private User user;

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
