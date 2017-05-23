package com.askade.flunky.exception;

/**
 * Created by AdrianIonita on 5/23/2017.
 */
public class AuthKeyException extends Exception{

    private String exceptionMessage;

    public AuthKeyException() {
        super();
    }

    public AuthKeyException(String message) {
        super(message);
        setExceptionMessage(message);
    }

    public AuthKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthKeyException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return getExceptionMessage();
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
