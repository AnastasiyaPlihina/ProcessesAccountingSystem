package by.tms.diploma.service;

import by.tms.diploma.dto.*;
import by.tms.diploma.entity.*;
import by.tms.diploma.exception.ProcessException;
import by.tms.diploma.exception.StartProcessException;
import by.tms.diploma.mapper.ProcessMapper;
import by.tms.diploma.repository.ProcessRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProcessService {
    private static final Logger logger = LogManager.getLogger(ProcessService.class);

    private final ProcessRepository processRepository;

    private final ProcessMapper processMapper;

    private final EquipmentService equipmentService;

    public ProcessService(ProcessRepository processRepository, ProcessMapper processMapper, EquipmentService equipmentService) {
        this.processRepository = processRepository;
        this.processMapper = processMapper;
        this.equipmentService = equipmentService;
    }

    public Optional<AbstractProcess> saveProcess(AbstractProcess process) {
        return Optional.of(processRepository.save(process));
    }

    public CleaningProcess startCleaningProcess(User employee, List<Equipment> equipments, CleaningProcessDto cleaningProcessDto) {
        CleaningProcess cleaningProcess = processMapper.convertCleaningProcessDtoToCleaningProcess(cleaningProcessDto);
        setEquipmentListToStartProcess(cleaningProcess, equipments);
        cleaningProcess.setEmployee(employee);
        cleaningProcess.setProcessStart(LocalDateTime.now());
        logger.info("cleaning process start");
        return cleaningProcess;
    }

    public ProductionProcess startProductionProcess(User employee, List<Equipment> equipments, ProductionProcessDto productionProcessDto) {
        ProductionProcess productionProcess = processMapper.convertProductionProcessDtoToProductionProcess(productionProcessDto);
        setEquipmentListToStartProcess(productionProcess, equipments);
        productionProcess.setEmployee(employee);
        productionProcess.setProcessStart(LocalDateTime.now());
        logger.info("production process start");
        return productionProcess;
    }

    public MaintenanceService startMaintenanceService(User employee, List<Equipment> equipments, MaintenanceServiceDto maintenanceServiceDto) {
        MaintenanceService maintenanceService = processMapper.convertMaintenanceServiceDtoToMaintenanceService(maintenanceServiceDto);
        setEquipmentListToStartProcess(maintenanceService, equipments);
        maintenanceService.setEmployee(employee);
        maintenanceService.setProcessStart(LocalDateTime.now());
        logger.info("maintenance service start");
        return maintenanceService;
    }

    public QualificationProcess startQualificationProcess(User employee, List<Equipment> equipments, QualificationProcessDto qualificationProcessDto) {
        QualificationProcess qualificationProcess = processMapper.convertQualificationProcessDtoToQualificationProcess(qualificationProcessDto);
        setEquipmentListToStartProcess(qualificationProcess, equipments);
        qualificationProcess.setEmployee(employee);
        qualificationProcess.setProcessStart(LocalDateTime.now());
        logger.info("qualification process start");
        return qualificationProcess;
    }


    public void stopProcess(String equipmentQrCode) {
        AbstractProcess processByEquipment = findProcessByEquipment(equipmentQrCode);
        processByEquipment.setProcessEnd(LocalDateTime.now());
        saveProcess(processByEquipment);
        logger.info("stop process");
        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(equipmentQrCode);
        Equipment equipment = equipmentByQrCode.get();
        equipment.setProcess(false);
        if (processByEquipment instanceof MaintenanceService) {
            equipment.setLastMaintenanceServiceDate(LocalDate.now());
        }
        if (processByEquipment instanceof QualificationProcess) {
            equipment.setLastQualificationDate(LocalDate.now());
        }
        equipmentService.updateEquipment(equipmentByQrCode.get());
    }

    public List<AbstractProcessDto> findProcessListByEquipment(String equipmentQrCode) {
        List<AbstractProcess> allProcesses = processRepository.findAll();
        List<AbstractProcessDto> equipmentProcess = new ArrayList<>();
        for (AbstractProcess process : allProcesses) {
            List<Equipment> equipment = process.getEquipment();
            for (Equipment e : equipment) {
                if (e.getQrCode().equals(equipmentQrCode)) {
                    AbstractProcessDto abstractProcessDto = processMapper.convertAbstractProcessToAbstractProcessDto(process);
                    equipmentProcess.add(abstractProcessDto);
                }
            }
        }
        return equipmentProcess;
    }

    private void setEquipmentListToStartProcess(AbstractProcess process, List<Equipment> equipments) {
        process.setEquipment(new ArrayList<>());
        for (Equipment equipment : equipments) {
            List<AbstractProcessDto> processListByEquipment = findProcessListByEquipment(equipment.getQrCode());
            AbstractProcessDto abstractProcessDto = new AbstractProcessDto();
            if (!processListByEquipment.isEmpty()) {
                abstractProcessDto = processListByEquipment.get(processListByEquipment.size() - 1);
            }
            if (processListByEquipment.isEmpty()
                    || (abstractProcessDto.getProcessEnd() != null && abstractProcessDto.getCleaningType() != null)
                    || process instanceof CleaningProcess) {
                equipment.setProcess(true);
                Optional<Equipment> updateEquipment = equipmentService.updateEquipment(equipment);
                process.getEquipment().add(updateEquipment.get());
            } else {
                throw new StartProcessException();
            }
        }
    }

    private AbstractProcess findProcessByEquipment(String equipmentQrCode) {
        List<AbstractProcess> unfinishedProcesses = processRepository.findUnfinishedProcesses();
        for (AbstractProcess process : unfinishedProcesses) {
            List<Equipment> equipment = process.getEquipment();
            for (Equipment e : equipment) {
                if (e.getQrCode().equals(equipmentQrCode)) {
                    return process;
                }
            }
        }
        throw new ProcessException();
    }
}
