package aden.dev.site.exception;

public class NotFoundException extends  RuntimeException{
    public NotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
