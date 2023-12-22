package com.pizzariabellaNapoli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Description of ItemCarrinho
 * Created by calle on 20/12/2023.
 */
@Data
@Entity(name = "tb_itens_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;


    public ItemCarrinho() {

    }

    public ItemCarrinho(Long id, int quantidade, Pizza pizza, Carrinho carrinho) {
        this.id = id;
        this.quantidade = quantidade;
        this.pizza = pizza;
        this.carrinho = carrinho;
    }
}
