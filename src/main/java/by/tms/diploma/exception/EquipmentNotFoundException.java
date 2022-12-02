package by.tms.diploma.exception;

public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException() {
        super();
    }

    public EquipmentNotFoundException(String message) {
        super(message);
    }

    public EquipmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquipmentNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EquipmentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return ExceptionConstant.EQUIPMENT_NOT_FOUND;
    }
}
