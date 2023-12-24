package com.pizzariabellaNapoli.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime horario;

    private String formaPagamento;

    public Carrinho() {

    }

    public Carrinho(Long id, Funcionario funcionario, List<ItemCarrinho> itens, LocalDateTime horario, String formaPagamento) {
        this.id = id;
        this.funcionario = funcionario;
        this.itens = itens;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
    }

    @PrePersist
    public void prePersist() {
        if (horario != null) {
            horario = horario.atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
        }
    }
}
