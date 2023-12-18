package com.pizzariabellaNapoli.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description of CupomFiscal
 * Created by calle on 18/12/2023.
 */
@Data
@Entity(name = "tb_cupons_fiscais")
public class CupomFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String informacoesPedido;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime horario;

    private String formaPagamento;

    public CupomFiscal() {

    }

    public CupomFiscal(Long id, String informacoesPedido, LocalDateTime horario, String formaPagamento) {
        this.id = id;
        this.informacoesPedido = informacoesPedido;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
    }
}
