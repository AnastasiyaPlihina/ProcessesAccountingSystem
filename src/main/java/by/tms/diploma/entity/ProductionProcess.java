package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("PP")
public class ProductionProcess extends AbstractProcess {
    private String productName;
    private String seriesNumber;

    public ProductionProcess() {
    }

    public ProductionProcess(List<Equipment> equipment, User employee, String productName, String seriesNumber) {
        super(equipment, employee);
        this.productName = productName;
        this.seriesNumber = seriesNumber;
    }

    public ProductionProcess(String productName, String seriesNumber) {
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
