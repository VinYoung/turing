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
public class EmailValidator implements ConstraintValidator<UserName, String> {
    private final static Pattern EMAIL_PATTERN = Pattern.compile(
        "^([\\w-]+(\\.[\\w-]+)*)@[\\w-]+(\\.[\\w-]+)+$");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(s).matches();
    }
}
