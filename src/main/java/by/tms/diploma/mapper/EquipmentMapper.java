package by.tms.diploma.mapper;

import by.tms.diploma.dto.EquipmentDto;
import by.tms.diploma.entity.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    Equipment convertEquipmentDtoToEquipment(EquipmentDto equipmentDto);
}
