package com.pizzariabellaNapoli.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Description of Pedido
 * Created by calle on 18/12/2023.
 */
@Data
@Entity(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private LocalDateTime horario;

    private BigDecimal precoTotal;

    private String formaPagamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    public Pedido() {

    }

    public Pedido(Long id, Funcionario funcionario, StatusPedido status, LocalDateTime horario, BigDecimal precoTotal, String formaPagamento, List<ItemPedido> itens) {
        this.id = id;
        this.funcionario = funcionario;
        this.status = status;
        this.horario = horario;
        this.precoTotal = precoTotal;
        this.formaPagamento = formaPagamento;
        this.itens = itens;
    }
}
