package dev.jardel.catalog.dto.common;

import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.time.Instant;

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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
