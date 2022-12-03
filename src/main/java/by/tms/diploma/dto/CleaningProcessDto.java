package by.tms.diploma.dto;

import by.tms.diploma.entity.Equipment;

public class CleaningProcessDto {
//    private long equipmentId;
    private String cleaningType;
    private String procedure;

    public CleaningProcessDto() {
    }

    public CleaningProcessDto(String cleaningType, String procedure) {
        this.cleaningType = cleaningType;
        this.procedure = procedure;
    }

    //    public long getEquipmentId() {
//        return equipmentId;
//    }
//
//    public void setEquipmentId(long equipmentId) {
//        this.equipmentId = equipmentId;
//    }

    public String getCleaningType() {
        return cleaningType;
    }

    public void setCleaningType(String cleaningType) {
        this.cleaningType = cleaningType;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    @Override
    public String toString() {
        return "CleaningProcessDto{" +
                ", cleaningType='" + cleaningType + '\'' +
                ", procedure='" + procedure + '\'' +
                '}';
    }
}
