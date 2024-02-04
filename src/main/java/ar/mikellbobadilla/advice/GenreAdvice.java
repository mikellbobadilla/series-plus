package ar.mikellbobadilla.advice;

import ar.mikellbobadilla.advice.exceptions.ErrorResponse;
import ar.mikellbobadilla.advice.exceptions.GenreException;
import ar.mikellbobadilla.advice.exceptions.GenreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GenreAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(GenreNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        var error = buildErrorResponse(e, status);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(GenreException.class)
    public ResponseEntity<ErrorResponse> handleException(GenreException e) {
        var status = HttpStatus.BAD_REQUEST;
        var error = buildErrorResponse(e, status);
        return new ResponseEntity<>(error, status);
    }

    private ErrorResponse buildErrorResponse(GenreException e, HttpStatus status) {
        return ErrorResponse.builder()
                .status(status.value())
                .error(e.getMessage())
                .build();
    }
}
