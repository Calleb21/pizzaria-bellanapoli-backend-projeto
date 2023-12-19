package com.pizzariabellaNapoli.controllerTest;

import com.pizzariabellaNapoli.controller.ItemCarrinhoController;
import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.ItemCarrinho;
import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.service.ItemCarrinhoService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Description of ItemCarrinhoControllerTest
 * Created by calle on 20/12/2023.
 */
public class ItemCarrinhoControllerTest {

    @Mock
    private ItemCarrinhoService itemCarrinhoService;

    @InjectMocks
    private ItemCarrinhoController itemCarrinhoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodosItensCarrinho_DeveRetornarListaDeItensCarrinho() {
        ItemCarrinho itemCarrinho1 = new ItemCarrinho(1L, 2, new Pizza(), new Carrinho());
        ItemCarrinho itemCarrinho2 = new ItemCarrinho(2L, 1, new Pizza(), new Carrinho());
        List<ItemCarrinho> listaItensCarrinho = Arrays.asList(itemCarrinho1, itemCarrinho2);

        when(itemCarrinhoService.listarTodosItensCarrinho()).thenReturn(listaItensCarrinho);

        ResponseEntity<List<ItemCarrinho>> responseEntity = itemCarrinhoController.listarTodosItensCarrinho();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(listaItensCarrinho, responseEntity.getBody());
    }

    @Test
    void buscarItemCarrinhoPorId_Encontrado_DeveRetornarItemCarrinho() {
        ItemCarrinho itemCarrinho = new ItemCarrinho(1L, 2, new Pizza(), new Carrinho());

        when(itemCarrinhoService.buscarItemCarrinhoPorId(1L)).thenReturn(Optional.of(itemCarrinho));

        ResponseEntity<ItemCarrinho> responseEntity = itemCarrinhoController.buscarItemCarrinhoPorId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(itemCarrinho, responseEntity.getBody());
    }

    @Test
    void buscarItemCarrinhoPorId_NaoEncontrado_DeveRetornarNotFound() {
        when(itemCarrinhoService.buscarItemCarrinhoPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<ItemCarrinho> responseEntity = itemCarrinhoController.buscarItemCarrinhoPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void salvarItemCarrinho_DeveRetornarNovoItemCarrinho() {
        ItemCarrinho itemCarrinho = new ItemCarrinho(1L, 2, new Pizza(), new Carrinho());

        when(itemCarrinhoService.salvarItemCarrinho(any(ItemCarrinho.class))).thenReturn(itemCarrinho);

        ResponseEntity<ItemCarrinho> responseEntity = itemCarrinhoController.salvarItemCarrinho(itemCarrinho);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(itemCarrinho, responseEntity.getBody());
    }

    @Test
    void excluirItemCarrinho_DeveRetornarNoContent() {
        ResponseEntity<Void> responseEntity = itemCarrinhoController.excluirItemCarrinho(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(itemCarrinhoService, times(1)).excluirItemCarrinho(1L);
    }
}
