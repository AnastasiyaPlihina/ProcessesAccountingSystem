package by.tms.diploma.mapper;

import by.tms.diploma.dto.CleaningProcessDto;
import by.tms.diploma.dto.ProductionProcessDto;
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
}
