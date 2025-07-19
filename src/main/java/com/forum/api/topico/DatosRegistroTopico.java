package com.forum.api.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String fecha_creacion,
        @NotBlank String status,
        @NotBlank String autor,
        @NotBlank String curso
) {
}
