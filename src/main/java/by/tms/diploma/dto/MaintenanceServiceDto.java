package by.tms.diploma.dto;

import javax.validation.constraints.NotBlank;

public class MaintenanceServiceDto {
    @NotBlank
    private String maintenanceInstruction;

    public MaintenanceServiceDto() {
    }

    public MaintenanceServiceDto(String maintenanceInstruction) {
        this.maintenanceInstruction = maintenanceInstruction;
    }

    public String getMaintenanceInstruction() {
        return maintenanceInstruction;
    }

    public void setMaintenanceInstruction(String maintenanceInstruction) {
        this.maintenanceInstruction = maintenanceInstruction;
    }
}
