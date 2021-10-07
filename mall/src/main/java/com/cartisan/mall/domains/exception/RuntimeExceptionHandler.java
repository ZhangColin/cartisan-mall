package com.cartisan.mall.domains.exception;

import com.cartisan.constants.CodeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static com.cartisan.responses.ResponseUtil.fail;

/**
 * @author colin
 */
@RestControllerAdvice
@Order(50)
@Slf4j
public class RuntimeExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> error(RuntimeException exception) {
        return fail(exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> error(MethodArgumentNotValidException exception) {
        final CodeMessage errorMessage = CodeMessage.VALIDATE_ERROR.fillArgs(
                exception.getBindingResult().getFieldErrors().stream()
                        .map(e->e.getField() +" " +e.getDefaultMessage())
                        .collect(Collectors.joining("\n"))
        );

        log.warn("数据校验异常：{}", errorMessage.getMessage());
        return fail(errorMessage);
    }
}
