package br.info.felseje.exceptions;

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
