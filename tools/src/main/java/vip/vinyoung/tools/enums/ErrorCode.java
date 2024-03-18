package vip.vinyoung.tools.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@Getter
@ApiModel("发生异常时的返回类")
public enum ErrorCode {

    TURING_SYSTEM_001("SYSTEM_001", "系统错误！", "System error!"),
    TURING_ACCOUNT_001("ACCOUNT_001", "账号系统错误！", "Account system error!");

    private String errorCode;
    private String errorMsg;
    private String enErrorMsg;

    ErrorCode(String errorCode, String errorMsg, String enErrorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.enErrorMsg = enErrorMsg;
    }
}
