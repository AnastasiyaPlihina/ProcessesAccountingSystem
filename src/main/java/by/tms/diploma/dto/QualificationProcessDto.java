package by.tms.diploma.dto;

public class QualificationProcessDto {
    private String description;

    public QualificationProcessDto() {
    }

    public QualificationProcessDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
