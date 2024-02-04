package ar.mikellbobadilla.dto;


import ar.mikellbobadilla.dto.validations.IsText;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateGenre(
        @NotBlank(message = "Genre name is required.")
        @IsText
        @Length(min = 3, max = 10)
        String name
) {
}
