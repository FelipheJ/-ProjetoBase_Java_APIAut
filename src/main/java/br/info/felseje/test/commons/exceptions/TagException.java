package br.info.felseje.test.commons.exceptions;

public class TagException extends RuntimeException {

    public TagException() {
        super();
    }

    public TagException(String message) {
        super(message);
    }

    public TagException(Throwable cause) {
        super(cause);
    }

    public TagException(String message, Throwable cause) {
        super(message, cause);
    }
}
