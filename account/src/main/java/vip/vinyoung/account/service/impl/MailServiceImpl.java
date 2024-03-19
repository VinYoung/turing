package vip.vinyoung.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vip.vinyoung.account.bean.mail.MailBasic;
import vip.vinyoung.account.service.MailService;
import vip.vinyoung.tools.exception.ServiceException;
import java.util.List;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void send(MailBasic mail) throws ServiceException {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(mail.getReceivers().get(0));
        //邮件标题
        message.setSubject(mail.getSubject());
        //邮件内容
        message.setText(mail.getContent());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("邮件发送失败！");
        }
    }

    @Override
    public void send(List<? extends MailBasic> mail) throws ServiceException {

    }
}
