package vip.vinyoung.tools.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import vip.vinyoung.tools.config.Constants;

import java.util.UUID;

@Slf4j
@Component
public class CommonUtils {
    private final static int DEFAULT_VERIFICATION_CODE_LENGTH = 6;

    /**
     * 获取32位uuid
     * <br>
     * 32位数字+字母, 不包括其他符号
     *
     * @author wangyunshu
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll(Constants.SYMBOL_HYPHEN, Constants.STR_EMPTY);
    }

    /**
     * 生成日志trace_id
     * <br>
     *
     * @author wangyunshu
     */
    public static void createTraceId() {
        MDC.put(Constants.TRACE_ID, CommonUtils.uuid());
    }

    /**
     * 移除日志trace_id
     * <br>
     *
     * @author wangyunshu
     */
    public static void removeTraceId() {
        MDC.remove(Constants.TRACE_ID);
    }

    /**
     * 获取当前trace_id
     * <br>
     *
     * @author wangyunshu
     */
    public static String getTraceId() {
        return MDC.get(Constants.TRACE_ID);
    }

    /**
     * 获取request中前端请求时间戳
     * <br>
     *
     * @author wangyunshu
     */
    public static void getTimestamp(HttpServletRequest request) {
        MDC.put(Constants.REQUEST_TIMESTAMP, request.getParameter(Constants.REQUEST_TIMESTAMP));
    }

    /**
     * 删除已保存的前端时间戳
     * <br>
     *
     * @author wangyunshu
     */
    public static void removeTimestamp() {
        MDC.remove(Constants.REQUEST_TIMESTAMP);
    }
}
