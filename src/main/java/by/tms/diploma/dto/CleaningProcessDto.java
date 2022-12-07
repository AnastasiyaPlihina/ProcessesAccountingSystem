package by.tms.diploma.dto;


public class CleaningProcessDto {
    private String cleaningType;
    private String procedure;

    public CleaningProcessDto() {
    }

    public CleaningProcessDto(String cleaningType, String procedure) {
        this.cleaningType = cleaningType;
        this.procedure = procedure;
    }
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
