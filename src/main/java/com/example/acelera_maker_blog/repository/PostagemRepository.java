package com.example.acelera_maker_blog.repository;

import com.example.acelera_maker_blog.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

    List<Postagem> findAllByTemaDescricaoContainingIgnoreCase(String descricao); // NOVO
}
