package com.okfolio.tero.common.exception;

import com.okfolio.tero.common.ResultEntity;
import com.okfolio.tero.common.enums.ResultCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@RestControllerAdvice
public class GlobalExceptionHandlers {

    /**
     * 处理 Validator 异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResultEntity> bindExceptionHandler(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        HashMap<String, String> map = new HashMap<>(16);
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity
                .badRequest()
                .body(ResultEntity.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .code(ResultCodeEnum.INVALID_PARAMS.value())
                        .message(ResultCodeEnum.INVALID_PARAMS.message())
                        .data(map)
                        .build());
    }

    /**
     * 运行时异常，未处理的运行时异常都走这里
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResultEntity> runtimeExceptionHandler(RuntimeException exception) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResultEntity
                        .builder()
                        .code(ResultCodeEnum.INTERNAL_SERVER_ERROR.value())
                        .message(exception.getMessage())
                        .datetime(LocalDateTime.now())
                        .build());
    }
}
