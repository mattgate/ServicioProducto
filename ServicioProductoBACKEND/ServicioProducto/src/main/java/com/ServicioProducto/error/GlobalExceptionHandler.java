package com.ServicioProducto.error;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<?> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(extractCustomMessage(ex.getMessage()));
    }

    private static String extractCustomMessage(String fullMessage) {
        int firstBracket = fullMessage.indexOf("[");
        int secondBracket = fullMessage.indexOf("]", firstBracket + 1);
        int thirdBracket = fullMessage.indexOf("[", secondBracket + 1);
        int fourthBracket = fullMessage.indexOf("]", thirdBracket + 1);

        if (thirdBracket != -1 && fourthBracket != -1) {
            return fullMessage.substring(thirdBracket + 1, fourthBracket).trim();
        }

        return "Error al ejecutar procedimiento.";
    }
}
