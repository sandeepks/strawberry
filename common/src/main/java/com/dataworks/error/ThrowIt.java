package com.dataworks.error;

/**
 * Created by Sandeep on 5/12/17.
 */
public class ThrowIt extends Exception {

    public ThrowIt() {
        super();
    }

    public ThrowIt(String message) {
        super(message);
    }

    public ThrowIt(String message, Throwable cause) {
        super(message, cause);
    }

    public ThrowIt(Throwable cause) {
        super(cause);
    }

    public ThrowIt(String message, Throwable cause,
                   boolean enableSuppression,
                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
