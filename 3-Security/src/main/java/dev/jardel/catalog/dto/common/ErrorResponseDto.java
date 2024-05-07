package dev.jardel.catalog.dto.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
public class ErrorResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ErrorResponseDto() {
        this.timestamp = Instant.now();
    }

    public ErrorResponseDto(RuntimeException e, HttpServletRequest req) {
        this.timestamp = Instant.now();
        this.message = e.getMessage();
        this.path = req.getRequestURI();
    }
}
