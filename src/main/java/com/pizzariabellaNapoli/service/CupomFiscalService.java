package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.CupomFiscal;
import com.pizzariabellaNapoli.domain.ItemPedido;
import com.pizzariabellaNapoli.domain.Pedido;
import com.pizzariabellaNapoli.repository.CupomFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Description of CupomFiscalService
 * Created by calle on 18/12/2023.
 */
@Service
public class CupomFiscalService {

    private final CupomFiscalRepository cupomFiscalRepository;

    @Autowired
    public CupomFiscalService(CupomFiscalRepository cupomFiscalRepository) {
        this.cupomFiscalRepository = cupomFiscalRepository;
    }

    public List<CupomFiscal> listarTodosCuponsFiscais() {
        return cupomFiscalRepository.findAll();
    }

    public Optional<CupomFiscal> buscarCupomFiscalPorId(Long id) {
        return cupomFiscalRepository.findById(id);
    }

    public CupomFiscal salvarCupomFiscal(CupomFiscal cupomFiscal) {
        return cupomFiscalRepository.save(cupomFiscal);
    }

    public void excluirCupomFiscal(Long id) {
        cupomFiscalRepository.deleteById(id);
    }

    public CupomFiscal gerarCupomFiscal(Pedido pedido) {
        CupomFiscal cupomFiscal = new CupomFiscal();

        cupomFiscal.setInformacoesPedido(extrairInformacoesPedido(pedido));
        cupomFiscal.setHorario(LocalDateTime.now());
        cupomFiscal.setFormaPagamento(pedido.getFormaPagamento());

        return cupomFiscalRepository.save(cupomFiscal);
    }

    private String extrairInformacoesPedido(Pedido pedido) {

        StringBuilder informacoes = new StringBuilder("Produtos: ");
        for (ItemPedido item : pedido.getItens()) {
            informacoes.append(item.getPizza().getNome())
                    .append(" (").append(item.getQuantidade()).append("), ");
        }
        return informacoes.toString();
    }
}
