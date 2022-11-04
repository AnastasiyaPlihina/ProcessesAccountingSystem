package by.tms.diploma.mapper;

import by.tms.diploma.dto.EquipmentDto;
import by.tms.diploma.entity.Equipment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class EquipmentMapper {
    //ToDo Equipment builder
    public Equipment convertEquipmentDtoToEquipment(EquipmentDto equipmentDto) {
        Equipment equipment = new Equipment();
        equipment.setName(equipmentDto.getName());
        equipment.setQrCode(equipmentDto.getQrCode());
        equipment.setInternalCode(equipmentDto.getInternalCode());
        equipment.setInventoryNumber(equipmentDto.getInventoryNumber());
        equipment.setDepartment(equipmentDto.getDepartment());
        equipment.setTechnicalCharacteristic(equipmentDto.getTechnicalCharacteristic());
        equipment.setLastMaintenanceServiceDate(LocalDate.parse(equipmentDto.getLastMaintenanceServiceDate()));
        equipment.setLastQualificationDate(LocalDate.parse(equipmentDto.getLastQualificationDate()));
        return equipment;
    }
}
