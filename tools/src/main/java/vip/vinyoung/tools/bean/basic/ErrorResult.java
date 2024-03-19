package vip.vinyoung.tools.bean.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import vip.vinyoung.tools.enums.ErrorCode;
import vip.vinyoung.tools.utils.CommonUtils;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResult {
    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("error_msg")
    private String errorMsg;

    @JsonProperty("en_error_msg")
    private String enErrorMsg;

    @JsonProperty("trace_id")
    private final String traceId;

    public ErrorResult(String errorCode, String errorMsg, String enErrorMsg) {
        this.traceId = CommonUtils.getTraceId();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.enErrorMsg = enErrorMsg;
    }

    public ErrorResult(ErrorCode errorCode) {
        this.traceId = CommonUtils.getTraceId();
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorMsg();
        this.enErrorMsg = errorCode.getEnErrorMsg();
    }

    public ErrorResult(ErrorCode errorCode, String... fieldNames) {
        this(errorCode.getErrorCode(), CommonUtils.format(errorCode.getErrorMsg(), fieldNames),
            CommonUtils.format(errorCode.getEnErrorMsg(), fieldNames));
    }
}

