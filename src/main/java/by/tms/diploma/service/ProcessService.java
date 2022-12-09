package by.tms.diploma.service;

import by.tms.diploma.dto.AbstractProcessDto;
import by.tms.diploma.dto.CleaningProcessDto;
import by.tms.diploma.dto.ProcessDto;
import by.tms.diploma.dto.ProductionProcessDto;
import by.tms.diploma.entity.*;
import by.tms.diploma.exception.EquipmentNotFoundException;
import by.tms.diploma.exception.ProcessException;
import by.tms.diploma.mapper.ProcessMapper;
import by.tms.diploma.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Optional<AbstractProcess> saveProcess(AbstractProcess process) {
        return Optional.of(processRepository.save(process));
    }

    public CleaningProcess startCleaningProcess(User employee, List<Equipment> equipments, CleaningProcessDto cleaningProcessDto) {
        CleaningProcess cleaningProcess = processMapper.convertCleaningProcessDtoToCleaningProcess(cleaningProcessDto);
        cleaningProcess.setEquipment(new ArrayList<>());
        for (Equipment equipment : equipments) {
            equipment.setProcess(true);
            Optional<Equipment> updateEquipment = equipmentService.updateEquipment(equipment);
            cleaningProcess.getEquipment().add(updateEquipment.get());
        }
        cleaningProcess.setEmployee(employee);
        cleaningProcess.setProcessStart(LocalDateTime.now());
        return cleaningProcess;
    }

    public ProductionProcess startProductionProcess(User employee, List<Equipment> equipments, ProductionProcessDto productionProcessDto) {
        ProductionProcess productionProcess = processMapper.convertProductionProcessDtoToProductionProcess(productionProcessDto);
        productionProcess.setEquipment(new ArrayList<>());
        for (Equipment equipment : equipments) {
            equipment.setProcess(true);
            Optional<Equipment> updateEquipment = equipmentService.updateEquipment(equipment);
            productionProcess.getEquipment().add(updateEquipment.get());
        }
        productionProcess.setEmployee(employee);
        productionProcess.setProcessStart(LocalDateTime.now());
        return productionProcess;
    }

    public void stopProcess(String equipmentQrCode) {
        AbstractProcess processByEquipment = findProcessByEquipment(equipmentQrCode);
        processByEquipment.setProcessEnd(LocalDateTime.now());
        saveProcess(processByEquipment);
        Optional<Equipment> equipmentByQrCode = equipmentService.findEquipmentByQrCode(equipmentQrCode);
        equipmentByQrCode.get().setProcess(false);
        equipmentService.updateEquipment(equipmentByQrCode.get());
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
}
