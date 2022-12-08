package by.tms.diploma.repository;

import by.tms.diploma.entity.AbstractProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessRepository extends JpaRepository<AbstractProcess, Long> {
    AbstractProcess findByEquipment(long equipmentId);

    @Query(value = "select * from process where process_end is null", nativeQuery = true)
    List<AbstractProcess> findUnfinishedProcesses();
}
