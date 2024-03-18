package ar.mikellbobadilla.exceptions.genre;

import org.springframework.web.ErrorResponse;

public class GenreException extends RuntimeException {
    public GenreException(String message) {
        super(message);
    }
}
