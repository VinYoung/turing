//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.vinyoung.tools.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vip.vinyoung.tools.config.valication.ValicationConstants;
import vip.vinyoung.tools.validator.PasswordValidator;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {PasswordValidator.class}
)
public @interface Password {
    String message() default ValicationConstants.VALIDATE_PASSWORD;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
