package ar.mikellbobadilla.v1.genre.dto;


import ar.mikellbobadilla.validations.IsText;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateGenreDTO(
        @NotBlank(message = "Genre name is required.")
        @IsText
        @Length(min = 3, max = 10)
        String name
) {
}
