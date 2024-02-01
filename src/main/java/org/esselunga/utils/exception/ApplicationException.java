package org.esselunga.utils.exception;

public class ApplicationException extends Exception {
    public ApplicationException(final Throwable cause) {
        super(cause);
    }

    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException() {
    }
}
