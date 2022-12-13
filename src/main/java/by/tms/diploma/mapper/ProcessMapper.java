package by.tms.diploma.mapper;

import by.tms.diploma.dto.*;
import by.tms.diploma.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ProcessMapper {
    public CleaningProcess convertCleaningProcessDtoToCleaningProcess(CleaningProcessDto cleaningProcessDto) {
        return new CleaningProcess(cleaningProcessDto.getCleaningType(), cleaningProcessDto.getProcedure());
    }

    public ProductionProcess convertProductionProcessDtoToProductionProcess(ProductionProcessDto productionProcessDto) {
        return new ProductionProcess(productionProcessDto.getProductName(), productionProcessDto.getSeriesNumber());
    }

    public MaintenanceService convertMaintenanceServiceDtoToMaintenanceService(MaintenanceServiceDto maintenanceServiceDto) {
        return new MaintenanceService(maintenanceServiceDto.getMaintenanceInstruction());
    }

    public QualificationProcess convertQualificationProcessDtoToQualificationProcess(QualificationProcessDto qualificationProcessDto) {
        return  new QualificationProcess(qualificationProcessDto.getDescription());
    }

    public AbstractProcessDto convertAbstractProcessToAbstractProcessDto(AbstractProcess process) {
        AbstractProcessDto abstractProcessDto = new AbstractProcessDto(process.getProcessStart(), process.getProcessEnd(), process.getEmployee());
        if (process instanceof CleaningProcess) {
            abstractProcessDto.setCleaningType(((CleaningProcess) process).getCleaningType());
            abstractProcessDto.setProcedure(((CleaningProcess) process).getProcedure());
        } else if (process instanceof ProductionProcess) {
            abstractProcessDto.setProductName(((ProductionProcess) process).getProductName());
            abstractProcessDto.setSeriesNumber(((ProductionProcess) process).getSeriesNumber());
        }
        return abstractProcessDto;
    }
}
