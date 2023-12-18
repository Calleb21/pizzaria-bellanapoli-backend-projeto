package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.CupomFiscal;
import com.pizzariabellaNapoli.service.CupomFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of CupomFiscalController
 * Created by calle on 18/12/2023.
 */
@RestController
@RequestMapping("/api/cuponsfiscais")
@CrossOrigin(origins = "http://localhost:5173")
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

    @PostMapping("/gerar")
    public ResponseEntity<CupomFiscal> gerarCupomFiscal(@RequestBody CupomFiscal cupomFiscal) {
        CupomFiscal novoCupomFiscal = cupomFiscalService.salvarCupomFiscal(cupomFiscal);
        return new ResponseEntity<>(novoCupomFiscal, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<Void> cancelarCupomFiscal(@PathVariable Long id) {
        cupomFiscalService.excluirCupomFiscal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
