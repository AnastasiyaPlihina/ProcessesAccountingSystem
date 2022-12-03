package by.tms.diploma.repository;

import by.tms.diploma.entity.AbstractProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<AbstractProcess, Long> {
}
