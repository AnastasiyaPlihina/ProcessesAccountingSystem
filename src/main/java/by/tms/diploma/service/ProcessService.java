package by.tms.diploma.service;

import by.tms.diploma.dto.CleaningProcessDto;
import by.tms.diploma.dto.ProcessDto;
import by.tms.diploma.entity.*;
import by.tms.diploma.exception.EquipmentNotFoundException;
import by.tms.diploma.exception.ProcessException;
import by.tms.diploma.mapper.ProcessMapper;
import by.tms.diploma.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProcessService {
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private EquipmentService equipmentService;

    public void saveProcess(AbstractProcess process) {
        processRepository.save(process);
    }

    public CleaningProcess startCleaningProcess(User employee, List<Equipment> equipments, CleaningProcessDto cleaningProcessDto) {
        CleaningProcess cleaningProcess = processMapper.convertCleaningProcessDtoToCleaningProcess(cleaningProcessDto);
        for (Equipment equipment:equipments) {
            equipment.setProcess(true);
            Optional<Equipment> updateEquipment = equipmentService.updateEquipment(equipment);
            cleaningProcess.getEquipment().add(updateEquipment.get());
        }
        cleaningProcess.setEmployee(employee);
        cleaningProcess.setProcessStart(LocalDateTime.now());
        return cleaningProcess;
    }

//    public AbstractProcess createProcess(ProcessDto processDto) {
//        String processType = processDto.getProcessType();
//        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(processDto.getEquipmentQrCode());
//        if(equipmentByQrCode.isPresent()) {
//            Equipment equipment = equipmentByQrCode.get();
//            return switch (processType) {
//                case ("cleaning") -> new CleaningProcess(equipment);
//                case ("production") -> new ProductionProcess(equipment);
//                case ("maintenance") -> new MaintenanceService(equipment);
//                case ("repair") -> new RepairProcess(equipment);
//                default -> throw new ProcessException();
//            };
//        } else {
//            throw new EquipmentNotFoundException();
//        }
//    }


}
