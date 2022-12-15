package by.tms.diploma.dto;

import javax.validation.constraints.NotBlank;

public class ProductionProcessDto {
    @NotBlank
    private String productName;
    @NotBlank
    private String seriesNumber;

    public ProductionProcessDto() {
    }

    public ProductionProcessDto(String productName, String seriesNumber) {
        this.productName = productName;
        this.seriesNumber = seriesNumber;
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
