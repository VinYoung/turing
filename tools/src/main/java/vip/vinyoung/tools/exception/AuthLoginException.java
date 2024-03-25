package vip.vinyoung.tools.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 认证或鉴权异常类
 *
 * @author Vin.Young
 * @since 2024-03-25
 */
@Getter
@Setter
public class AuthLoginException extends ServiceException {
    public AuthLoginException(String code) {
        super(code, HttpStatus.UNAUTHORIZED);
    }

    public AuthLoginException(String code, HttpStatus httpStatus) {
        super(code, httpStatus);
    }
}
