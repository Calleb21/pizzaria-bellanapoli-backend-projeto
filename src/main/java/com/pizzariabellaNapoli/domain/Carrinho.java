package com.pizzariabellaNapoli.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Description of Carrinho
 * Created by calle on 20/12/2023.
 */
@Data
@Entity(name = "tb_carrinhos")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<ItemCarrinho> itens;

    public Carrinho() {

    }

    public Carrinho(Long id, Funcionario funcionario, List<ItemCarrinho> itens) {
        this.id = id;
        this.funcionario = funcionario;
        this.itens = itens;
    }
}
