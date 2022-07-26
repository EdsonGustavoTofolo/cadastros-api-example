package br.edu.utfpr.labscontrol.cadastrosapi.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    @Getter
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public BusinessException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
