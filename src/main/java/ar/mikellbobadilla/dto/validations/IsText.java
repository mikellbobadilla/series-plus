package ar.mikellbobadilla.dto.validations;

import ar.mikellbobadilla.dto.validations.validators.TextValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TextValidator.class)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
public @interface IsText {

    String message() default "The value must be a text.";

    Class<?>[] groups () default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
