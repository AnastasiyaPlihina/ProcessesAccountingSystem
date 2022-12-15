package by.tms.diploma.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProcessDto {
    @Size(min = 1)
    private List<String> equipmentQrCodes;
    @NotNull
    private String processType;

    public ProcessDto() {
    }

    public ProcessDto(List<String> equipmentQrCodes, String processType) {
        this.equipmentQrCodes = equipmentQrCodes;
        this.processType = processType;
    }

    public List<String> getEquipmentQrCodes() {
        return equipmentQrCodes;
    }

    public void setEquipmentQrCodes(List<String> equipmentQrCodes) {
        this.equipmentQrCodes = equipmentQrCodes;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    @Override
    public String toString() {
        return "ProcessDto{" +
                "equipmentQrCodes='" + equipmentQrCodes + '\'' +
                ", processType='" + processType + '\'' +
                '}';
    }
}
