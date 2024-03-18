package ar.mikellbobadilla.dtos.genre;

import lombok.Builder;

import java.util.List;

@Builder
public record PageGenreDTO(
        List<GenreResponseDTO> content,
        int page,
        int size,
        int totalPages,
        long totalElements
) {
}
