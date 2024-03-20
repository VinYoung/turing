package vip.vinyoung.tools.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import vip.vinyoung.tools.annotation.validation.UserName;
import java.util.regex.Pattern;

/**
 * 密码校验类
 *
 * @author Vin.Young
 * @since 2024-03-18
 */
public class UserNameValidator implements ConstraintValidator<UserName, String> {
    private final static Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_\\-]{4,200}$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return USERNAME_PATTERN.matcher(s).matches();
    }
}
