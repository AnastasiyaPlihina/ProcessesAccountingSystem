package by.tms.diploma.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "P_TYPE")
@Table(name = "process")
public abstract class AbstractProcess extends AbstractEntity {
    private LocalDateTime processStart;
    private LocalDateTime processEnd;

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

}
