package dev.jardel.catalog.config;

import dev.jardel.catalog.domain.category.exceptions.CategoryNotFoundException;
import dev.jardel.catalog.dto.common.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // indica que a classe irá capturar as exceções lançadas pelos controllers
public class ControllersExceptionsHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCategoryNotFound(CategoryNotFoundException e, HttpServletRequest req) {
        return notFoundResponse(e, req);
    }


    // exception response builders

    private ResponseEntity<ErrorResponseDto> unauthorizedResponse(CategoryNotFoundException e, HttpServletRequest req) {
        ErrorResponseDto dto = new ErrorResponseDto(e, req);
        dto.setStatus(401);
        dto.setError("Unauthorized access");
        return ResponseEntity.status(401).body(dto);
    }

    private ResponseEntity<ErrorResponseDto> notFoundResponse(CategoryNotFoundException e, HttpServletRequest req) {
        ErrorResponseDto dto = new ErrorResponseDto(e, req);
        dto.setStatus(404);
        dto.setError("Resource not found");
        return ResponseEntity.status(404).body(dto);
    }

    private ResponseEntity<ErrorResponseDto> conflictResponse(CategoryNotFoundException e, HttpServletRequest req) {
        ErrorResponseDto dto = new ErrorResponseDto(e, req);
        dto.setStatus(409);
        dto.setError("Conflict on resource");
        return ResponseEntity.status(409).body(dto);
    }

}
