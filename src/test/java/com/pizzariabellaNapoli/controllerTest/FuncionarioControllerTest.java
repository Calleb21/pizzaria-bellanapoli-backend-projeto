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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    void buscarFuncionarioPorId_Encontrado_DeveRetornarFuncionario() {
        Funcionario funcionario = new Funcionario(1L, "Nome", "email@example.com", "senha");

        when(funcionarioService.buscarFuncionarioPorId(1L)).thenReturn(Optional.of(funcionario));

        ResponseEntity<Funcionario> responseEntity = funcionarioController.buscarFuncionarioPorId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(funcionario, responseEntity.getBody());
    }

    @Test
    void buscarFuncionarioPorId_NaoEncontrado_DeveRetornarNotFound() {
        when(funcionarioService.buscarFuncionarioPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Funcionario> responseEntity = funcionarioController.buscarFuncionarioPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
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
    void excluirFuncionario_DeveRetornarNoContent() {
        ResponseEntity<Void> responseEntity = funcionarioController.excluirFuncionario(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(funcionarioService, times(1)).excluirFuncionario(1L);
    }
}