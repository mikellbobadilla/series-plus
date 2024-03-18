package ar.mikellbobadilla.validations.validators;

import ar.mikellbobadilla.validations.IsText;
import jakarta.validation.ConstraintValidator;

public class TextValidator implements ConstraintValidator<IsText, String> {
    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        return value.matches("^.*$");
    }
}
