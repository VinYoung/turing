package vip.vinyoung.tools.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 服务运行时的自定义异常类，用于抛出服务中的一些检查问题
 *
 * @author Vin.Young
 * @since 2024-03-14
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {
    private String code;

    private HttpStatus httpStatus;

    public ServiceException(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
