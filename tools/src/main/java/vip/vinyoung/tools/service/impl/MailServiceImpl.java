package vip.vinyoung.tools.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import vip.vinyoung.tools.bean.mail.MailBasic;
import vip.vinyoung.tools.config.JavaMailSenderConfig;
import vip.vinyoung.tools.exception.ServiceException;
import vip.vinyoung.tools.service.MailService;
import java.util.List;

@Component
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JavaMailSenderConfig javaMailSenderConfig;

    @Override
    public void send(MailBasic mail) throws ServiceException {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(javaMailSenderConfig.getUsername());
        //谁要接收
        message.setTo(mail.getReceivers().get(0));
        //邮件标题
        message.setSubject(mail.getSubject());
        //邮件内容
        message.setText(mail.getContent());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("邮件发送失败 {}！", e.getMessage());
        }
    }

    @Override
    public void send(List<? extends MailBasic> mail) throws ServiceException {

    }
}
