package com.forum.api.domain.topico;

public record DatosListaTopico(
        Long id,
        String titulo,
        String fecha_creacion,
        String status,
        String autor,
        String curso
) {
    public DatosListaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getFecha_creacion(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
