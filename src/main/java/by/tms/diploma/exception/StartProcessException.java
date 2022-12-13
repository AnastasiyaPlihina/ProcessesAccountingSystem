package by.tms.diploma.exception;

public class StartProcessException extends RuntimeException {
    public StartProcessException() {
        super();
    }

    public StartProcessException(String message) {
        super(message);
    }

    public StartProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public StartProcessException(Throwable cause) {
        super(cause);
    }

    protected StartProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return ExceptionConstant.CANT_START_PROCESS;
    }
}
