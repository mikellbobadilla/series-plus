package ar.mikellbobadilla.v1.serie.dto;

import ar.mikellbobadilla.validations.IsImage;
import ar.mikellbobadilla.validations.IsText;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record CreateSerieDTO(
        @IsText
        @Length(min = 3, max = 50)
        @NotBlank
        String title,
        @IsImage
        MultipartFile poster,
        @NotBlank
        String description,
        Set<String> genres
) {
}
