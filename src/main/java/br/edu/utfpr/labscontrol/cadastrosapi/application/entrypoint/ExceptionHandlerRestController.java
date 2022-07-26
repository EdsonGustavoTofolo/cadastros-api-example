package br.edu.utfpr.labscontrol.cadastrosapi.application.entrypoint;

import br.edu.utfpr.labscontrol.cadastrosapi.shared.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerRestController {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorApi> businessExceptionHandler(BusinessException exception, WebRequest request) {
        var errorApi = ErrorApi.builder()
                .dataHora(LocalDateTime.now())
                .mensagens(List.of(exception.getMessage()))
                .status(exception.getStatus().value())
                .build();
        return ResponseEntity.status(errorApi.getStatus()).body(errorApi);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(fieldError -> fieldError.getDefaultMessage()).toList();
        var errorApi = ErrorApi.builder()
                .mensagens(errorMessages)
                .dataHora(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.badRequest().body(errorApi);
    }
}
