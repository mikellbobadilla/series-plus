package ar.mikellbobadilla.dto;

import java.util.List;

public record GenrePageResponse(
        List<GenreResponse> content,
        int page,
        int size,
        int totalPages,
        long totalElements
) {
}
