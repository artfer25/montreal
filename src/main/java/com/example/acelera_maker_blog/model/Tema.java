package com.example.acelera_maker_blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "temas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descricao;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
    private List<Postagem> postagens;
}
