package com.example.acelera_maker_blog.service;

import com.example.acelera_maker_blog.model.Postagem;
import com.example.acelera_maker_blog.repository.PostagemRepository;
import com.example.acelera_maker_blog.repository.TemaRepository;
import com.example.acelera_maker_blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Postagem> listarTodas() {
        return postagemRepository.findAll();
    }

    public Optional<Postagem> buscarPorId(Long id) {
        return postagemRepository.findById(id);
    }

    public Postagem salvar(Postagem postagem) {
        // Validar e associar Tema
        var tema = temaRepository.findById(postagem.getTema().getId())
                .orElseThrow(() -> new RuntimeException("Tema não encontrado com ID: " + postagem.getTema().getId()));
        postagem.setTema(tema);

        // Validar e associar Usuario
        var usuario = usuarioRepository.findById(postagem.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + postagem.getUsuario().getId()));
        postagem.setUsuario(usuario);

        return postagemRepository.save(postagem);
    }

    public void deletar(Long id) {
        postagemRepository.deleteById(id);
    }

    
    public List<Postagem> buscarPorTema(String descricao) {
        return postagemRepository.findAllByTemaDescricaoContainingIgnoreCase(descricao);
    }
}
