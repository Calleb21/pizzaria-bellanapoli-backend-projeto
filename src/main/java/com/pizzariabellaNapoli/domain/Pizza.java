package com.pizzariabellaNapoli.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description of Pizza
 * Created by calle on 18/12/2023.
 */
@Data
@Entity(name = "tb_pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagem;

    private String nome;

    private String ingredientes;

    private BigDecimal valor;

    public Pizza() {

    }

    public Pizza(Long id, String imagem, String nome, String ingredientes, BigDecimal valor) {
        this.id = id;
        this.imagem = imagem;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.valor = valor;
    }
}
