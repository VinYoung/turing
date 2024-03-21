package vip.vinyoung.tools.bean.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.List;

/**
 * 邮件发送基类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MailBasic {
    /**
     * 邮件接收方，可多人
     */
    private List<String> receivers;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;
}
