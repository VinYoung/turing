package vip.vinyoung.tools.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import vip.vinyoung.tools.config.Constants;
import vip.vinyoung.tools.enums.AccountEnum;
import java.util.Locale;
import java.util.UUID;

public class CommonUtils {
    private final static String PASSWORD_ERROR_KEY = "PasswordError_";

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

    /**
     * 删除已保存的前端时间戳
     * <br>
     *
     * @author vinyoung
     */
    public static String format(String s, Object... values) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }
        s = s.replaceAll("\\{}", "%s");
        return String.format(Locale.ENGLISH, s, values);
    }

    /**
     * 判断账号类型
     * <br>
     *
     * @param account 账号
     * @return 账号类型
     * @author vinyoung
     */
    public static AccountEnum guessAccount(String account) {
        if (StringUtils.isEmpty(account)) {
            return AccountEnum.USER_NAME;
        }
        if (StringUtils.contains(account, Constants.AT_SYMBOL)) {
            return AccountEnum.EMAIL;
        }
        return AccountEnum.USER_NAME;
    }

    /**
     * 获取密码错误的redis key
     * <br>
     *
     * @author wangyunshu
     */
    public static String getLoginPasswordErrorRedisKey(String id) {
        return PASSWORD_ERROR_KEY.concat(id);
    }
}
