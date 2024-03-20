package vip.vinyoung.tools.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import vip.vinyoung.tools.config.ErrorConstants;
import vip.vinyoung.tools.config.valication.ValicationConstants;

@Getter
@ApiModel("发生异常时的返回类")
public enum ErrorCode {

    TURING_SYSTEM_001(ErrorConstants.SYSTEM_001, "系统错误！", "System error!"),
    TURING_ACCOUNT_001(ErrorConstants.ACCOUNT_001, "账号系统错误！", "Account system error!"),
    TURING_VALIDATE_PASSWORD(ValicationConstants.VALIDATE_PASSWORD,
        "{} 密码格式错误，包含大小写字母、数字和_-.?;,!@#特殊字符，且在8到20位之间！",
        "The {} password format is incorrect and contains uppercase and lowercase letters, numbers and _-.?;,!@# special characters, and is between 8 and 20 characters!"),
    VALIDATE_USERNAME(ValicationConstants.VALIDATE_USERNAME,
        "{} 用户名格式错误，大小写字母开头，包含大小写字母、数字和_-特殊字符，且在4到200位之间！",
        "The {} username format is incorrect. It starts with an uppercase and lowercase letter, contains uppercase and lowercase letters, numbers and _- special characters, and is between 4 and 200 characters!"),
    VALIDATE_NOTNULL(ValicationConstants.VALIDATE_NOTNULL, "{} 不能为空！", "{} cannot be empty!"),
    VALIDATE_ERROR(ValicationConstants.VALIDATE_ERROR, "获取参数校验结果失败！",
        "Failed to obtain parameter verification results!"),
    VALIDATE_EMAIL(ValicationConstants.VALIDATE_EMAIL, "{} 邮箱校验失败！", "Email {} verification failed!");

    private final String errorCode;

    private final String errorMsg;

    private final String enErrorMsg;

    ErrorCode(String errorCode, String errorMsg, String enErrorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.enErrorMsg = enErrorMsg;
    }

    public static ErrorCode getByCode(String errorCode) {
        for (ErrorCode value : ErrorCode.values()) {
            if (value.getErrorCode().equalsIgnoreCase(errorCode)) {
                return value;
            }
        }
        return TURING_SYSTEM_001;
    }
}
