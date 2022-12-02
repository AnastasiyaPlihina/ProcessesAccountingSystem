package by.tms.diploma.service;

import by.tms.diploma.dto.ProcessDto;
import by.tms.diploma.entity.*;
import by.tms.diploma.exception.EquipmentNotFoundException;
import by.tms.diploma.exception.ProcessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProcessService {
    @Autowired
    private EquipmentService equipmentService;

    private AbstractProcess createProcess(ProcessDto processDto) {
        String processType = processDto.getProcessType();
        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(processDto.getEquipmentQrCode());
        if(equipmentByQrCode.isPresent()) {
            Equipment equipment = equipmentByQrCode.get();
            return switch (processType) {
                case ("cleaning") -> new CleaningProcess(equipment);
                case ("production") -> new ProductionProcess(equipment);
                case ("maintenance") -> new MaintenanceService(equipment);
                case ("repair") -> new RepairProcess(equipment);
                default -> throw new ProcessException();
            };
        } else {
            throw new EquipmentNotFoundException();
        }
    }


}
