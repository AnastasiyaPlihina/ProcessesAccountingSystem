package by.tms.diploma.dto;

import javax.validation.constraints.NotBlank;

public class QualificationProcessDto {
    @NotBlank
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
