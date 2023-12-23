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
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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

    @PostMapping("/cadastrar")
    public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.salvarFuncionario(funcionario);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    @PostMapping("/autenticar")
    public ResponseEntity<Funcionario> autenticarFuncionario(@RequestBody Funcionario dadosAutenticacao) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorEmail(dadosAutenticacao.getEmail());

        if (funcionario != null && funcionario.getSenha().equals(dadosAutenticacao.getSenha())) {
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
