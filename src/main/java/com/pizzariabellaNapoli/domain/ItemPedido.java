package com.pizzariabellaNapoli.domain;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Description of ItemPedido
 * Created by calle on 18/12/2023.
 */
@Data
@Entity(name = "tb_itens_pedidos")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public ItemPedido() {

    }

    public ItemPedido(Long id, int quantidade, Pizza pizza, Pedido pedido) {
        this.id = id;
        this.quantidade = quantidade;
        this.pizza = pizza;
        this.pedido = pedido;
    }
}
