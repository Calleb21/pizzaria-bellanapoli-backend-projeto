package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.Funcionario;
import com.pizzariabellaNapoli.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of FuncionarioController
 * Created by calle on 18/12/2023.
 */
@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "http://localhost:5173")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listarTodosFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarTodosFuncionarios();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        return funcionarioService.buscarFuncionarioPorId(id)
                .map(funcionario -> new ResponseEntity<>(funcionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.salvarFuncionario(funcionario);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
