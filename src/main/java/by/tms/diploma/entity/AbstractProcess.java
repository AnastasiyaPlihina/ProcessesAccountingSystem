package by.tms.diploma.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractProcess extends AbstractEntity {
    private LocalDateTime start;
    private LocalDateTime end;

    public AbstractProcess() {
    }

    public AbstractProcess(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
