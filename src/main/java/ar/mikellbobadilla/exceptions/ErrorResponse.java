package ar.mikellbobadilla.exceptions;

import lombok.Builder;

@Builder
public record ErrorResponse(Integer status, String error) {
}
