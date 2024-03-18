package ar.mikellbobadilla.validations;


import ar.mikellbobadilla.validations.validators.ImageExtensionValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ImageExtensionValidator.class)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD})
public @interface IsImage {

    String message() default "The value must be an image file.";

    Class<?>[] groups () default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
