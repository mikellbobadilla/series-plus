package ar.mikellbobadilla.validations.validators;

import ar.mikellbobadilla.utils.ImageExtension;
import ar.mikellbobadilla.validations.IsImage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class ImageExtensionValidator implements ConstraintValidator<IsImage, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {

        return Arrays
                .stream(ImageExtension.values())
                .anyMatch(ext -> {
                    String fileName = file.getOriginalFilename();
                    return fileName != null && fileName.endsWith(ext.getExtension());
                });
    }
}
