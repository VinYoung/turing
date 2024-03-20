//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vip.vinyoung.tools.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vip.vinyoung.tools.config.valication.ValicationConstants;
import vip.vinyoung.tools.validator.UserNameValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(
    {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER,
        ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UserNameValidator.class})
public @interface UserName {
    String message() default ValicationConstants.VALIDATE_USERNAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
