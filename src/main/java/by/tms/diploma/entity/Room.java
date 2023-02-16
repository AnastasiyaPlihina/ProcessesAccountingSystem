package by.tms.diploma.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("RM")
public class Room extends AbstractControlObject {
    private String roomGrade;

    public Room() {
    }

    public Room(String name, String qrCode, String internalCode, String technicalCharacteristic, Department department, LocalDate lastQualificationDate, ObjectStatus status, boolean isProcess, String roomGrade) {
        super(name, qrCode, internalCode, technicalCharacteristic, department, lastQualificationDate, status, isProcess);
        this.roomGrade = roomGrade;
    }

    public String getRoomGrade() {
        return roomGrade;
    }

    public void setRoomGrade(String roomGrade) {
        this.roomGrade = roomGrade;
    }
}
