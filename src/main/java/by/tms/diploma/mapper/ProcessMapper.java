package by.tms.diploma.mapper;

import by.tms.diploma.dto.AbstractProcessDto;
import by.tms.diploma.dto.CleaningProcessDto;
import by.tms.diploma.dto.ProductionProcessDto;
import by.tms.diploma.entity.AbstractProcess;
import by.tms.diploma.entity.CleaningProcess;
import by.tms.diploma.entity.ProductionProcess;
import org.springframework.stereotype.Component;

@Component
public class ProcessMapper {
    public CleaningProcess convertCleaningProcessDtoToCleaningProcess(CleaningProcessDto cleaningProcessDto) {
       return new CleaningProcess(cleaningProcessDto.getCleaningType(), cleaningProcessDto.getProcedure());
    }
    public ProductionProcess convertProductionProcessDtoToProductionProcess(ProductionProcessDto productionProcessDto) {
        return new ProductionProcess(productionProcessDto.getProductName(), productionProcessDto.getSeriesNumber());
    }
    public AbstractProcessDto convertAbstractProcessToAbstractProcessDto(AbstractProcess process) {
        AbstractProcessDto abstractProcessDto = new AbstractProcessDto(process.getProcessStart(), process.getProcessEnd(), process.getEmployee());
        if(process instanceof CleaningProcess) {
            abstractProcessDto.setCleaningType (((CleaningProcess) process).getCleaningType());
            abstractProcessDto.setProcedure(((CleaningProcess) process).getProcedure());
        } else if(process instanceof ProductionProcess) {
            abstractProcessDto.setProductName(((ProductionProcess) process).getProductName());
            abstractProcessDto.setSeriesNumber(((ProductionProcess) process).getSeriesNumber());
        }
        return abstractProcessDto;
    }
}
