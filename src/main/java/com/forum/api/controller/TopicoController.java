package com.forum.api.controller;

import com.forum.api.topico.DatosListaTopico;
import com.forum.api.topico.DatosRegistroTopico;
import com.forum.api.topico.DatosActualizacionTopico;
import com.forum.api.topico.Topico;
import com.forum.api.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroTopico datos){
        System.out.println(datos);
        repository.save(new Topico(datos));
    }

    @GetMapping
    public Page<DatosListaTopico> listar(@PageableDefault(size=10, sort= {"titulo"}) Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaTopico::new);
    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionTopico datos) {
        var topico = repository.getReferenceById(datos.id());
        topico.actualizarInformaciones(datos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopico> detallar(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var topico = topicoOptional.get();
        return ResponseEntity.ok(new DatosListaTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var existe = repository.existsById(id);
        if (!existe) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
