package com.pizzariabellaNapoli.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description of CupomFiscal
 * Created by calle on 20/12/2023.
 */
@Data
@Entity(name = "tb_cupons_fiscais")
public class CupomFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime horario;

    private String formaPagamento;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    public CupomFiscal(Long id, LocalDateTime horario, String formaPagamento, Carrinho carrinho) {
        this.id = id;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
        this.carrinho = carrinho;
    }
}
