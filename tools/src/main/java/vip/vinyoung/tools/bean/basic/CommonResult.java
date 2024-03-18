package vip.vinyoung.tools.bean.basic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import vip.vinyoung.tools.config.Constants;
import vip.vinyoung.tools.utils.CommonUtils;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> {
    private String status;
    private T result;
    private String message;
    private HttpStatus code = HttpStatus.OK;
    @JsonProperty("trace_id")
    private final String traceId;

    private CommonResult() {
        message = "";
        traceId = CommonUtils.getTraceId();
    }

    public static CommonResult<Object> success() {
        return success(null, "operate success");
    }

    public static <T> CommonResult<T> success(T result) {
        return success(result, "operate success");
    }

    public static <T> CommonResult<T> success(T result, String message) {
        CommonResult<T> success = new CommonResult<>();
        success.status = Constants.REQ_SUCCESS;
        success.result = result;
        success.message = message;
        return success;
    }

    public static <T> CommonResult<T> failed(T result) {
        return failed(result, "operate failed.", HttpStatus.BAD_REQUEST);
    }

    public static <T> CommonResult<T> failed(T result, HttpStatus status) {
        return failed(result, "operate failed.", status);
    }

    public static <T> CommonResult<T> failed(T result, String message, HttpStatus status) {
        CommonResult<T> failed = new CommonResult<>();
        failed.status = Constants.REQ_FAILED;
        failed.result = result;
        failed.message = message;
        failed.code = status;
        return failed;
    }

    public boolean isSuccess() {
        return Constants.REQ_SUCCESS.equals(this.status);
    }
}

