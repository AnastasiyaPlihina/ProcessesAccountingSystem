package by.tms.diploma.dto;

public class MaintenanceServiceDto {
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
