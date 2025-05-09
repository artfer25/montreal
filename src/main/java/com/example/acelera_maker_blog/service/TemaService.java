package com.example.acelera_maker_blog.service;

import com.example.acelera_maker_blog.model.Tema;
import com.example.acelera_maker_blog.repository.TemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    public List<Tema> listarTodos() {
        return temaRepository.findAll();
    }

    public Optional<Tema> buscarPorId(Long id) {
        return temaRepository.findById(id);
    }

    public List<Tema> buscarPorDescricao(String descricao) {
        return temaRepository.findAllByDescricaoContainingIgnoreCase(descricao);
    }

    public Tema salvar(Tema tema) {
        return temaRepository.save(tema);
    }

    public void deletar(Long id) {
        temaRepository.deleteById(id);
    }
}
