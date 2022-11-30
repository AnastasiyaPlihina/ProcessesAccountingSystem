package by.tms.diploma.dto;

public class ProcessDto {
    private String equipmentQrCode;
    private String processType;

    public ProcessDto() {
    }

    public ProcessDto(String equipmentQrCode, String processType) {
        this.equipmentQrCode = equipmentQrCode;
        this.processType = processType;
    }

    public String getEquipmentQrCode() {
        return equipmentQrCode;
    }

    public void setEquipmentQrCode(String equipmentQrCode) {
        this.equipmentQrCode = equipmentQrCode;
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
                "equipmentQrCode='" + equipmentQrCode + '\'' +
                ", processType='" + processType + '\'' +
                '}';
    }
}
