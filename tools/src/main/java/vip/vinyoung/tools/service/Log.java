package vip.vinyoung.tools.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vip.vinyoung.tools.bean.basic.LogDetail;
import vip.vinyoung.tools.utils.CommonUtils;

public interface Log {
    default Logger getLogger() {
        return LoggerFactory.getLogger(Log.class);
    }

    static void log(Logger logger, LogDetail.Level level, String _msg, Object... values) {
        String msg = Log.getMessage(_msg, values);
        switch (level) {
            case INFO:
                logger.info(msg);
                break;
            case ERROR:
                logger.error(msg);
                break;
            case WARN:
                logger.warn(msg);
                break;
            case DEBUG:
                logger.debug(msg);
                break;
        }
    }

    default void debug(String _msg, Object... values) {
        Log.log(getLogger(), LogDetail.Level.DEBUG, _msg, values);
    }

    default void info(String _msg, Object... values) {
        Log.log(getLogger(), LogDetail.Level.INFO, _msg, values);
    }

    default void warn(String _msg, Object... values) {
        Log.log(getLogger(), LogDetail.Level.WARN, _msg, values);
    }

    default void error(String _msg, Object... values) {
        Log.log(getLogger(), LogDetail.Level.ERROR, _msg, values);
    }

    /**
     * 日志内容格式化
     * <br>
     *
     * @return 替换占位符后的日志字符串
     * @author wangyunshu
     */
    static String getMessage(String _msg, Object... values) {
        return CommonUtils.format(_msg, values);
    }
}
