package vip.vinyoung.tools.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import vip.vinyoung.tools.enums.ErrorCode;

/**
 * 服务运行时的自定义异常类，用于抛出服务中的一些检查问题
 *
 * @author Vin.Young
 * @since 2024-03-14
 */
@Getter
@Setter
public class ServiceException extends RuntimeException {
    private ErrorCode code;
    private String message;
    private String enMessage;
    private HttpStatus httpStatus;

    public ServiceException(ErrorCode code, HttpStatus httpStatus, Object... values) {
        this.code = code;
        this.httpStatus = httpStatus;
        String msg = code.getErrorMsg();
        this.message = String.format(msg, values);
        String enMsg = code.getEnErrorMsg();
        this.enMessage = String.format(enMsg, values);
    }
}
