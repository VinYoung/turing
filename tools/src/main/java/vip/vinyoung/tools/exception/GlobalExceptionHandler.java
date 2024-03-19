package vip.vinyoung.tools.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import vip.vinyoung.tools.bean.basic.CommonResult;
import vip.vinyoung.tools.bean.basic.ErrorResult;
import vip.vinyoung.tools.enums.ErrorCode;
import vip.vinyoung.tools.service.Log;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object>, Log {
    private static final String EVENT_FAILURE = "EVENT FAILURE >>> {}";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getParameterType().equals(CommonResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof CommonResult) {
            HttpStatus code = ((CommonResult<?>) body).getCode();
            response.setStatusCode(code);
        }
        return body;
    }

    /**
     * 处理其他未单独处理的异常
     *
     * @param e 异常信息
     * @return 返回体
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> exception(Exception e) {
        error(EVENT_FAILURE, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResult(ErrorCode.TURING_SYSTEM_001));
    }

    /**
     * 入参异常统一处理，使用快速失败模式，故至多只有单个校验错误
     *
     * @param e 异常信息
     * @return 返回体
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            FieldError fieldError = fieldErrors.get(0);
            String errorCode = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            ErrorCode error = ErrorCode.getByCode(errorCode);
            ErrorResult errorResult = new ErrorResult(error, field);
            error(EVENT_FAILURE, errorCode);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
        }
        error("Failed to obtain parameter verification results");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ErrorCode.VALIDATE_ERROR));
    }

    /**
     * 系统检查的异常，使用error_code和error_msg的形式抛出
     *
     * @param e 异常详情
     * @return 返回体
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResult> ServiceException(ServiceException e) {
        error(EVENT_FAILURE, e.getMessage());
        return ResponseEntity.status(e.getHttpStatus())
            .body(new ErrorResult(e.getCode().getErrorCode(), e.getMessage(), e.getEnMessage()));
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
