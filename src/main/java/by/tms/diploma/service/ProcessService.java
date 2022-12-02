package by.tms.diploma.service;

import by.tms.diploma.dto.ProcessDto;
import by.tms.diploma.entity.*;
import by.tms.diploma.exception.ProcessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProcessService {

    public AbstractProcess createProcess(ProcessDto processDto) {
        String processType = processDto.getProcessType();
        return switch (processType) {
            case ("cleaning") -> new CleaningProcess();
            case ("production") -> new ProductionProcess();
            case ("maintenance") -> new MaintenanceService();
            case ("repair") -> new RepairProcess();
            default -> throw new ProcessException();
        };
    }



}
