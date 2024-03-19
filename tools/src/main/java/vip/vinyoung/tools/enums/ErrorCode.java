package vip.vinyoung.tools.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import vip.vinyoung.tools.config.ValicationConstants;

@Getter
@ApiModel("发生异常时的返回类")
public enum ErrorCode {

    TURING_SYSTEM_001("SYSTEM_001", "系统错误！", "System error!"),
    TURING_ACCOUNT_001("ACCOUNT_001", "账号系统错误！", "Account system error!"),
    TURING_VALIDATE_PASSWORD(ValicationConstants.VALIDATE_PASSWORD,
            "{}密码格式错误，包含大小写字母、数字和_-.?;,!@#$%%^&*特殊字符，且在8到20位之间！",
            "The {} password format is incorrect and contains uppercase and lowercase letters, numbers and _-.?;,!@#$%%^&* special characters, and is between 8 and 20 characters!"),
    VALIDATE_NOTNULL(ValicationConstants.VALIDATE_NOTNULL, "{}不能为空！", "{} cannot be empty!");

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
