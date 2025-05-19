package com.example.acelera_maker_blog.controller;

import com.example.acelera_maker_blog.model.Tema;
import com.example.acelera_maker_blog.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temas")
public class TemaController {

    @Autowired
    private TemaService temaService;

    @GetMapping
    public List<Tema> listarTodos() {
        return temaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> buscarPorId(@PathVariable Long id) {
        return temaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tema salvar(@RequestBody Tema tema) {
        return temaService.salvar(tema);
    }

    @PutMapping
    public ResponseEntity<Tema> atualizar(@RequestBody Tema tema) {
        return temaService.buscarPorId(tema.getId())
                .map(registro -> ResponseEntity.ok(temaService.salvar(tema)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        temaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
