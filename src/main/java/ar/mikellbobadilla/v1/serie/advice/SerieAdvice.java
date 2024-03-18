package ar.mikellbobadilla.v1.serie.advice;

import ar.mikellbobadilla.v1.exceptions.ErrorResponse;
import ar.mikellbobadilla.v1.exceptions.GenreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SerieAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse buildErrorResponse(GenreException e, HttpStatus status) {
        return ErrorResponse.builder()
                .status(status.value())
                .error(e.getMessage())
                .build();
    }
}
