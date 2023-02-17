package by.tms.diploma.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "O_TYPE")
@Table(name = "control_object")
public abstract class AbstractControlObject extends AbstractEntity {
    private String name;
    private String qrCode;
    private String internalCode;
    private String technicalCharacteristic;
    @ManyToOne
    private Department department;
    private LocalDate lastQualificationDate;
    @Enumerated(EnumType.STRING)
    private ObjectStatus objectStatus;
    private boolean isProcess;

    public AbstractControlObject() {
    }

    public AbstractControlObject(String name, String qrCode, String internalCode, String technicalCharacteristic, Department department, LocalDate lastQualificationDate, ObjectStatus objectStatus, boolean isProcess) {
        this.name = name;
        this.qrCode = qrCode;
        this.internalCode = internalCode;
        this.technicalCharacteristic = technicalCharacteristic;
        this.department = department;
        this.lastQualificationDate = lastQualificationDate;
        this.objectStatus = objectStatus;
        this.isProcess = isProcess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getTechnicalCharacteristic() {
        return technicalCharacteristic;
    }

    public void setTechnicalCharacteristic(String technicalCharacteristic) {
        this.technicalCharacteristic = technicalCharacteristic;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getLastQualificationDate() {
        return lastQualificationDate;
    }

    public void setLastQualificationDate(LocalDate lastQualificationDate) {
        this.lastQualificationDate = lastQualificationDate;
    }

    public ObjectStatus getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(ObjectStatus objectStatus) {
        this.objectStatus = objectStatus;
    }

    public boolean isProcess() {
        return isProcess;
    }

    public void setProcess(boolean process) {
        isProcess = process;
    }
}
