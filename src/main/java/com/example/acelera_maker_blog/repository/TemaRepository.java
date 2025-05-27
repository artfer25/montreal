package com.example.acelera_maker_blog.repository;

import com.example.acelera_maker_blog.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {
    List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
}
