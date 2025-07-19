package com.forum.api.topico;

public record DatosActualizacionTopico(
        Long id,
        String titulo,
        String status,
        String autor,
        String curso
) {
}
