package com.pizzariabellaNapoli.controllerTest;

import com.pizzariabellaNapoli.controller.CarrinhoController;
import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.Funcionario;
import com.pizzariabellaNapoli.domain.ItemCarrinho;
import com.pizzariabellaNapoli.service.CarrinhoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * Description of CarrinhoControllerTest
 * Created by calle on 20/12/2023.
 */
public class CarrinhoControllerTest {

    @Mock
    private CarrinhoService carrinhoService;

    @InjectMocks
    private CarrinhoController carrinhoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodosCarrinhos_DeveRetornarListaDeCarrinhos() {
        Carrinho carrinho1 = new Carrinho(1L, new Funcionario(), List.of(new ItemCarrinho()));
        Carrinho carrinho2 = new Carrinho(2L, new Funcionario(), List.of(new ItemCarrinho()));
        List<Carrinho> listaCarrinhos = Arrays.asList(carrinho1, carrinho2);

        when(carrinhoService.listarTodosCarrinhos()).thenReturn(listaCarrinhos);

        ResponseEntity<List<Carrinho>> responseEntity = carrinhoController.listarTodosCarrinhos();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(listaCarrinhos, responseEntity.getBody());
    }

    @Test
    void buscarCarrinhoPorId_Encontrado_DeveRetornarCarrinho() {
        Carrinho carrinho = new Carrinho(1L, new Funcionario(), List.of(new ItemCarrinho()));

        when(carrinhoService.buscarCarrinhoPorId(1L)).thenReturn(Optional.of(carrinho));

        ResponseEntity<Carrinho> responseEntity = carrinhoController.buscarCarrinhoPorId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carrinho, responseEntity.getBody());
    }

    @Test
    void buscarCarrinhoPorId_NaoEncontrado_DeveRetornarNotFound() {
        when(carrinhoService.buscarCarrinhoPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Carrinho> responseEntity = carrinhoController.buscarCarrinhoPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void salvarCarrinho_DeveRetornarNovoCarrinho() {
        Carrinho carrinho = new Carrinho(1L, new Funcionario(), List.of(new ItemCarrinho()));

        when(carrinhoService.salvarCarrinho(any(Carrinho.class))).thenReturn(carrinho);

        ResponseEntity<Carrinho> responseEntity = carrinhoController.salvarCarrinho(carrinho);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(carrinho, responseEntity.getBody());
    }

    @Test
    void excluirCarrinho_DeveRetornarNoContent() {
        ResponseEntity<Void> responseEntity = carrinhoController.excluirCarrinho(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(carrinhoService, times(1)).excluirCarrinho(1L);
    }

    @Test
    void listarCarrinhosPorFuncionario_DeveRetornarListaDeCarrinhos() {
        Funcionario funcionario = new Funcionario(); // ajuste conforme necessário
        Carrinho carrinho1 = new Carrinho(1L, funcionario, List.of(new ItemCarrinho()));
        Carrinho carrinho2 = new Carrinho(2L, funcionario, List.of(new ItemCarrinho()));
        List<Carrinho> listaCarrinhos = Arrays.asList(carrinho1, carrinho2);

        when(carrinhoService.listarCarrinhosPorFuncionario(funcionario)).thenReturn(listaCarrinhos);

        ResponseEntity<List<Carrinho>> responseEntity = carrinhoController.listarCarrinhosPorFuncionario(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(listaCarrinhos, responseEntity.getBody());
    }

    @Test
    void buscarCarrinhoPorFuncionarioEId_Encontrado_DeveRetornarCarrinho() {
        Long idFuncionario = 1L;
        Long idCarrinho = 1L;
        Funcionario funcionario = new Funcionario();
        Carrinho carrinho = new Carrinho(idCarrinho, funcionario, List.of(new ItemCarrinho()));

        when(carrinhoService.buscarCarrinhoPorFuncionarioEId(funcionario, idCarrinho)).thenReturn(Optional.of(carrinho));

        ResponseEntity<Carrinho> responseEntity = carrinhoController.buscarCarrinhoPorFuncionarioEId(idFuncionario, idCarrinho);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(carrinho, responseEntity.getBody());
    }

    @Test
    void buscarCarrinhoPorFuncionarioEId_NaoEncontrado_DeveRetornarNotFound() {
        Long idFuncionario = 1L;
        Long idCarrinho = 1L;
        Funcionario funcionario = new Funcionario();

        when(carrinhoService.buscarCarrinhoPorFuncionarioEId(funcionario, idCarrinho)).thenReturn(Optional.empty());

        ResponseEntity<Carrinho> responseEntity = carrinhoController.buscarCarrinhoPorFuncionarioEId(idFuncionario, idCarrinho);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
