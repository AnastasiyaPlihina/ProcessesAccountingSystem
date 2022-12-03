package by.tms.diploma.mapper;

import by.tms.diploma.dto.CleaningProcessDto;
import by.tms.diploma.entity.CleaningProcess;
import org.springframework.stereotype.Component;

@Component
public class ProcessMapper {
    public CleaningProcess convertCleaningProcessDtoToCleaningProcess(CleaningProcessDto cleaningProcessDto) {
       return new CleaningProcess(cleaningProcessDto.getCleaningType(), cleaningProcessDto.getProcedure());
    }
}
