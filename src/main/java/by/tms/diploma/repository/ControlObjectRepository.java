package by.tms.diploma.repository;

import by.tms.diploma.entity.AbstractControlObject;
import by.tms.diploma.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ControlObjectRepository extends JpaRepository<AbstractControlObject, Long> {
    @Query(value = "select * from control_object where o_type =:'EQ' and  qr_code =: qrCode", nativeQuery = true)
    Optional<AbstractControlObject> findEquipmentByQrCode(String qrCode);
}
