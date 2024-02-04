package ar.mikellbobadilla.dto.validations.validators;

import ar.mikellbobadilla.dto.validations.IsText;
import jakarta.validation.ConstraintValidator;

public class TextValidator implements ConstraintValidator<IsText, String> {
    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {

       return value == null || value.matches("^\\p{L}*$");
    }
}
