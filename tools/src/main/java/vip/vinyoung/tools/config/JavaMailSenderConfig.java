package vip.vinyoung.tools.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@Data
@ConfigurationProperties(prefix = "spring.mail")
public class JavaMailSenderConfig {
    private String host;

    private String protocol;

    private String prodefaultEncodingtocol;

    private String username;

    private String password;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setProtocol("smtp");
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setUsername("601878125@qq.com");
        javaMailSender.setPassword("gltbbdfhamoubffe");
        javaMailSender.setPort(587);
        return javaMailSender;
    }
}
