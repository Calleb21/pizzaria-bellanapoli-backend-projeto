package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.CupomFiscal;
import com.pizzariabellaNapoli.service.CupomFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of CupomFiscalController
 * Created by calle on 20/12/2023.
 */
public class CupomFiscalController {

    private final CupomFiscalService cupomFiscalService;

    @Autowired
    public CupomFiscalController(CupomFiscalService cupomFiscalService) {
        this.cupomFiscalService = cupomFiscalService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CupomFiscal>> listarTodosCuponsFiscais() {
        List<CupomFiscal> cuponsFiscais = cupomFiscalService.listarTodosCuponsFiscais();
        return new ResponseEntity<>(cuponsFiscais, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<CupomFiscal> buscarCupomFiscalPorId(@PathVariable Long id) {
        return cupomFiscalService.buscarCupomFiscalPorId(id)
                .map(cupomFiscal -> new ResponseEntity<>(cupomFiscal, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/salvar")
    public ResponseEntity<CupomFiscal> salvarCupomFiscal(@RequestBody CupomFiscal cupomFiscal) {
        CupomFiscal novoCupomFiscal = cupomFiscalService.salvarCupomFiscal(cupomFiscal);
        return new ResponseEntity<>(novoCupomFiscal, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirCupomFiscal(@PathVariable Long id) {
        cupomFiscalService.excluirCupomFiscal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/listarPorCarrinho/{idCarrinho}")
    public ResponseEntity<List<CupomFiscal>> listarCuponsFiscaisPorCarrinho(@PathVariable Long idCarrinho) {
        Carrinho carrinho = new Carrinho();
        List<CupomFiscal> cuponsFiscais = cupomFiscalService.listarCuponsFiscaisPorCarrinho(carrinho);
        return new ResponseEntity<>(cuponsFiscais, HttpStatus.OK);
    }

    @GetMapping("/buscarPorCarrinhoEId/{idCarrinho}/{idCupomFiscal}")
    public ResponseEntity<CupomFiscal> buscarCupomFiscalPorCarrinhoEId(@PathVariable Long idCarrinho, @PathVariable Long idCupomFiscal) {
        Carrinho carrinho = new Carrinho();
        return cupomFiscalService.buscarCupomFiscalPorCarrinhoEId(carrinho, idCupomFiscal)
                .map(cupomFiscal -> new ResponseEntity<>(cupomFiscal, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
