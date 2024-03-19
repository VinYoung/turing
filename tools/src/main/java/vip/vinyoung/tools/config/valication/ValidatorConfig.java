package vip.vinyoung.tools.config.valication;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置入参校验为快速失败模式
 *
 * @author vinyoung
 * @since 2024-03-19
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
