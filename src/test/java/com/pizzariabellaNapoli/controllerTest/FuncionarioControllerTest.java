package com.pizzariabellaNapoli.controllerTest;

import com.pizzariabellaNapoli.controller.FuncionarioController;
import com.pizzariabellaNapoli.domain.Funcionario;
import com.pizzariabellaNapoli.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Description of FuncionarioControllerTest
 * Created by calle on 18/12/2023.
 */
public class FuncionarioControllerTest {

    @Mock
    private FuncionarioService funcionarioService;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodosFuncionarios_DeveRetornarListaDeFuncionarios() {
        Funcionario funcionario1 = new Funcionario(1L, "Nome1", "email1@example.com", "senha1");
        Funcionario funcionario2 = new Funcionario(2L, "Nome2", "email2@example.com", "senha2");
        List<Funcionario> listaFuncionarios = Arrays.asList(funcionario1, funcionario2);

        when(funcionarioService.listarTodosFuncionarios()).thenReturn(listaFuncionarios);

        ResponseEntity<List<Funcionario>> responseEntity = funcionarioController.listarTodosFuncionarios();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(listaFuncionarios, responseEntity.getBody());
    }

    @Test
    void salvarFuncionario_DeveRetornarNovoFuncionario() {
        Funcionario funcionario = new Funcionario(1L, "Nome", "email@example.com", "senha");

        when(funcionarioService.salvarFuncionario(any(Funcionario.class))).thenReturn(funcionario);

        ResponseEntity<Funcionario> responseEntity = funcionarioController.salvarFuncionario(funcionario);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(funcionario, responseEntity.getBody());
    }

    @Test
    public void autenticarFuncionario_SuccessTest() {
        Funcionario dadosAutenticacao = new Funcionario(null, "Calleb", "calleb@email.com", "calleb123");
        Funcionario funcionario = new Funcionario(1L, "Calleb", "calleb@email.com", "calleb123");

        when(funcionarioService.buscarFuncionarioPorEmail(dadosAutenticacao.getEmail())).thenReturn(funcionario);

        ResponseEntity<Funcionario> responseEntity = funcionarioController.autenticarFuncionario(dadosAutenticacao);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(funcionario, responseEntity.getBody());
    }

    @Test
    public void autenticarFuncionario_FailTest() {
        Funcionario dadosAutenticacao = new Funcionario(null, "Calleb", "calleb@email.com", "senhaIncorreta");

        when(funcionarioService.buscarFuncionarioPorEmail(dadosAutenticacao.getEmail())).thenReturn(null);

        ResponseEntity<Funcionario> responseEntity = funcionarioController.autenticarFuncionario(dadosAutenticacao);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
}