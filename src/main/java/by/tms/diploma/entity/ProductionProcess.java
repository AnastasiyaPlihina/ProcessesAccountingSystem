package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PP")
public class ProductionProcess extends AbstractProcess {
    private String productName;
    private String seriesNumber;

    public ProductionProcess() {
    }

    public ProductionProcess(Equipment equipment) {
        super(equipment);
    }

    public ProductionProcess(Equipment equipment, String productName, String seriesNumber) {
        super(equipment);
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
