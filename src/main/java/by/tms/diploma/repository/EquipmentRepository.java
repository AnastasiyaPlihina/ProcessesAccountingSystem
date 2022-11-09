package by.tms.diploma.repository;

import by.tms.diploma.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByQrCode(String qrCode);
}
