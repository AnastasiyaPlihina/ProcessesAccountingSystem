package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("MP")
public class MaintenanceService extends AbstractProcess {

    @ElementCollection
    private List<String> servicedItems;

    public MaintenanceService() {
    }

    public MaintenanceService(Equipment equipment, User employee, List<String> servicedItems) {
        super(equipment, employee);
        this.servicedItems = servicedItems;
    }

    public List<String> getServicedItems() {
        return servicedItems;
    }

    public void setServicedItems(List<String> servicedItems) {
        this.servicedItems = servicedItems;
    }
}
