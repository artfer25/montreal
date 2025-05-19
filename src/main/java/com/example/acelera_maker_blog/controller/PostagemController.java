package com.example.acelera_maker_blog.controller;

import com.example.acelera_maker_blog.model.Postagem;
import com.example.acelera_maker_blog.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public List<Postagem> listarTodas() {
        return postagemService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscarPorId(@PathVariable Long id) {
        return postagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Postagem salvar(@RequestBody Postagem postagem) {
        return postagemService.salvar(postagem);
    }

    @PutMapping
    public ResponseEntity<Postagem> atualizar(@RequestBody Postagem postagem) {
        return postagemService.buscarPorId(postagem.getId())
                .map(registroExistente -> ResponseEntity.ok(postagemService.salvar(postagem)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        postagemService.deletar(id);
        return ResponseEntity.ok().build();
    }

    // NOVO
    @GetMapping("/tema/{descricao}")
    public ResponseEntity<List<Postagem>> buscarPorTema(@PathVariable String descricao) {
        List<Postagem> postagens = postagemService.buscarPorTema(descricao);
        if (postagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(postagens);
    }
}
