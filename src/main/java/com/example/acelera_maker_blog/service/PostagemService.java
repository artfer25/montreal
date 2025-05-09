package com.example.acelera_maker_blog.service;

import com.example.acelera_maker_blog.model.Postagem;
import com.example.acelera_maker_blog.repository.PostagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public List<Postagem> listarTodas() {
        return postagemRepository.findAll();
    }

    public Optional<Postagem> buscarPorId(Long id) {
        return postagemRepository.findById(id);
    }

    public List<Postagem> buscarPorTitulo(String titulo) {
        return postagemRepository.findAllByTituloContainingIgnoreCase(titulo);
    }

    public Postagem salvar(Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    public void deletar(Long id) {
        postagemRepository.deleteById(id);
    }
}
