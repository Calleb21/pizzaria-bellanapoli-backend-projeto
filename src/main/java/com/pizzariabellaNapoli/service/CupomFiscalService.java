package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.CupomFiscal;
import com.pizzariabellaNapoli.repository.CupomFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description of CupomFiscalService
 * Created by calle on 20/12/2023.
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

    public List<CupomFiscal> listarCuponsFiscaisPorCarrinho(Carrinho carrinho) {
        return cupomFiscalRepository.findByCarrinho(carrinho);
    }

    public Optional<CupomFiscal> buscarCupomFiscalPorCarrinhoEId(Carrinho carrinho, Long id) {
        return cupomFiscalRepository.findByCarrinhoAndId(carrinho, id);
    }
}
