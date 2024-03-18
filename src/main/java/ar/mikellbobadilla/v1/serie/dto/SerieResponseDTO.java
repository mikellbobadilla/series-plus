package ar.mikellbobadilla.v1.serie.dto;

public record SerieResponseDTO(Long id, String title, String description, String poster, Integer seasons, boolean  status) {
}
