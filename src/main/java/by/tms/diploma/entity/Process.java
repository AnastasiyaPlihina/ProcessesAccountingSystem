package by.tms.diploma.entity;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Entity
public abstract class Process extends AbstractEntity {
    private LocalDateTime start;
    private LocalDateTime end;
}
