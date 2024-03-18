package vip.vinyoung.tools.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import vip.vinyoung.tools.annotation.validation.Password;

import java.util.regex.Pattern;

/**
 * 密码校验类
 *
 * @author Vin.Young
 * @since 2024-03-18
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
    private final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_\\-.?;,!@#$%^&*])[a-zA-Z0-9_\\-.?;,!@#$%^&*]{8,20}$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(s).matches();
    }
}
