package by.tms.diploma.entity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class CleaningProcess extends AbstractProcess {
    private String cp;

    public CleaningProcess() {
    }

    public CleaningProcess(LocalDateTime start, LocalDateTime end, String cp) {
        super(start, end);
        this.cp = cp;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
