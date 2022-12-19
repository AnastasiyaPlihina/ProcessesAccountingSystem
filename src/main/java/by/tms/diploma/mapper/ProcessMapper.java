package by.tms.diploma.mapper;

import by.tms.diploma.dto.*;
import by.tms.diploma.entity.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProcessMapper {
    CleaningProcess convertCleaningProcessDtoToCleaningProcess(CleaningProcessDto cleaningProcessDto);

    ProductionProcess convertProductionProcessDtoToProductionProcess(ProductionProcessDto productionProcessDto);

    MaintenanceService convertMaintenanceServiceDtoToMaintenanceService(MaintenanceServiceDto maintenanceServiceDto);

    QualificationProcess convertQualificationProcessDtoToQualificationProcess(QualificationProcessDto qualificationProcessDto);

    default AbstractProcessDto convertAbstractProcessToAbstractProcessDto(AbstractProcess process) {
        AbstractProcessDto abstractProcessDto = new AbstractProcessDto(process.getProcessStart(), process.getProcessEnd(), process.getEmployee());
        if (process instanceof CleaningProcess) {
            abstractProcessDto.setCleaningType(((CleaningProcess) process).getCleaningType());
            abstractProcessDto.setProcedure(((CleaningProcess) process).getProcedure());
        } else if (process instanceof ProductionProcess) {
            abstractProcessDto.setProductName(((ProductionProcess) process).getProductName());
            abstractProcessDto.setSeriesNumber(((ProductionProcess) process).getSeriesNumber());
        } else if (process instanceof MaintenanceService) {
            abstractProcessDto.setMaintenanceInstruction(((MaintenanceService) process).getMaintenanceInstruction());
        } else {
            abstractProcessDto.setQualificationDescription(((QualificationProcess) process).getDescription());
        }
        return abstractProcessDto;
    }
}
