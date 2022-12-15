package by.tms.diploma.dto;


import javax.validation.constraints.NotBlank;

public class CleaningProcessDto {
    @NotBlank
    private String cleaningType;
    @NotBlank
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
