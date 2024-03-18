package ar.mikellbobadilla.v1.genre.dto;

import java.util.List;

public record GenrePageResponse(
        List<GenreResponse> content,
        int page,
        int size,
        int totalPages,
        long totalElements
) {
}
