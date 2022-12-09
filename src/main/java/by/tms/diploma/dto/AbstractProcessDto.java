package by.tms.diploma.dto;

import by.tms.diploma.entity.User;

import java.time.LocalDateTime;

public class AbstractProcessDto {
    private LocalDateTime processStart;
    private LocalDateTime processEnd;
    private User employee;
    private String cleaningType;
    private String procedure;
    private String productName;
    private String seriesNumber;

    public AbstractProcessDto() {
    }

    public AbstractProcessDto(LocalDateTime processStart, LocalDateTime processEnd, User employee) {
        this.processStart = processStart;
        this.processEnd = processEnd;
        this.employee = employee;
    }

    public LocalDateTime getProcessStart() {
        return processStart;
    }

    public void setProcessStart(LocalDateTime processStart) {
        this.processStart = processStart;
    }

    public LocalDateTime getProcessEnd() {
        return processEnd;
    }

    public void setProcessEnd(LocalDateTime processEnd) {
        this.processEnd = processEnd;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }
}
