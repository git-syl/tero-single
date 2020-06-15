package com.oktfolio.tero.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Strings;
import com.oktfolio.tero.common.enums.ResultCode;
import com.oktfolio.tero.common.enums.ResultCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/08
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultEntity<T> {
    protected String code;
    protected String message;
    protected T data;
    protected HttpStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    protected LocalDateTime datetime;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    private ResultEntity() {
    }

    public ResultEntity(String code, String message, T data, HttpStatus status, LocalDateTime datetime) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
        this.datetime = datetime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String code;
        private String message;
        private HttpStatus status;
        private LocalDateTime datetime;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder datetime(LocalDateTime datetime) {
            this.datetime = datetime;
            return this;
        }

        public Builder status(@Nonnull HttpStatus status) {
            this.status = status;
            return this;
        }

        public <T> ResultEntity<T> data(T data) {
            return new ResultEntity<>(code, message, data, status, datetime);
        }

        public <T> ResultEntity<T> build() {
            return this.data(null);
        }

    }

    public static <T> ResultEntity<T> ok() {
        return ResultEntity.builder()
                .status(HttpStatus.OK)
                .build();
    }

    public static <T> ResultEntity<T> ok(@Nullable T data) {
        return ResultEntity.builder()
                .status(HttpStatus.OK)
                .datetime(LocalDateTime.now())
                .data(data);
    }

    public static <T> ResultEntity<T> created() {
        return ResultEntity.builder()
                .status(HttpStatus.CREATED)
                .build();
    }

    public static <T> ResultEntity<T> created(T data) {
        return ResultEntity.builder()
                .status(HttpStatus.CREATED)
                .data(data);
    }

    public static <T> ResultEntity<T> noContent() {
        return ResultEntity.builder()
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    public static ResultEntity.Builder of(ResultCode resultCode) {
        return ResultEntity.builder()
                .status(resultCode.status())
                .code(resultCode.value())
                .message(resultCode.message())
                .datetime(LocalDateTime.now());
    }

    public static ResultEntity.Builder error() {
        return ResultEntity.builder()
                .status(ResultCodeEnum.ERROR.status())
                .code(ResultCodeEnum.ERROR.value())
                .message(ResultCodeEnum.ERROR.message())
                .datetime(LocalDateTime.now());
    }

    public static ResultEntity.Builder error(HttpStatus status, String message) {
        return ResultEntity.builder()
                .status(status)
                .code(ResultCodeEnum.ERROR.value())
                .message(message)
                .datetime(LocalDateTime.now());
    }

    public static ResultEntity.Builder notFound(ResultCode resultCode) {
        return ResultEntity.builder()
                .status(HttpStatus.NOT_FOUND)
                .code(resultCode.value())
                .message(resultCode.message())
                .datetime(LocalDateTime.now());
    }

    public static ResultEntity.Builder badRequest(ResultCode resultCode) {
        return ResultEntity.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(resultCode.value())
                .message(resultCode.message());
    }

    public static ResultEntity.Builder unauthorized(ResultCode resultCode) {
        return ResultEntity.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .code(resultCode.value())
                .message(resultCode.message())
                .datetime(LocalDateTime.now());
    }

    public static ResultEntity.Builder unauthorized(String message) {
        return ResultEntity.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .code(ResultCodeEnum.ERROR.value())
                .message(message)
                .datetime(LocalDateTime.now());
    }

    public static ResultEntity.Builder forbidden(ResultCode resultCode) {
        return ResultEntity.builder()
                .status(HttpStatus.FORBIDDEN)
                .code(resultCode.value())
                .message(resultCode.message())
                .datetime(LocalDateTime.now());
    }

    public static ResultEntity.Builder created(ResultCode resultCode) {
        return ResultEntity.builder()
                .status(HttpStatus.CREATED)
                .code(resultCode.value())
                .message(resultCode.message());
    }

    public static ResultEntity.Builder internalServerError() {
        return ResultEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .datetime(LocalDateTime.now());
    }

    public static ResultEntity.Builder internalServerError(ResultCode resultCode) {
        return ResultEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(resultCode.value())
                .message(resultCode.message())
                .datetime(LocalDateTime.now());
    }

    public ResponseEntity<Object> responseEntity() {

        if (Objects.isNull(this.data)
                && Strings.isNullOrEmpty(this.code)
                && Strings.isNullOrEmpty(this.message)
                && !Objects.isNull(this.status)) {
            return new ResponseEntity<>(this.getStatus());
        } else {
            return new ResponseEntity<>(this, this.getStatus());
        }
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", status=" + status +
                ", datetime=" + datetime +
                '}';
    }
}
