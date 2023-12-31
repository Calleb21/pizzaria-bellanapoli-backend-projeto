package com.pizzariabellaNapoli.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Description of Funcionario
 * Created by calle on 18/12/2023.
 */
@Data
@Entity(name = "tb_funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    public Funcionario() {

    }

    public Funcionario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
