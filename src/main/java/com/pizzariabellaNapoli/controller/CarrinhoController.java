package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.Funcionario;
import com.pizzariabellaNapoli.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of CarrinhoController
 * Created by calle on 20/12/2023.
 */
@RestController
@RequestMapping("/api/carrinhos")
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carrinho>> listarTodosCarrinhos() {
        List<Carrinho> carrinhos = carrinhoService.listarTodosCarrinhos();
        return new ResponseEntity<>(carrinhos, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Carrinho> buscarCarrinhoPorId(@PathVariable Long id) {
        return carrinhoService.buscarCarrinhoPorId(id)
                .map(carrinho -> new ResponseEntity<>(carrinho, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Carrinho> salvarCarrinho(@RequestBody Carrinho carrinho) {
        carrinho.getItens().forEach(item -> item.setCarrinho(carrinho));

        Carrinho novoCarrinho = carrinhoService.salvarCarrinho(carrinho);
        return new ResponseEntity<>(novoCarrinho, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirCarrinho(@PathVariable Long id) {
        carrinhoService.excluirCarrinho(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/listarPorFuncionario/{idFuncionario}")
    public ResponseEntity<List<Carrinho>> listarCarrinhosPorFuncionario(@PathVariable Long idFuncionario) {
        Funcionario funcionario = new Funcionario();
        List<Carrinho> carrinhos = carrinhoService.listarCarrinhosPorFuncionario(funcionario);
        return new ResponseEntity<>(carrinhos, HttpStatus.OK);
    }

    @GetMapping("/buscarPorFuncionarioEId/{idFuncionario}/{idCarrinho}")
    public ResponseEntity<Carrinho> buscarCarrinhoPorFuncionarioEId(@PathVariable Long idFuncionario, @PathVariable Long idCarrinho) {
        Funcionario funcionario = new Funcionario();
        return carrinhoService.buscarCarrinhoPorFuncionarioEId(funcionario, idCarrinho)
                .map(carrinho -> new ResponseEntity<>(carrinho, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
