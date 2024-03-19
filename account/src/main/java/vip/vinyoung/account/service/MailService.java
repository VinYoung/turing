package vip.vinyoung.account.service;

import org.springframework.stereotype.Service;
import vip.vinyoung.account.bean.mail.MailBasic;
import vip.vinyoung.tools.exception.ServiceException;
import java.util.List;

@Service
public interface MailService {
    /**
     * 发送单人邮件
     *
     * @param mail 邮件内容
     * @throws ServiceException 发送失败
     */
    void send(MailBasic mail) throws ServiceException;

    /**
     * 发送多人邮件
     *
     * @param mail 邮件内容
     * @throws ServiceException 发送失败
     */
    void send(List<? extends MailBasic> mail) throws ServiceException;
}
