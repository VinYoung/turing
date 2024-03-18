package vip.vinyoung.tools.bean.basic;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class LogDetail {
    private String id;
    private int line;
    private String log;
    private String level;
    private String service;
    private String threadName;
    private String classPath;
    private String ip;
    private Date datetime;

    public enum Level {
        INFO("info"),
        ERROR("error"),
        WARN("warn"),
        DEBUG("debug");

        private final String level;

        Level(String level) {
            this.level = level;
        }

        public String getValue() {
            return level;
        }
    }
}
