package com.askade.flunky.exception;

/**
 * Created by AdrianIonita on 5/22/2017.
 */
public class LoginException extends Exception {

    private String exceptionMessage;

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
        setExceptionMessage(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
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
